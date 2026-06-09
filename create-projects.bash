#!/usr/bin/env bash

mkdir microservices
cd microservices

spring init \
--boot-version=4.0.6 \
--type=gradle-project \
--java-version=21 \
--packaging=jar \
--name=product-service \
--package-name=com.thehappycode.microservices.core.product \
--groupId=com.thehappycode.microservices.core.product \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
product-service

spring init \
--boot-version=4.0.6 \
--type=gradle-project \
--java-version=21 \
--packaging=jar \
--name=review-service \
--package-name=com.thehappycode.microservices.core.review \
--groupId=com.thehappycode.microservices.core.review \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
review-service

spring init \
--boot-version=4.0.6 \
--type=gradle-project \
--java-version=21 \
--packaging=jar \
--name=recommendation-service \
--package-name=com.thehappycode.microservices.core.recommendation \
--groupId=com.thehappycode.microservices.core.recommendation \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
recommendation-service

spring init \
--boot-version=4.0.6 \
--type=gradle-project \
--java-version=21 \
--packaging=jar \
--name=product-composite-service \
--package-name=com.thehappycode.microservices.composite.product \
--groupId=com.thehappycode.microservices.composite.product \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
product-composite-service

spring init \
--boot-version=4.0.6 \
--type=gradle-project \
--java-version=21 \
--packaging=jar \
--name=api \
--package-name=com.thehappycode.api \
--groupId=com.thehappycode.api \
--dependencies=webflux \
--version=1.0.0-SNAPSHOT \
api

cd ..