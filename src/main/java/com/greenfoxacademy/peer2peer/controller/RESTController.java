package com.greenfoxacademy.peer2peer.controller;


import com.greenfoxacademy.peer2peer.Service.ValidatorService;
import com.greenfoxacademy.peer2peer.model.*;

import com.greenfoxacademy.peer2peer.model.Error;
import com.greenfoxacademy.peer2peer.repository.MessageRepository;
import com.greenfoxacademy.peer2peer.repository.UserNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class RESTController {

  private String envUrl = System.getenv("CHAT_APP_PEER_ADDRESS") + "/api/message/receive";
  private String envID = System.getenv("CHAT_APP_UNIQUE_ID");

  @Autowired
  UserNameRepository userNameRepository;
  @Autowired
  MessageRepository messageRepository;
  @Autowired
  ValidatorService validatorService;

  @CrossOrigin("*")
  @RequestMapping(value = "/api/message/receive", method = RequestMethod.POST)
  public Status postMessage(@RequestBody() Received received) {
    if (received.getMessage().getUsername()!=null && received.getMessage().getText()!=null && !received.getClient().getId().equals(envID) && received.getMessage().getText().equals("")) {
      messageRepository.save(new Message(received.getMessage().getUsername(), received.getMessage().getText()));
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject(envUrl, received, Ok.class);
      return new Ok(validatorService.validator(received));
      } else {
      return new Error("error", validatorService.validator(received));
    }
  }



}
