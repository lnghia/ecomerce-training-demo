package com.example.demo.email;

import com.example.demo.email.model.EmailToSend;
import com.example.demo.email.threadpool.EmailSenderThreadSchedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
  @Autowired private final JavaMailSender javaMailSender;

  private final EmailSenderThreadSchedulers emailSenderThreadSchedulers;

  public void sendEmail(String receiver, String title, String content)
      throws ExecutionException, InterruptedException {
    emailSenderThreadSchedulers.sendEmails(
        List.of(EmailToSend.builder().title(title).content(content).receiver(receiver).build()));
  }
}
