package com.greenfoxacademy.peer2peer.repository;

import com.greenfoxacademy.peer2peer.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
  List<Message> findAllByOrderByTimestampAsc();
}
