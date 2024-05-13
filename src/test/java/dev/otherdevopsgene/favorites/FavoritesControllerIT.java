package dev.otherdevopsgene.favorites;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FavoritesControllerIT {

  @Autowired private TestRestTemplate template;

  @Test
  public void getFavorites() throws Exception {
    ResponseEntity<String> response = template.getForEntity("/", String.class);
    assertThat(response.getBody()).startsWith("Hello, World! The current time is");
  }
}
