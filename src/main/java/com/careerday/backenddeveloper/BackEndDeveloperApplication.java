package com.careerday.backenddeveloper;

import com.careerday.backenddeveloper.accounts.support.Account;
import com.careerday.backenddeveloper.accounts.support.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackEndDeveloperApplication {

    @Autowired
    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackEndDeveloperApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            accountRepository.deleteAll();
            Account apiAuth = new Account();
            apiAuth.setUsername("admin");
            apiAuth.setPassword("1234");
            accountRepository.save(apiAuth);
        };
    }
}
