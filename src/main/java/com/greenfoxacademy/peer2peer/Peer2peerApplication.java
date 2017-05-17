package com.greenfoxacademy.peer2peer;

import com.greenfoxacademy.peer2peer.repository.Peer2PeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Peer2peerApplication {

	@Autowired
	Peer2PeerRepository peer2PeerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Peer2peerApplication.class, args);
	}

}
