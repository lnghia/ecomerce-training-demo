package com.example.demo.email.task;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.Callable;

@Getter
@Slf4j
public class SendEmailTask implements Callable<SendEmailTask.SendEmailResult> {
  private final JavaMailSender javaMailSender;

  private final String title;

  private final String content;

  private final String receiver;

  public SendEmailTask(
      JavaMailSender javaMailSender, String title, String content, String receiver) {
    this.javaMailSender = javaMailSender;
    this.title = title;
    this.content = content;
    this.receiver = receiver;
  }

  @Override
  public SendEmailResult call() {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(receiver);
    message.setSubject(title);
    message.setText(content);

    try {
      javaMailSender.send(message);

      return SendEmailResult.createSuccessResult(title, content, receiver);
    } catch (Exception ex) {
      log.error(ex.getMessage());

      return SendEmailResult.createFailureResultWithThrowable(title, content, receiver, ex);
    }
  }

  @Data
  public static class SendEmailResult {
    public final String title;

    public final String content;

    public final String receiver;

    public final Throwable error;

    public SendEmailResult(String title, Throwable error, String content, String receiver) {
      this.title = title;
      this.error = error;
      this.content = content;
      this.receiver = receiver;
    }

    public boolean isOk() {
      return error == null;
    }

    public static SendEmailTask.SendEmailResult createSuccessResult(
        String title, String content, String receiver) {
      return new SendEmailResult(title, null, content, receiver);
    }

    public static SendEmailResult createFailureResultWithThrowable(
        String title, String content, String receiver, Throwable error) {
      return new SendEmailResult(title, error, content, receiver);
    }
  }
}
