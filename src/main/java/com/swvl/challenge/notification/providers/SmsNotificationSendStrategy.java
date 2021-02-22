package com.swvl.challenge.notification.providers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationSendStrategy implements INotificationSendStrategy {

  private final Logger logger = LoggerFactory.getLogger(SmsNotificationSendStrategy.class);

  public final String ACCOUNT_SID = "AC1656eff19c72593267d66e901b478dd6";
  public final String AUTH_TOKEN = "ef1dc5a3196186cb8a129670e65983cf";

  @Override
  public void send(String to, String message) {
    logger.info("Sending SMS to: " + to + " with message: " + message);
  }

  private void realProviderSend(String to, String message) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message.creator(new PhoneNumber(to), new PhoneNumber("+18304689845"), message).create();
  }
}
