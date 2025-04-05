package com.andev03.hackathon.repository;

import com.andev03.hackathon.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByType(String type);

    @Query("Select count(q) from Question q where q.type = :type")
    int countByType(@Param("type") String type);
}
