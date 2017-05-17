package com.greenfoxacademy.peer2peer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Log")
@Getter
@Setter
public class LogMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String path;
  private LocalDateTime localDateTime;
  private String message;
  private String Loglevel;


}
