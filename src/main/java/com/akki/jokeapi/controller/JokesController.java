package com.akki.jokeapi.controller;

import com.akki.jokeapi.entity.Jokes;
import com.akki.jokeapi.service.JokesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JokesController simply accepts following requests:
 * /addJoke, /allJokes, /allJokes{category}
 * @author Akash Prajapati
 * @version 1.0
 */
@RestController
@RequestMapping("joke-api")
public class JokesController {

    @Autowired
    JokesService jokesService;

    /**
     * @apiNote insert a joke in DB
     * @param joke
     * @return result of joke insertion in DB
     */
    @PostMapping("addJoke")
    public String addOneJoke(@RequestBody Jokes joke) {
        return jokesService.insertJoke(joke);
    }

    /**
     * @apiNote insert jokes in DB
     * @param jokes
     * @return result of joke insertion in DB
     */
//    @PostMapping("addJokes")
//    public String addMultipleJokes(@RequestBody List<String> jokes) {
//        return jokesService.insertJokes(jokes);
//    }

    /**
     * @apiNote fetch all the jokes stored in DB
     * @return List of jokes in JSON format
     */
    @CrossOrigin
    @GetMapping(value = "allJokes", produces = {"application/json"})
    public ResponseEntity<List<Jokes>> showAllJokes() {
        return jokesService.getAllJokes();
    }

    /**
     * @apiNote fetch jokes by category such as general, programming etc.
     * @param category
     * @return
     */
    @GetMapping(value = "allJokes/{category}", produces = {"application/json"})
    public ResponseEntity<List<Jokes>> showAllJokesByJType(@PathVariable String category) {
        return jokesService.getAllJokesByJType(category);
    }

}
