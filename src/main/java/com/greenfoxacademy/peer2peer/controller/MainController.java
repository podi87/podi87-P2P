package com.greenfoxacademy.peer2peer.controller;


import com.greenfoxacademy.peer2peer.Service.ValidatorService;
import com.greenfoxacademy.peer2peer.model.LogMessage;
import com.greenfoxacademy.peer2peer.model.Message;
import com.greenfoxacademy.peer2peer.model.UserName;
import com.greenfoxacademy.peer2peer.repository.MessageRepository;
import com.greenfoxacademy.peer2peer.repository.UserNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

  @Autowired
  private UserNameRepository userNameRepository;
  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private ValidatorService validatorService;

 public String error = "";
  String env = System.getenv("CHAT_APP_LOGLEVEL");

 public void logging(LogMessage logMessage) {
   System.out.println(logMessage.getTimeLog()+"  " +env+logMessage.getRequest()+logMessage.getPath()
           +logMessage.getMethod()+logMessage.getParam());
 }

  @RequestMapping(value = "/")
  public String start(Model model) {
    System.out.println(userNameRepository.count());
    model.addAttribute("users", userNameRepository.findAll());
    model.addAttribute("messages", messageRepository.findAllByOrderByTimestampAsc());
    model.addAttribute("actualName", userNameRepository.findOne(1l));
//    if(env.equals("INFO")){
//      logging(new LogMessage(" / ", " GET ", ""));
//    } else if (env.equals("ERROR")){
//      System.err.println();
//    }
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
//    if(env.equals("INFO")){
//      logging(new LogMessage(" /enter", " POST ", "newName: " + newName));
//    } else if (env.equals("ERROR")){
//      System.err.println();
//    }
   return temp;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Model model, @RequestParam(name = "id") long id, @RequestParam(name = "changedName") String changedName){
    userNameRepository.findOne(id).setName(changedName);
    userNameRepository.save(userNameRepository.findOne(id));
//    if(env.equals("INFO")){
//      logging(new LogMessage(" /update", " POST ", "changedName: " + changedName));
//    } else if (env.equals("ERROR")){
//      System.err.println();
//    }
    return "redirect:/";
  }

  @RequestMapping(value = "/send", method = RequestMethod.POST)
  public String chatPost(Model model, @RequestParam(name = "message") String message, @RequestParam(name = "id") long id) {
    messageRepository.save(new Message(userNameRepository.findOne(id).getName(), message));
    if(env.equals("INFO")){
      logging(new LogMessage(" /send ", " POST ", "message: " + message + " id: " + String.valueOf(id)));
    } else if (env.equals("ERROR")){
      System.err.println();
    }
    return "redirect:/";
  }

}

