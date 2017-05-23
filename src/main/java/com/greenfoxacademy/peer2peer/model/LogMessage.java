package com.greenfoxacademy.peer2peer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class LogMessage {

  private long id;

  private String path;
  private String timeLog;
  private String request;
  private String method;
  private String param;

  public LogMessage(String path, String method, String param) {

    LocalDateTime localDateTime = LocalDateTime.now();
    StringBuilder time = new StringBuilder(localDateTime.toString());
    time.setCharAt(10, ' ');
    this.timeLog = time.toString();
    this.request = " Request: ";
    this.path = path;
    this.method = method;
    this.param = param;
  }
}
