package com.example.springawssns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreateTopicResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ramin Esfandiari (Inmeta Consulting AS) on 16.02.2018.
 *
 * Test
 */
@RestController
@Slf4j
public class SNSController {

    private final AmazonSNS snsClient;

    public SNSController(AmazonSNS snsClient) {
        this.snsClient = snsClient;
    }

    @GetMapping("/api/topic/create/{topicName}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTopic(@PathVariable String topicName) {
        CreateTopicResult topicResult = snsClient.createTopic(topicName);
        log.info("---> Topic created with ARN -> {}", topicResult.getTopicArn());
        return topicResult.getTopicArn();
    }

    @DeleteMapping("api/topic/delete/{topicARN}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTopic(@PathVariable String topicARN) {
        log.info("---> Deleting topic with ARN -> {}", topicARN);
        snsClient.deleteTopic(topicARN);
    }
}
