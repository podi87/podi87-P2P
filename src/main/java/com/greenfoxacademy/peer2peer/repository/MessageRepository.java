package com.greenfoxacademy.peer2peer.repository;

import com.greenfoxacademy.peer2peer.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageRepository extends CrudRepository<Message, Long> {
  List<Message> findAllByOrderByTimestampAsc();
}
