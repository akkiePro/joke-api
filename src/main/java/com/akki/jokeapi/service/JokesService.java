package com.akki.jokeapi.service;

import com.akki.jokeapi.dao.JokesDAO;
import com.akki.jokeapi.entity.Jokes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * JokesService is a service layer of JokesController
 * @author Akash Prajapati
 */
@Service
public class JokesService {

    @Autowired
    private JokesDAO jokesDAO;

    private static final Logger logger = LoggerFactory.getLogger(JokesService.class);

    /**
     * List of all Jokes from JokesDAO
     * @return ResponseEntity of List
     */
    public ResponseEntity<List<Jokes>> getAllJokes() {
        logger.info("Entered ::JokesService.getAllJokes()::");
        try {
            List<Jokes> allJokes = jokesDAO.findAll();
            logger.info("JokesService.getAllJokes():: allJokes = {}", allJokes);
            return new ResponseEntity<>(allJokes, HttpStatus.OK);
        }catch (Exception e) {
            logger.error("JokesService.getAllJokes() threw an Exception");
            e.printStackTrace();
        }
        logger.info("Entered ::JokesService.getAllJokes()::");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /**
     * gives all Jokes by jType/category
     * @param jType
     * @return ResponseEntity of List
     */
    public ResponseEntity<List<Jokes>> getAllJokesByJType(String jType) {
        logger.info("Entered ::JokesService.getAllJokesByJType()::");
        try {
            List<Jokes> allJokes = jokesDAO.findAllByjType(jType);
            logger.info("JokesService.getAllJokesByJType():: allJokes = {}", allJokes);
            return new ResponseEntity<>(allJokes, HttpStatus.OK);
        }catch (Exception e) {
            logger.error("JokesService.getAllJokesByJType() threw an Exception");
            e.printStackTrace();
        }
        logger.info("Entered ::JokesService.getAllJokesByJType()::");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /**
     * insert a Joke in DB
     * @param joke
     * @return result of insertion in DB
     */
    public String insertJoke(Jokes joke) {
        jokesDAO.save(joke);
        return "success";
    }

    /**
     * insert a Joke in DB
     * @param joke
     * @return result of insertion in DB
     */
//    public String insertJokes(List<String> jokes) {
//        System.out.println(jokes);
////        jokesDAO.saveAll(jokes);
//        return "success";
//    }
}
