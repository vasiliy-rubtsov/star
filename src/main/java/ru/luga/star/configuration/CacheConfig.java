package ru.luga.star.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {
    @Bean
    public Caffeine<Object, Object> getCaffeine() {
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder();
        caffeine
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .initialCapacity(10);
        return caffeine;
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine ) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(List.of("UserActivityProfile"));
        return cacheManager;
    }
}
