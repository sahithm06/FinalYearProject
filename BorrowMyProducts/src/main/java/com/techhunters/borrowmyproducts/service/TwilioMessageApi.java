package com.techhunters.borrowmyproducts.service;

import org.springframework.stereotype.Service;

@Service
public interface TwilioMessageApi {
    boolean notifyUser(String msg, String phoneNumber);
}
