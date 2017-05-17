package com.greenfoxacademy.peer2peer.controller;


import com.greenfoxacademy.peer2peer.model.UserName;
import com.greenfoxacademy.peer2peer.repository.UserNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {


  @Autowired
  UserNameRepository userNameRepository;


  @RequestMapping(value = "/")
  public String start(Model model) {
    model.addAttribute("userName", userNameRepository.findAll());
    return "index";
  }

  @RequestMapping(value = "/show")
  public String show(Model model) {
    model.addAttribute("userName", userNameRepository.findAll());
    return "index";
  }

  @RequestMapping(value = "/enter")
  public String enter(Model model, @RequestParam(name = "name", required = false) String name){
    model.addAttribute("userName", userNameRepository.findAll());
    userNameRepository.save(new UserName("App", " Hi there! Submit your message using the send button!"));
    userNameRepository.save(new UserName(name));
    return "enter";
  }

  @RequestMapping(value = "/list")
  public String send(Model model, @RequestParam(name = "message", required = false) String message, @RequestParam(name="id", required = false) Long id) {
    model.addAttribute("userName", userNameRepository.findAll());
    userNameRepository.findOne(id).setMessage(message);
    return "send";
  }
}

