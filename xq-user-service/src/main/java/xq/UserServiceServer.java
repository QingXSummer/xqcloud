package xq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserServiceServer {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceServer.class, args);
    }
}