package com.ttn.bootcamp.repository.day2;

import com.ttn.bootcamp.entity.day2.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
