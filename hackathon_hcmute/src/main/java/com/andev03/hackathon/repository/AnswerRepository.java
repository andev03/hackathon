package com.andev03.hackathon.repository;

import com.andev03.hackathon.pojo.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByAnswerType(String type);
}
