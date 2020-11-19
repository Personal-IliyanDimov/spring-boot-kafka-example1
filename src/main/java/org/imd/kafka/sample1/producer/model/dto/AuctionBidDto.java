package org.imd.kafka.sample1.producer.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class AuctionBidDto {
    private Long userId;
    private BigDecimal bidPrice;
}
