package com.greenfoxacademy.peer2peer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

  private String id;


  public Client(String id) {
    this.id = id;
  }

  public Client() {}
}
