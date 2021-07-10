package com.marvel.zuptest.repositories;

import com.marvel.zuptest.models.Comics;
import com.marvel.zuptest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicsRepository extends JpaRepository<Comics, Integer> {

}
