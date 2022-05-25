package com.example.demo.email;

import com.example.demo.email.model.EmailToSend;
import com.example.demo.email.threadpool.EmailSenderThreadSchedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
  private final EmailSenderThreadSchedulers emailSenderThreadSchedulers;

  public void sendEmail(String receiver, String title, String content) {
    emailSenderThreadSchedulers.sendEmails(
        List.of(EmailToSend.builder().title(title).content(content).receiver(receiver).build()));
  }
}
