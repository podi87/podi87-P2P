package com.greenfoxacademy.peer2peer.controller;


import com.greenfoxacademy.peer2peer.Service.ValidatorService;
import com.greenfoxacademy.peer2peer.model.*;
import com.greenfoxacademy.peer2peer.repository.MessageRepository;
import com.greenfoxacademy.peer2peer.repository.UserNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class MainController {

  @Autowired
  private UserNameRepository userNameRepository;
  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private ValidatorService validatorService;

 public String error = "";
 private String envLog = System.getenv("CHAT_APP_LOGLEVEL");
 private String envUrl = System.getenv("CHAT_APP_PEER_ADDRESS") + "/api/message/receive";
 private String envID = System.getenv("CHAT_APP_UNIQUE_ID");

 public void logging(LogMessage logMessage) {
   System.out.println(logMessage.getTimeLog()+"  " + envLog +logMessage.getRequest()+logMessage.getPath()
           +logMessage.getMethod()+logMessage.getParam());
 }

  @RequestMapping(value = "/")
  public String start(Model model) {
    model.addAttribute("users", userNameRepository.findAll());
    model.addAttribute("messages", messageRepository.findAllByOrderByTimestampAsc());
    model.addAttribute("actualName", userNameRepository.findOne(1l));
    if(envLog.equals("INFO")){
      logging(new LogMessage(" / ", " GET ", ""));
    } else if (envLog.equals("ERROR")){
      System.err.println();
    }
    if (userNameRepository.count()==0) {
      return "enter";
    } else {
      return "startpage";
    }
  }

  @RequestMapping(value = "/enter", method = RequestMethod.POST)
  public String enter(Model model, @RequestParam(name = "newName") String newName) {
   String temp ="";
   long id = 1;
    if (newName.isEmpty()) {
      error = "Please provide your username!";
      temp = "enter";
    } else {
      model.addAttribute("error", error);
      messageRepository.save(new Message("Api", "Hi there! Submit your message using the send button!"));
      userNameRepository.save(new UserName(newName));
      temp = "redirect:/";
    }
    if(envLog.equals("INFO")){
      logging(new LogMessage(" /enter", " POST ", "newName: " + newName));
    } else if (envLog.equals("ERROR")){
      System.err.println();
    }
   return temp;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Model model, @RequestParam(name = "id") long id, @RequestParam(name = "changedName") String changedName){
    userNameRepository.findOne(id).setName(changedName);
    userNameRepository.save(userNameRepository.findOne(id));
    if(envLog.equals("INFO")){
      logging(new LogMessage(" /update", " POST ", "changedName: " + changedName));
    } else if (envLog.equals("ERROR")){
      System.err.println();
    }
    return "redirect:/";
  }

  @RequestMapping(value = "/send", method = RequestMethod.POST)
  public String chatPost(Model model, @RequestParam(name = "message") String message, @RequestParam(name = "id") long id) {
    Message receivedMessage = new Message(userNameRepository.findOne(id).getName(), message);
    if (receivedMessage.getText().equals("")) {
      messageRepository.save(receivedMessage);
    }
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForObject(envUrl, new Received(receivedMessage, new Client(envID)), Received.class);
    if(envLog.equals("INFO")){
      logging(new LogMessage(" /send ", " POST ", "message: " + message + " id: " + String.valueOf(id)));
    } else if (envLog.equals("ERROR")){
      System.err.println();
    }
    return "redirect:/";
  }

}

