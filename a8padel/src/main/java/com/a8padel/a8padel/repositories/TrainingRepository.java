package com.a8padel.a8padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a8padel.a8padel.entities.Training;


@Repository
public interface TrainingRepository extends JpaRepository <Training, Long>{
 
}
