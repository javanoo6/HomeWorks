package ru.javanoo6.Lesson_2.Web_Part.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMoveException extends RuntimeException {
  public  InvalidMoveException(String message) {super(message);}
}
