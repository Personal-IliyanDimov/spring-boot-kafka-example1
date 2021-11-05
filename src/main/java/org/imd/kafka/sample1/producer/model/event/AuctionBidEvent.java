package org.imd.kafka.sample1.producer.model.event;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AuctionBidEvent {
    private String auctionBidId;
    private String auctionId;
    private String userId;
    private BigDecimal bidPrice;
    private LocalDateTime bidDateTime;
}
