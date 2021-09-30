# Tutorial: Java Records - A WebFlux and Spring Data Example

This repository contains all the code for the WebClient tutorial, illustrating how to mock authorization and how to do integration testing for code that uses WebClient for accessing third-party services.

**Prerequisites**:
- [Java 14+](https://openjdk.java.net/install/index.html)
- [Okta CLI](https://cli.okta.com)

## Getting Started

To install this example, run the following commands:
```bash
git clone https://github.com/indiepopart/java-records.git
```

## Configure Okta Authentication

```shell
cd java-records
```

With OktaCLI, register for a free developer account:

```shell
okta register
```
Provide the required information. Once you complete the registration, create a client application with the following command:

```shell
okta apps create
```
You will be prompted to select the following options:

- Application name: java-records
- Type of Application: Web
- Type of Application: Okta Spring Boot Starter
- Redirect URI: Default
- Post Logout Redirect URI: Default

The OktaCLI will create the client application and configure the issuer, clientId and clientSecret in `src/main/resources/application.properties`. Update the `issuer`, `client-id` and `client-secret` in `application.yml`. Delete `application.properties`.

```yml
okta:
  oauth2:
    issuer: https://{yourOktaDomain}/oauth2/default
    client-id: {clientId}
    client-secret: {clientSecret}
```

## Build and Run the Application Tests

```shell
./mvnw clean install
```
