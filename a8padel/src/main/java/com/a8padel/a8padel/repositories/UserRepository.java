package com.a8padel.a8padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a8padel.a8padel.entities.User;


@Repository
public interface UserRepository extends JpaRepository <User,Long>{
     User findByUsername(String username);
}
