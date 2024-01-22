package me.hamtom.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMqService {
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(MessageDto message) {
        log.info("sent message: {}", message.toString());
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(MessageDto message) {
        log.info("received message: {}", message.toString());
    }
}
