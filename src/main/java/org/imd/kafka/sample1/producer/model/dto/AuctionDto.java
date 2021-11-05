package org.imd.kafka.sample1.producer.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.imd.kafka.sample1.producer.model.event.type.AuctionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AuctionDto {
    private String auctionId;
    private AuctionType auctionType;
    private Long itemId;
    private BigDecimal targetPrice;
    private LocalDateTime startDate;
}
