package dev.otherdevopsgene.favorites;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoritesController {

  @Value("${favorites.fruits}")
  private String fruits;

  @Value("${favorites.color}")
  private String color;

  @Value("${favorites.name}")
  private String name;

  @GetMapping("/")
  public String favorites() {
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a 'on' MMMM d, yyyy'.'");
    final LocalDateTime now = LocalDateTime.now();

    final StringBuilder message = new StringBuilder("Hello, World! The current time is ");
    message.append(dtf.format(now)).append("\n\n");
    message.append("My name is ").append(name).append('\n');
    message.append("My favorite fruits are ").append(fruits).append('\n');
    message.append("My favorite color is ").append(color).append('\n');

    return message.toString();
  }
}
