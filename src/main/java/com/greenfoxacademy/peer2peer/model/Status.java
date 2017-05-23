package com.greenfoxacademy.peer2peer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status {

  private String status;

  public Status() {
  }

  public Status(String status) {
    this.status = status;
  }
}
