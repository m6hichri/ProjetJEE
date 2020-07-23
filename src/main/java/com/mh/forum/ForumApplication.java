package com.mh.forum;

import com.mh.forum.user.dao.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ForumApplication {

    @Autowired
    MongoUserRepository mongoUserRepository;

   /* @Autowired
    PasswordEncoder passwordEncoder;*/

    public static void main(String[] args) {

        SpringApplication.run(ForumApplication.class, args);
    }


    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        //  replace ** with the url of the front-side one deployed on azure
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

/*
    @Override
    public void run(String... args) throws Exception {
        if(!userRepository.existsById("5e94f0996d3c33263a489edb")) {
        //if(!userRepository.existsById("5e94f0996d3c33263a489edb")) {
            String hashPassword = passwordEncoder.encode("mouna");
            User admin = User.builder()
                    .email("mouna@gmail")
                    .password(hashPassword)
                    .firstName("Mouna")
                    .lastName("HICHRI")
                    .build();
            userRepository.save(admin);
        }
    }
*/

//    @Bean
//    CommandLineRunner runner(CategoryRepository categoryRepository) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                categoryRepository.deleteAll();
//                categoryRepository.saveAll(
//                        List.of(
//                                new Category("Science"),
//                                new Category("Space"),
//                                new Category("Technologie")
//                        )
//                );
//
//            }
//        };
//    }
}
