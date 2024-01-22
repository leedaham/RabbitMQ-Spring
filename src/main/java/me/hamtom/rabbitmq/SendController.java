package me.hamtom.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendController {

    private final RabbitMqService service;

    @PostMapping("/send")
    public ResponseEntity<?> sendMsg(@RequestBody MessageDto message) {
        service.sendMessage(message);
        return ResponseEntity.ok("sent complete!");
    }
}
