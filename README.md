# Distributed E-Commerce Microservices Platform

A production-ready, cloud-native microservices platform built with Java 21, Spring Boot 3, and Spring Cloud. Designed with event-driven architecture, database-per-service isolation, and enterprise security standards.

---

## 🏗 System Architecture & Module Map

```text
ecommerce-platform/                (Maven Parent POM)
├── user-service/                  (Port: 8081 | DB: postgres-user)
├── product-service/               (Port: 8082 | DB: postgres-product)
├── cart-service/                  (Port: 8083 | DB: redis-cart)
├── order-service/                 (Port: 8084 | DB: postgres-order)
├── payment-service/               (Port: 8085 | Sandboxed Integration)
├── notification-service/          (Port: 8086 | Async Consumer)
├── api-gateway/                   (Port: 8080 | Spring Cloud Gateway)
└── discovery-server/              (Port: 8761 | Netflix Eureka)