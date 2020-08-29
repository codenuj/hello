package com.hello.user.bday.repository;

import com.hello.user.bday.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String userName);
}
