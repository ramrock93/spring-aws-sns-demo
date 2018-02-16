package com.example.springawssns.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.Topic;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
@Slf4j
public class SNSConfiguration {

    @Value("${aws.sns.accessKey}")
    private String accessKey;
    @Value("${aws.sns.secretKey}")
    private String secretKey;
    @Value("${aws.sns.region}")
    private String region;

    @Bean
    public AmazonSNS ssnClient() {
        // Create Amazon SNS Client
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region)
                .build();
    }
}