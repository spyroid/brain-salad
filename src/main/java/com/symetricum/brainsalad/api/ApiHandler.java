package com.symetricum.brainsalad.api;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.ConstraintViolation;
import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.Validator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@Configuration
public class ApiHandler {

    @Bean
    RouterFunction<ServerResponse> getEmployeeByIdRoute() {
        return route(POST("/do"), this::doPost);
    }

    ObjectMapper mapper = new ObjectMapper();
    FilterProvider filterProvider = new SimpleFilterProvider().addFilter("productFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id"));

    private Mono<ServerResponse> doPost(ServerRequest req) {

        Validator<DataRequest> validator = ValidatorBuilder.<DataRequest>of()
                ._string(DataRequest::getId, "id", c -> c.notBlank().message("id is required")).build();

        return req.bodyToMono(DataRequest.class)
                .switchIfEmpty(Mono.error(new ServerWebInputException("Request cannot be empty")))
                .flatMap(body -> {
                    ConstraintViolations vl = validator.validate(body);
                    if (!vl.isValid()) {
                        return Mono.error(new ServerWebInputException(vl.violations().stream().map(ConstraintViolation::message).collect(Collectors.joining("; "))));
                    }
                    return Mono.defer(() -> Mono.just(toJson(getProducts(body))))
                            .subscribeOn(Schedulers.boundedElastic())
                            .onErrorResume(err -> {
                                log.error(err.getMessage());
                                return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while call blocking()"));
                            })
                            .flatMap(s -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(s))
                            ;
                })
                ;
//                .flatMap(data -> Mono.fromCallable(() -> blocking(data)).publishOn(boundedElastic()))
////                .publishOn(boundedElastic())
//                .onErrorResume(err -> {
//                    log.error(err.getMessage());
//                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while call blocking()"));
//                })
//                .doOnNext(dataRequest -> {
//                    log.info(dataRequest.toString());
//                })
//                .flatMap(o -> ok().bodyValue(o));


    }

    @SneakyThrows
    private String toJson(List<Product> products) {
        return mapper.addMixIn(Product.class, MixIn.class).writer(filterProvider).writeValueAsString(products);
    }

    private List<Product> getProducts(DataRequest dataRequest) {

        log.info("do blocking");

//        if (true) throw new RuntimeException("!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return List.of(new Product("11", "aaa"), new Product("22", "bbb"));
    }

    @JsonFilter("productFilter")
    static class MixIn {

    }

    @Data
    static class DataRequest {
        public String id;
        public String name;
    }

    @Data
    @AllArgsConstructor
    static class Product {
        public String id;
        public String name;
    }
}
