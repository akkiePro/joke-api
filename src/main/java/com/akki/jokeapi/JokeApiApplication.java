package com.akki.jokeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Akash Prajapati
 * @apiNote main class of joke-api
 */
@SpringBootApplication
public class JokeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokeApiApplication.class, args);
		System.out.println("Joke API started.");
	}

}
