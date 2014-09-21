# Project Cost Manager

## Descritpion

Cost Manager is going to be an application to track your costs and expenses. Make a weekly, monthly analysis of these, find your biggest expenses, and so on!

## Used technologies:
### Backend
- Maven
- Spring
- Spring Data MongoDB
- Elasticsearch(in progress)
- JAX RS with Jersey

### Frontend
- Angular JS
- Bootstrap CSS

## Modules:
### client
Frontend of the application.
### common
Common package for the modules
### core
Core functions for the modules like embedded MongoDB, ElasticSearch, test helpers
### data-services
DAO for saving and retrieving objects from MongoDB through Spring Data
### web-services
RESTful web interface for the client
### integration-test
Integration tests for the application that runs with Maven Jetty plugin and uses embedded MongoDB




