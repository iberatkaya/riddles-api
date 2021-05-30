package com.rest.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Riddle {

  /**
   * The riddle question;
   */
  final String question;

  /**
   * The answer of the riddle question.
   */
  final String answer;

  private int id;

  public Riddle(int id, String question, String answer) {
    this.id = id;
    this.question = question;
    this.answer = answer;
  }

  /**
   * @return The id.
   */
  public int getId() {
    return id;
  }

  /**
   * @return The riddle question.
   */
  public String getQuestion() {
    return question;
  }

  /**
   * @return The answer of the riddle.
   */
  public String getAnswer() {
    return answer;
  }

  @Override
  public String toString() {
    return "{" + " question='" + getQuestion() + "'" + ", answer='" + getAnswer() + "'" + ", id='" + getId() + "'"
        + "}";
  }

  /**
   * Get the Riddle as an ObjectNode to send easily as a response.
   * 
   * @return The Riddle as an ObjectNode.
   */
  public ObjectNode toObjectNode() {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode objectNode = mapper.createObjectNode();

    objectNode.put("id", id);
    objectNode.put("question", question);
    objectNode.put("answer", answer);

    return objectNode;
  }
}
