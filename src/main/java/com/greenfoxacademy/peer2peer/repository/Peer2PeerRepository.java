package com.greenfoxacademy.peer2peer.repository;


import com.greenfoxacademy.peer2peer.model.LogMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface Peer2PeerRepository extends CrudRepository<LogMessage, Long> {
}
