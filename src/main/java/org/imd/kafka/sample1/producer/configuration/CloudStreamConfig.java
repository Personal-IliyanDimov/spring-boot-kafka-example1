package org.imd.kafka.sample1.producer.configuration;

import org.imd.kafka.sample1.producer.model.event.AuctionBidEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class CloudStreamConfig {

    @Bean
    public Supplier<AuctionEvent> auctionSupplier() {
        return () -> null;
    }

    @Bean
    public Supplier<AuctionBidEvent> auctionBidSupplier() {
        return () -> null;
    }
}
