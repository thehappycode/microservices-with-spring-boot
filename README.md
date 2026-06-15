# microservices-with-spring-boot

## CLI

### Build

```gradlew
$./gradlew build -x test;
$./gradlew :microservices:product-service:build -x test;
```

### Run

> java -jar microservices/product-service/build/libs/\*.jar &

### Kill port

> kill -9 $(lsof -ti:<port>)

### Refresh dependencies

> ./gradlew clean build --refresh-dependencies
