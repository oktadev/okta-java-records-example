# Java Records - A WebFlux and Spring Data Example

This repository contains all the code for the Java Records tutorial, illustrating how records can be used for building REST APIs and database queries.

This repository contains the completed code for [Java Records: A WebFlux and Spring Data Example](http://developer.okta.com/blog/2021/11/05/java-records). Please read the blog post to see how it was created.

**Prerequisites**:

- [HTTPie](https://httpie.io/)
- [Java 14+](https://openjdk.java.net/install/index.html)
- [Okta CLI](https://cli.okta.com)
- [Docker](https://docs.docker.com/engine/install/)
- [Docker Compose](https://docs.docker.com/compose/install/)

> [Okta](https://developer.okta.com/) has Authentication and User Management APIs that reduce development time with instant-on, scalable user infrastructure. Okta's intuitive API and expert support make it easy for developers to authenticate, manage, and secure users and roles in any application.

* [Getting Started](#getting-started)
* [Links](#links)
* [Help](#help)
* [License](#license)

## Getting started

To install this example, first clone this repository:

```bash
git clone https://github.com/oktadev/okta-java-records-example.git
```

## Configure Okta authentication

```shell
cd java-records
```

Using the Okta CLI, register for a free developer account:

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

The Okta CLI will create the client application and configure the issuer, clientId and clientSecret in `src/main/resources/application.properties`. Update the `issuer`, `client-id` and `client-secret` in `application.yml`. Delete `application.properties`.

```yml
okta:
  oauth2:
    issuer: https://{yourOktaDomain}/oauth2/default
    client-id: {clientId}
    client-secret: {clientSecret}
```

## Run with Docker Compose

In the project root, generate the application container image with the following Maven command:

```shell
./mvnw spring-boot:build-image
```

Go to the docker folder and run the services with Docker Compose:

```shell
cd docker
docker-compose up
```

Once the services are up, go to `http://localhost:8080/mentalStateAverageDamage`, and you should see the Okta login page. Sign in with your Okta credentials, and if successful, it will redirect to the /mentalStateAverageDamage endpoint, and you should see a response body like the following:


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

## Links

This example uses the following open source libraries:

* [Open JDK](https://openjdk.java.net/)
* [Okta Spring Boot Starter](https://github.com/okta/okta-spring-boot)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data](https://spring.io/projects/spring-data)
* [MongoDB](https://www.mongodb.com/)

## Help

Please post any questions as comments on the [blog post](http://developer.okta.com/blog/2021/11/05/java-records), or visit our [Okta Developer Forums](https://devforum.okta.com/).

## License

Apache 2.0, see [LICENSE](LICENSE).
