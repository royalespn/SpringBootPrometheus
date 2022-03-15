package com.iman.springbootprometheus.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(InputChannelRabbitBinding.class)
public class ConsumerRabbit {

    @StreamListener(InputChannelRabbitBinding.NAME)
    public void consume(Event event){
        log.info("message on rabbit channel:" + event.getOrganizationId());
    }
}
