package org.imd.kafka.sample1.producer.controller;

import lombok.RequiredArgsConstructor;
import org.imd.kafka.sample1.producer.model.dto.AuctionBidDto;
import org.imd.kafka.sample1.producer.model.dto.AuctionDto;
import org.imd.kafka.sample1.producer.model.dto.AuctionFlushDto;
import org.imd.kafka.sample1.producer.model.event.AuctionBidEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionEvent;
import org.imd.kafka.sample1.producer.model.event.AuctionFlushEvent;
import org.imd.kafka.sample1.producer.service.AuctionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@Validated
@RequestMapping(path = "/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void createAuction(@RequestBody AuctionDto auctionDto) throws IOException {
        final AuctionEvent auctionEvent = new AuctionEvent();
        auctionEvent.setAuctionId(auctionDto.getAuctionId());
        auctionEvent.setAuctionType(auctionDto.getAuctionType());
        auctionEvent.setItemId(auctionDto.getItemId());
        auctionEvent.setTargetPrice(auctionDto.getTargetPrice());
        auctionEvent.setStartDate(auctionDto.getStartDate());

        auctionService.sendAuctionEvent(auctionEvent);
    }

    @PostMapping(value = {"/{aid}/bids"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void createBid(@PathVariable(name = "aid") @NotNull Long aid,  @RequestBody AuctionBidDto bidDto) throws IOException {
        final AuctionBidEvent auctionBidEvent = new AuctionBidEvent();
        auctionBidEvent.setAuctionBidId(bidDto.getAuctionBidId());
        auctionBidEvent.setAuctionId(aid);
        auctionBidEvent.setBidDateTime(LocalDateTime.now());
        auctionBidEvent.setUserId(bidDto.getUserId());
        auctionBidEvent.setBidPrice(bidDto.getBidPrice());

        auctionService.sendAuctionBidEvent(auctionBidEvent);
    }

    @PostMapping(value = {"/{aid}/flush"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void flushAuction(@PathVariable(name = "aid") @NotNull Long aid, @RequestBody AuctionFlushDto flushDto) throws IOException {
        final AuctionFlushEvent auctionFlushEvent = new AuctionFlushEvent();
        auctionFlushEvent.setAuctionId(aid);
        auctionFlushEvent.setRemove(flushDto.getRemove());

        auctionService.sendAuctionFlushEvent(auctionFlushEvent);
    }
}
