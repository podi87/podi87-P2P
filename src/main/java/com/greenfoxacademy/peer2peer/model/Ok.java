package com.greenfoxacademy.peer2peer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ok extends Status {

  public Ok(String status) {
    super(status);
  }
}
