package com.akki.jokeapi.service;

import com.akki.jokeapi.controller.JokesList;
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
import java.util.Random;

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
    public ResponseEntity<List<String>> getAllJokes() {
        logger.info("Entered ::JokesService.getAllJokes()::");
        try {
            List<String> allJokes = fetchAllJokes();
            logger.info("JokesService.getAllJokes():: allJokes = {}", allJokes);
//            System.out.println("all Jokes hahaha" + allJokes);
            return new ResponseEntity<>(allJokes, HttpStatus.OK);
        }catch (Exception e) {
            logger.error("JokesService.getAllJokes() threw an Exception");
            e.printStackTrace();
        }
        logger.info("Entered ::JokesService.getAllJokes()::");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    private List<String> fetchAllJokes() {
        logger.info("Entered JokesService.fetchAllJokes()");
        List<Jokes> allJokes = jokesDAO.findAll();
        List<String> onlyAllJokes = allJokes.stream()
                .map(Jokes::getJoke)
                .toList();

        logger.info("Exited JokesService.fetchAllJokes()");
        return onlyAllJokes;
    }

    private List<String> getOneRandomJoke(List<String> onlyAllJokes) {
        logger.info("Entered JokesService.getOneRandomJoke()");
        List<String> randomJoke = new ArrayList<>();
        // Check if the list is not empty
        if (onlyAllJokes != null && !onlyAllJokes.isEmpty()) {
            // Create an instance of the Random class
            Random random = new Random();

            // Generate a random index within the size of the list
            int randomIndex = random.nextInt(onlyAllJokes.size());

            logger.info("Exited JokesService.getOneRandomJoke():: randomJoke = {}", onlyAllJokes.get(randomIndex));
            randomJoke.add(onlyAllJokes.get(randomIndex));
            return randomJoke;
        }
        logger.info("Exited JokesService.getOneRandomJoke():: empty List");
        return randomJoke;
    }

    /**
     * joke from List of all Jokes of JokesDAO
     * @return ResponseEntity of String
     */
    public ResponseEntity<List<String>> getOneJoke() {
        logger.info("Entered ::JokesService.getOneJokes()");
        try {
            List<String> allJokes = fetchAllJokes();
            List<String> joke = getOneRandomJoke(allJokes);
            logger.info("Exited ::JokesService.getOneJokes()");
            return new ResponseEntity<>(joke, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("JokesService.getOneJoke() threw an Exception");
            e.printStackTrace();
        }
        logger.info("Exited ::JokesService.getOneJokes():: with error");
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
     * this method is for built-in 268 general jokes
     * @return result of insertion in DB
     */
    public String insertJokes() {
        List<Jokes> jokesList = new ArrayList<>();

        for (String builtInJoke : JokesList.jokes) {
            Jokes joke = new Jokes();
            joke.setJType("general");
            joke.setJoke(builtInJoke);
            jokesList.add(joke);
        }

        jokesDAO.saveAll(jokesList);
        return "success";
    }
}
