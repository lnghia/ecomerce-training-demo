package com.example.demo.email.model;

import com.example.demo.email.task.SendEmailTask;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class SendEmailExecutionResult {
  private HashMap<String, SendEmailTask.SendEmailResult> successFulSendResults;

  private HashMap<String, SendEmailTask.SendEmailResult> failedSendResults;
}
