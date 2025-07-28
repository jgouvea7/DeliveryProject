# Delivery

Projeto desenvolvido durante uma imersão com foco em microsserviços, mensageria e boas práticas de arquitetura backend utilizando Java e Spring Boot.

Os microsserviços se comunicam de forma assíncrona via **Apache Kafka**, e o projeto aplica conceitos de **Domain-Driven Design (DDD)**, com separação clara entre camadas de domínio, aplicação e infraestrutura. Também utiliza o **Resilience4j** para garantir tolerância a falhas com **circuit breaker** e **retry**.

> Projeto voltado para estudo e prática de conceitos avançados de backend, como mensageria, resiliência e arquitetura orientada ao domínio.

## Aprendizados

Durante a imersão, explorei conceitos como:

- Arquitetura de microsserviços com comunicação assíncrona
- Implementação de **Circuit Breaker** e **Retry** com Resilience4j
- Organização do código seguindo princípios de **DDD**
- Separação de responsabilidades entre API, domínio e infraestrutura
- Uso de mensageria com Apache Kafka
- Containerização com Docker

## Tecnologias Principais

- Java 21
- Spring Boot
- Apache Kafka
- PostgreSQL
- Docker
- Resilience4j
