package org.imd.kafka.sample1.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class Sample1ProducerApplication {

	public static void main(String[] args) {
		Schedulers.enableMetrics();
		SpringApplication.run(Sample1ProducerApplication.class, args);
	}

}
