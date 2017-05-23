package com.greenfoxacademy.peer2peer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Received {

  private Message message;
  private Client client;

  public Received(Message message, Client client) {
    this.message = message;
    this.client = client;
  }

  public Received(){

  }
}
