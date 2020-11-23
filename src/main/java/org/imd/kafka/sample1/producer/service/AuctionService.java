package org.imd.kafka.sample1.producer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.imd.kafka.sample1.producer.model.event.AuctionBidEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final StreamBridge streamBridge;

    private ObjectMapper objectMapper;

    @Async
    public void sendAuctionEvent(AuctionEvent auctionEvent) throws IOException {
        final String data = objectMapper.writeValueAsString(auctionEvent);
        streamBridge.send("auction-supplier", data);
    }

    @Async
    public void sendAuctionBidEvent(AuctionBidEvent auctionBidEvent) throws IOException {
        final String data = objectMapper.writeValueAsString(auctionBidEvent);
        streamBridge.send("auction-bid-supplier", data);
    }

    @PostConstruct
    public void afterProperties() {
        objectMapper = new ObjectMapper();
    }
}
