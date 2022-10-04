# Spring Boot & Kotlin API Template

<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->

[![All Contributors](https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->


## About
This is a template project for those who intent to start developing an API using Kotlin, Spring Boot and some initial configurations such as lint rules, logging with opentelemetry and swagger for API documentation.

### Requirements

### Kotlin + Spring Boot
- [Kotlin](https://kotlinlang.org/)
- [Spring Boot](https://spring.io/quickstart)
- [SDKMan](https://sdkman.io/usage) - necessary only to run it on your machine instead of container 

## Running

### with docker

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

Run on your terminal: 

HTTPS:

```sh
git clone https://github.com/ksetoue/spring-api-template.git
````

- [How to clone a repository](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository)
- [Cloning a repo with SSH](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)
- [Github CLI](https://cli.github.com/manual/)

### Running in development mode
1. Open your terminal or console
2. test the `docker ps` command 
3. go to the project folder then run:
   ```sh
   $ docker-compose up -d
   # newer versions of docker engine:
   $ docker compose up -d
    `````

### 

### Accessing 
- Home endpoint will be available on http://localhost:8084/

- To access swagger go to http://localhost:8084/api-docs/swagger-ui/index.html#/

--------------------
## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://ksetoue.github.io/"><img src="https://avatars.githubusercontent.com/u/13456109?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Karoline Setoue</b></sub></a><br /><a href="https://github.com/ksetoue/spring-api-template/commits?author=ksetoue" title="Code">💻</a> <a href="https://github.com/ksetoue/spring-api-template/commits?author=ksetoue" title="Documentation">📖</a></td>
</tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
