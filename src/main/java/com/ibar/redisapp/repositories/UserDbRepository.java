package com.ibar.redisapp.repositories;

import com.ibar.redisapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDbRepository  extends JpaRepository< User, Long> {
}
