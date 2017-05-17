package com.greenfoxacademy.peer2peer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "name")
@Getter
@Setter
public class UserName {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "Names")
  private String name;

  @Column(name = "Messages")
  private String message;

  public UserName(String name, String message) {
    this.name = name;
    this.message = message;
  }

  public UserName(String name) {
    this.name = name;
  }

  public UserName() {
  }
}
