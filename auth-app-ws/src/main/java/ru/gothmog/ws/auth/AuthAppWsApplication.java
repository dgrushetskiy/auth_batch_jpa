package ru.gothmog.ws.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.gothmog.ws.auth.config.batch.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class AuthAppWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthAppWsApplication.class, args);
    }

}
