package com.greenfoxacademy.peer2peer.repository;

import com.greenfoxacademy.peer2peer.model.UserName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNameRepository extends CrudRepository<UserName, Long> {
}