package org.imd.kafka.sample1.producer.configuration;

import org.imd.kafka.sample1.producer.model.event.AuctionBidEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionFlushEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Supplier;

@Configuration
public class CloudStreamConfig {

    private EmitterProcessor<AuctionEvent> auctionProcessor = EmitterProcessor.create();
    private EmitterProcessor<AuctionBidEvent> auctionBidProcessor = EmitterProcessor.create();
    private EmitterProcessor<AuctionFlushEvent> auctionFlushProcessor = EmitterProcessor.create();

    @Bean("auctionSink")
    public FluxSink<AuctionEvent> getAuctionSink() {
        return auctionProcessor.sink(FluxSink.OverflowStrategy.BUFFER);
    }

    @Bean("auctionBidSink")
    public FluxSink<AuctionBidEvent> getAuctionBidSink() {
        return auctionBidProcessor.sink(FluxSink.OverflowStrategy.BUFFER);
    }

    @Bean("auctionFlushSink")
    public FluxSink<AuctionFlushEvent> getAuctionFlushSink() {
        return auctionFlushProcessor.sink(FluxSink.OverflowStrategy.BUFFER);
    }

    @Bean
    public Supplier<Flux<AuctionEvent>> auctionSupplier() {
        return () -> Flux.from(this.auctionProcessor).name("producer-metrics-auction").metrics();
    }

    @Bean
    public Supplier<Flux<AuctionBidEvent>> auctionBidSupplier() {
        return () -> Flux.from(this.auctionBidProcessor).name("producer-metrics-auction-bid").metrics();
    }

    @Bean
    public Supplier<Flux<AuctionFlushEvent>> auctionFlushSupplier() {
        return () -> Flux.from(this.auctionFlushProcessor).name("producer-metrics-auction-flush").metrics();
    }
}
