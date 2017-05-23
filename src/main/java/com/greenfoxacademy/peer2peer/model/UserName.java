package com.greenfoxacademy.peer2peer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "chatusername")
@Getter
@Setter
public class UserName {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "Names")
  private String name;

  public UserName(String name) {
    this.name = name;
  }

  public UserName() {
  }

  @Override
  public String toString() {
    return name;
  }
}
