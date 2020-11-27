package org.imd.kafka.sample1.producer.model.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuctionFlushEvent {
    private Long auctionId;
}
