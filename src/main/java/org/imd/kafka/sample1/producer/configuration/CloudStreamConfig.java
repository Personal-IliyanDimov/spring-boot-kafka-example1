package org.imd.kafka.sample1.producer.configuration;

import org.imd.kafka.sample1.producer.model.event.AuctionBidEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Configuration
public class CloudStreamConfig {

    private EmitterProcessor<AuctionEvent> auctionProcessor = EmitterProcessor.create();
    private EmitterProcessor<AuctionBidEvent> auctionBidProcessor = EmitterProcessor.create();

    @Bean("auctionProcessor")
    public EmitterProcessor<AuctionEvent> getAuctionProcessor() {
        return auctionProcessor;
    }

    @Bean("auctionBidProcessor")
    public EmitterProcessor<AuctionBidEvent> getAuctionBidProcessor() {
        return auctionBidProcessor;
    }

    @Bean
    public Supplier<Flux<AuctionEvent>> auctionSupplier() {
        return () -> this.auctionProcessor;
    }

    @Bean
    public Supplier<Flux<AuctionBidEvent>> auctionBidSupplier() {
        return () -> this.auctionBidProcessor;
    }
}
