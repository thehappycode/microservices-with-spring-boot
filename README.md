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

### CURL

❯ curl http://localhost:8989/product-composite/1 -s | jq .

### Docker

#### Start docker 

> sudo systemctl enable --now docker
> systemctl enable --now docker

#### Xoá hết tất cả container.

> docker rm -f $(docker ps -aq)

#### Hạn chế tài nguyên

Do JVM cấp phát tài nguyên (CPU/RAM) hệ thống dựa vào dung lượng tài nguyên khả dụng của container.
Do đó khi Java cố gắng cấp phát bộ nhớ cho containers nhiều hơn mức cho phép của máy chủ Docker (host), nó thông báo lỗi 'out of memory'.
Điều này cũng tương tự với CPU.

##### Hạn chế core CPU

Kiểm tra số core hiện tại

> echo 'Runtime.getRuntime().availableProcessors()' | docker run --rm -i eclipse-temurin:21 jshell -q

Cấp phát 3CPUS

> echo 'Runtime.getRuntime().availableProcessors()' | docker run --rm -i --cpus=3 eclipse-temurin:21 jshell -q

##### Hạn chế RAM

Java mặc cấp phát 1/4 bộ nhớ vào HEAP.

Kiểm tra RAM

> docker run -it --rm eclipse-temurin:21 java -XX:+PrintFlagsFinal | grep "size_t MaxHeapSize"

Cấp phát 1GB RAM và 600m bộ nhớ HEAP.

> docker run -it --rm -m=1024M eclipse-temurin:21 java -Xmx600m -XX:+PrintFlagsFinal -version | grep "size_t MaxHeapSize"

Test

> echo 'new byte[100_000_000]' | docker run -i --rm -m=1024M eclipse-temurin:21 jshell -q

> echo 'new byte[500_000_000]' | docker run -i --rm -m=1024M eclipse-temurin:21 jshell -q

#### Docker build

> docker build -f ./microservices/product-service/Dockerfile -t product-service ./microservices/product-service

#### Find images

> docker images | grep product-service

#### Docker run

> docker run -d --rm -p8080:8080 -e "SPRING_PROFILES_ACTIVE=docker" --name prd-srv product-service

#### Docker view log

> docker logs prd-srv -f --tail 0

> docker log prd-srv -f --since 5m

#### Docker remove

> docker rm -f my-prd-srv

#### Docker compose build

> docker-compose build;
> docker-compose build --no-cache

#### Docker compose up

> docker-compose up -d

#### Docker compose logs;

> docker logs -f <container>
> docker-compose logs -f
> docker-compose logs -f --tail 0
> docker-compose logs -f --since 5
