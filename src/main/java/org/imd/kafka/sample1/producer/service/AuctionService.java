package org.imd.kafka.sample1.producer.service;

import org.imd.kafka.sample1.producer.model.event.AuctionBidEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.EmitterProcessor;

import java.io.IOException;

@Service
public class AuctionService {

    @Qualifier("auctionProcessor")
    @Autowired
    private EmitterProcessor<AuctionEvent> auctionEventEmitterProcessor;

    @Qualifier("auctionBidProcessor")
    @Autowired
    private EmitterProcessor<AuctionBidEvent> auctionBidEventEmitterProcessor;


    public void sendAuctionEvent(AuctionEvent auctionEvent) throws IOException {
        auctionEventEmitterProcessor.onNext(auctionEvent);
    }

    public void sendAuctionBidEvent(AuctionBidEvent auctionBidEvent) throws IOException {
        auctionBidEventEmitterProcessor.onNext(auctionBidEvent);
    }
}
