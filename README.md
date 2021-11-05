# Java Records - A WebFlux and Spring Data Example

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

## Getting Started

To install this example, run the following commands:

```bash
git clone https://github.com/oktadev/okta-java-records-example.git
```

## Configure Okta Authentication

```shell
cd java-records
```

With Okta CLI, register for a free developer account:

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

## Build and Run the Application Tests

```shell
./mvnw verify
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
