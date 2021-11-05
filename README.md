# Tutorial: Java Records - A WebFlux and Spring Data example

This repository contains all the code for the WebClient tutorial, illustrating how to mock authorization and how to do integration testing for code that uses WebClient for accessing third-party services.

**Prerequisites**:
- [Java 14+](https://openjdk.java.net/install/index.html)
- [Okta CLI](https://cli.okta.com)
- [Docker](https://docs.docker.com/engine/install/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Getting started

To install this example, run the following commands:
```bash
git clone https://github.com/indiepopart/java-records.git
```

## Configure Okta authentication

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

## Build and run the application tests

```shell
./mvnw clean install
```


## Run with Docker Compose

Go to the docker folder and run the services with Docker Compose:

```shell
cd docker
docker-compose up
```

Once the services are up, go to http://localhost:8080/mentalStateAverageDamage, and you should see the Okta login page. Sign in with your Okta credentials, and if successful, it will redirect to the /mentalStateAverageDamage endpoint, and you should see a response body like the following:


```json
[
   {
      "mentalState":"sober",
      "damageToPlayers":604.3777777777777,
      "damageToStructures":3373.511111111111,
      "damageTaken":246.46666666666667
   },
   {
      "mentalState":"high",
      "damageToPlayers":557.547619047619,
      "damageToStructures":2953.8571428571427,
      "damageTaken":241.71428571428572
   }
]
```

