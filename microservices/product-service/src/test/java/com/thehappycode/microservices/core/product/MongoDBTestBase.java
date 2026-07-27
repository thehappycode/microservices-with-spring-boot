package com.thehappycode.microservices.core.product;

import java.time.Duration;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class MongoDBTestBase {

    @Container
    @ServiceConnection
    static MongoDBContainer database = new MongoDBContainer("mongo:6.0.4")
            .withStartupTimeout(Duration.ofSeconds(300));
}
