package com.greenfoxacademy.peer2peer.repository;


import com.greenfoxacademy.peer2peer.model.UserName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserNameRepository extends CrudRepository<UserName, Long> {

}