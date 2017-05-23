package com.greenfoxacademy.peer2peer.repository;

import com.greenfoxacademy.peer2peer.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
  List<Message> findAllByOrderByTimestampAsc();
}
