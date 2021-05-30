package com.rest.api.controllers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.rest.api.models.Riddle;
import com.rest.api.utils.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public ResponseEntity<ObjectNode> home() {
		try {
			// Read the CSV file.
			CSVReader csvReader = new CSVReader(new InputStreamReader(Utils.csvFileResource().getInputStream()));

			// Get the total lines count.
			long totalLines = Utils.totalRiddlesCount();

			// Select a random line.
			Random random = new Random();
			int riddleNumber = random.nextInt(Math.toIntExact(totalLines) - 1);

			// Skip until the riddle line.
			csvReader.skip(riddleNumber);

			// Get the riddle line.
			String[] lines = csvReader.readNext();

			csvReader.close();

			// Get the question.
			String question = lines[0];

			// Get the answer of the question.
			String answer = lines[1];

			// Create the Riddle instance.
			Riddle riddle = new Riddle(riddleNumber, question, answer);

			return new ResponseEntity<ObjectNode>(riddle.toObjectNode(), HttpStatus.OK);
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode objectNode = mapper.createObjectNode();
			return new ResponseEntity<ObjectNode>(objectNode, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}