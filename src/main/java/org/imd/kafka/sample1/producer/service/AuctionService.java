package org.imd.kafka.sample1.producer.service;

import org.imd.kafka.sample1.producer.model.event.AuctionBidEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionFlushEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;

import java.io.IOException;

@Service
public class AuctionService {

    @Qualifier("auctionSink")
    @Autowired
    private FluxSink<AuctionEvent> auctionEventSink;

    @Qualifier("auctionBidSink")
    @Autowired
    private FluxSink<AuctionBidEvent> auctionBidEventSink;

    @Qualifier("auctionFlushSink")
    @Autowired
    private FluxSink<AuctionFlushEvent> auctionFlushEventSink;


    public void sendAuctionEvent(AuctionEvent auctionEvent) throws IOException {
        auctionEventSink.next(auctionEvent);
    }

    public void sendAuctionBidEvent(AuctionBidEvent auctionBidEvent) throws IOException {
        auctionBidEventSink.next(auctionBidEvent);
    }

    public void sendAuctionFlushEvent(AuctionFlushEvent auctionFlushEvent) {
        auctionFlushEventSink.next(auctionFlushEvent);
    }
}
