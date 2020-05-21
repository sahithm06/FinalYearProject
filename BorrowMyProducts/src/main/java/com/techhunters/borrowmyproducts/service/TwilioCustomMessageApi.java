package com.techhunters.borrowmyproducts.service;

import com.techhunters.borrowmyproducts.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TwilioCustomMessageApi implements TwilioMessageApi {

    private TwilioConfig twilioConfig;

    @Autowired
    public void TwilioConfig(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }


    @Override
    public boolean notifyUser(String msg, String phoneNumber) {
        try {
            Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(phoneNumber),
                    new com.twilio.type.PhoneNumber("+15108673525"),
                    msg)
                    .create();
            log.info("Message Successfully sent \"{}\"to this phoneNumber:{} ", msg, phoneNumber);
            return true;

        } catch (Exception exception) {
            log.info("Message sending Failed");
            log.info("exception occurred =====>{}", exception.getMessage());
            return false;
        }

    }
}
