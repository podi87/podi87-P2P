package com.greenfoxacademy.peer2peer.controller;


import com.greenfoxacademy.peer2peer.Service.ValidatorService;
import com.greenfoxacademy.peer2peer.model.*;

import com.greenfoxacademy.peer2peer.model.Error;
import com.greenfoxacademy.peer2peer.repository.MessageRepository;
import com.greenfoxacademy.peer2peer.repository.UserNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RESTController {

  @Autowired
  UserNameRepository userNameRepository;
  @Autowired
  MessageRepository messageRepository;
  @Autowired
  ValidatorService validatorService;

  @CrossOrigin("*")
  @RequestMapping(value = "/api/message/receive", method = RequestMethod.POST)
  public Status postMessage(@RequestBody() Received received) {
    if (received.getMessage().getUsername()!=null && received.getMessage().getText()!=null){
      messageRepository.save(new Message(received.getMessage().getUsername(), received.getMessage().getText()));
      return new Ok(validatorService.validator(received));
    } else {
      return new Error("error", validatorService.validator(received));
    }
  }


}
