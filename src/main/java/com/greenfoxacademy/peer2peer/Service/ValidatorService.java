package com.greenfoxacademy.peer2peer.Service;

import com.greenfoxacademy.peer2peer.model.Received;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidatorService {

  public String validator(Received received) {
    if (received.getMessage().getUsername() == null) {
      return "Missing fields: message.username";
    }
     else if (received.getMessage().getText() == null) {
      return "Missing fields: message.text";
    }
     else if (received.getClient() == null) {
      return "Missing fields: client.id";
    }
    return "Unknown error";
  }
}
