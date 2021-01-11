package com.symetricum.brainsalad.api;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.symetricum.brainsalad.api.ApiHandler.Product;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

@Slf4j
@SpringBootApplication
public class ApiApp {

    Cache<String, Product> cache = Caffeine.newBuilder().expireAfterWrite(Duration.ofSeconds(5)).build();

    public static void main(String[] args) {
        SpringApplication.run(ApiApp.class, args);
    }

    @Autowired
    ProductLookupService productLookupService;

    @SneakyThrows
    @PostConstruct
    void init() {

        cache.put("1", new Product("111", "aabbcc"));

        Product v1 = cache.getIfPresent("1");
        log.info("(1) -> " + v1);

        sleep(6_000);

        Product v2 = cache.getIfPresent("1");
        log.info("(2) -> " + v2 + " - " + v1);


    }
}
