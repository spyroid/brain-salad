package com.symetricum.brainsalad.api;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.symetricum.brainsalad.api.ApiHandler.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductLookupService {

    Cache<SearchKey, Product> cache = Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(10)).build();

    @PostConstruct
    void init() {

        List<ProductWrapper> res = find(SearchKeyBuilder.create("ADP").addKey("123").build());

    }

    List<ProductWrapper> find(List<SearchKey> searchKeys) {

        List<ProductWrapper> res = new ArrayList<>();

        for(SearchKey key: searchKeys) {

        }

        // productDao.find(searchKeys);


        return res;

    }

    static class SearchKeyBuilder {

        private final String productIdType;
        private List<SearchKey> keys = new ArrayList<>();

        public SearchKeyBuilder(String productIdType) {
            this.productIdType = productIdType;
        }

        static SearchKeyBuilder create(String productIdType) {
            return new SearchKeyBuilder(productIdType);
        }

        List<SearchKey> build() {
            return keys;
        }

        public SearchKeyBuilder addKey(String productId) {
            keys.add(new SearchKey(productId, productIdType));
            return this;
        }
    }

    @Data
    @AllArgsConstructor
    static class SearchKey {
        public String productId;
        public String productIdType;
    }


    static class ProductWrapper {
    }
}
