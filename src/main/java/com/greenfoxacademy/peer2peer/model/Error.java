package com.greenfoxacademy.peer2peer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error extends Status {

  private String message;

  public Error(String status, String message) {
    super(status);
    this.message = message;
  }
}
