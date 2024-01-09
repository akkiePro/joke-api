package com.akki.jokeapi.dao;

import com.akki.jokeapi.entity.Jokes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JokesDAO is a repository/DAO class which extends JpaRepository
 * @author Akash Prajapati
 */
@Repository
public interface JokesDAO extends JpaRepository<Jokes, Integer> {

    /**
     * WHERE j_type=${category}
     * @param jType
     * @return
     */
    List<Jokes> findAllByjType(String jType);

}
