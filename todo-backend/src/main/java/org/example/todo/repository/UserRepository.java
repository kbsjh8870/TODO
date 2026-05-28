package org.example.todo.repository;

import org.example.todo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
     Optional<User> findByNameAndPassword(String name, String password);
}
