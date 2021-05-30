package com.restapp.api.model;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repo) {
    return args -> {
      log.info("Preloading " + repo.save(new User("Caique", "Dev")));
      log.info("Preloading " + repo.save(new User("Guto", "Intern")));
    };
  }

}
