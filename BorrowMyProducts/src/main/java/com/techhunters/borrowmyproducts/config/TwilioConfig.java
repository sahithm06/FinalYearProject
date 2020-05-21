package com.techhunters.borrowmyproducts.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "twilio")

public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String serviceSid;
}
