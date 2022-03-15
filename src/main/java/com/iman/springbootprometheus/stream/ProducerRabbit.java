package com.iman.springbootprometheus.stream;

import com.iman.springbootprometheus.controllers.StudentController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(OutputChannelRabbitBinding.class)
@RestController
public class ProducerRabbit {

    Logger log = LoggerFactory.getLogger(StudentController.class);

    OutputChannelRabbitBinding outputChannelRabbitBinding;

    public ProducerRabbit(OutputChannelRabbitBinding outputChannelRabbitBinding) {
        this.outputChannelRabbitBinding = outputChannelRabbitBinding;
    }

    @PostMapping("/publishToRabbit")
    public String publishToKafka(@RequestBody Event event) {

        outputChannelRabbitBinding.channel()
                .send(MessageBuilder.withPayload(event)
                        .setHeader("type", "Event")
                        .build());
        log.info("Message sent to rabbit");
        return "Message sent";
    }
}
