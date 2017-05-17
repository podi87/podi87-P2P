package com.greenfoxacademy.peer2peer.controller;


import com.greenfoxacademy.peer2peer.repository.Peer2PeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

  @Autowired
  Peer2PeerRepository peer2PeerRepository;


  @RequestMapping(value = "/")
  public String log(Model model){
    return "index";
  }



}

