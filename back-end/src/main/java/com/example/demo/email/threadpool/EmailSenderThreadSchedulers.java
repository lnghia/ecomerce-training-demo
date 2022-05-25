package com.example.demo.email.threadpool;

import com.example.demo.email.model.EmailToSend;
import com.example.demo.email.model.SendEmailExecutionResult;
import com.example.demo.email.task.SendEmailTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

@Component
@Slf4j
public class EmailSenderThreadSchedulers implements AutoCloseable {
  private final int THREAD_NUM = 15;

  private final ExecutorService sendExecutor;

  private final CompletionService<SendEmailTask.SendEmailResult> sendEmailService;

  private HashMap<String, SendEmailTask.SendEmailResult> successfulSends;

  private HashMap<String, SendEmailTask.SendEmailResult> failedSends;

  private final JavaMailSender javaMailSender;

  @Autowired
  public EmailSenderThreadSchedulers(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
    sendExecutor = Executors.newFixedThreadPool(THREAD_NUM);
    sendEmailService = new ExecutorCompletionService<>(sendExecutor);
    successfulSends = new HashMap<>();
    failedSends = new HashMap<>();
  }

  @Override
  public void close() {
    if (sendExecutor != null) {
      sendExecutor.shutdown();
    }
  }

  public SendEmailExecutionResult sendEmails(List<EmailToSend> emailToSendList) {
    successfulSends = new HashMap<>(emailToSendList.size());
    failedSends = new HashMap<>(emailToSendList.size());

    scheduleSendEmail(emailToSendList);

    log.info("All email sends have been scheduled.");

    for (int finished = 0; finished < emailToSendList.size(); ++finished) {
      try {
        Future<SendEmailTask.SendEmailResult> resultFuture = sendEmailService.take();
        SendEmailTask.SendEmailResult sendEmailResult = resultFuture.get();

        if (sendEmailResult.isOk()) {
          successfulSends.put(sendEmailResult.getReceiver(), sendEmailResult);
        } else {
          log.error(sendEmailResult.getReceiver(), sendEmailResult.getError());
          failedSends.put(sendEmailResult.getReceiver(), sendEmailResult);
        }
      } catch (Exception ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();
      }
    }

    log.info("All emails have been sent.");

    return SendEmailExecutionResult.builder()
        .successFulSendResults(successfulSends)
        .failedSendResults(failedSends)
        .build();
  }

  private void scheduleSendEmail(List<EmailToSend> emailToSendList) {
    emailToSendList.forEach(
        item -> {
          log.info("Scheduling email sending for: " + item.getReceiver());
          SendEmailTask task =
              new SendEmailTask(
                  javaMailSender, item.getTitle(), item.getContent(), item.getReceiver());
          sendEmailService.submit(task);
        });
  }
}
