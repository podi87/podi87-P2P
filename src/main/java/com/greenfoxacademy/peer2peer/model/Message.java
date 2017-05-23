package com.greenfoxacademy.peer2peer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@Setter
@Getter
public class Message {

  @Id
  private long id;

  private String username;
  private String text;
  private long timestamp;


  public Message(String username, String text) {
    this.id = generateId();
    this.username = username;
    this.text = text;
    this.timestamp = new Date().getTime()/1000;
  }

  public Message() {
  }

  public Long generateId() {
    return 1000000 + (long)(Math.random()* 9999999);
  }

}
