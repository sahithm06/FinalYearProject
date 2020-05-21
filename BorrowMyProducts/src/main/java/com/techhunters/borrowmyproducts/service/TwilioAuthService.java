package com.techhunters.borrowmyproducts.service;


import com.techhunters.borrowmyproducts.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TwilioAuthService implements AuthService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioAuthService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public boolean sendOtp(String phoneNumber) {
        try {
            log.info(twilioConfig.getAccountSid() + "   " + twilioConfig.getAuthToken() + "   " + twilioConfig.getServiceSid());
            log.info(phoneNumber);
            Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
            Verification verification = Verification
                    .creator(twilioConfig.getServiceSid(),
                            phoneNumber,
                            "sms")
                    .create();


            log.info("verification status: {}", verification.getStatus());

            return true;
        } catch (Exception exception) {
            log.error("exception : {}", exception.getMessage());
            return false;
        }

    }

    @Override
    public boolean verifyOtp(String otp, String phoneNumber) {
        try {
            Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
            VerificationCheck verificationCheck = VerificationCheck
                    .creator(twilioConfig.getServiceSid(), otp)
                    .setTo(phoneNumber).create();
            log.info("otp :" + verificationCheck.getStatus());
            if (verificationCheck.getStatus().equals("approved")) {
                log.info("successfully verified otp");
                return true;
            } else {
                log.info("user entered a wrong otp: {}, for this phone number: {}", otp, phoneNumber);
                return false;
            }


        } catch (Exception exception) {
            log.error("exception : {}", exception.getMessage());

            return false;
        }

    }


}
