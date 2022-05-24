package com.example.demo.services.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
  @Autowired private final JavaMailSender javaMailSender;

  // TODO: should take advantage of thread pool to schedule and execute sending emails
  @Async
  public boolean sendOTPEmail(String otp, String receiver) {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(receiver);
    message.setSubject("Please verify your email address");
    message.setText(
        "Hey! Hope you are doing great, thank you so much for using our service, just one final step! Here is your verification code: \n"
            + otp
            + "\n"
            + "Enter the code to verify your email and you should be good to go. Enjoy!\n"
            + "This code will be valid within 5 minutes.");

    try {
      new Thread(() -> this.javaMailSender.send(message)).start();
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }

    return true;
  }

  // TODO: should take advantage of thread pool to schedule and execute sending emails
  @Async
  public boolean sendResetPwOTPEmail(String otp, String receiver) {
    SimpleMailMessage message = new SimpleMailMessage();
    String content =
        String.format(
            "Reset the password for your account %s. Here is your code: %s", receiver, otp);

    message.setTo(receiver);
    message.setSubject("Your account password reset");
    message.setText(content);

    try {
      new Thread(() -> this.javaMailSender.send(message)).start();
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }

    return true;
  }

  // TODO: should take advantage of thread pool to schedule and execute sending emails
  public boolean sendPasswordHasChangedEmail(String receiver) {
    SimpleMailMessage message = new SimpleMailMessage();
    Date now = new Date();
    String content =
        String.format(
            "Your account %s password has been changed successfully today at %s", receiver, now);

    message.setTo(receiver);
    message.setSubject("Your account password has been changed");
    message.setText(content);

    try {
      new Thread(() -> this.javaMailSender.send(message)).start();
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }

    return true;
  }
}
