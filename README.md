# Vk Bot

#### Requires:

* [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
* [Docker](https://www.docker.com/) (Optional)

### Bot`s work example
*   [Link](https://vk.com/club226084398)


### How to run application

#### 1. Clone repository

> `git clone https://github.com/isljes/VkBot.git`

#### 2. Setup VK API properties

> 1. Open `src/main/resources/vk.properties`
> 2. Specify params gotten from VK
>
>
> | Variable name   | Description         |
> |-----------------|---------------------|
> | vk.access-token | VK API access token |
> | vk.group-id     | Group`s ID          |
>
>  Guides :
>   * [Getting VK access token](https://vk.com/@vksoftred-kak-poluchit-token-soobschestva-vkontakte)
>   * [Getting VK groupID](https://vk.com/faq18062)

#### 3. Build Jar

> `mvn clean package`

#### 4. Run Application

You have two ways to run this application:

* with java
* with Docker

##### Way 1. Run with java

> `java -jar target/VkBot.jar`

##### Way 2. Run with Docker

> ###### 1. Build docker image
> ` docker build -t vkBot .`
> ###### 2. Run docker container
> ` docker run --name=vkBot -d -p 8090:8090 vkBot`
`

### Test case

Необходимо выполнить интеграцию с [BotAPI VK.](https://vk.com/dev/bots_docs)

В рамках задания нужно создать бота который будет цитировать присланный ему текст. Пример взаимодействия с подобным
ботом см. на картинке:


> **Person:**  Привет
>
> **Bot:** Вы сказали:  Привет
>
> **Person:**  как дела
>
> **Bot:** Вы сказали: как дела

##### Требования к реализации

В качестве решения хотелось бы получить ссылку на git репозиторий в котором находятся исходники Spring Boot приложения
выполняющего логику бота.
Все параметры необходимые для корректного запуска и проверки должны задаваться в конфигурационных файлах (необходимо
решить какие именно параметры).
Все сущности с помощью которых осуществляется взаимодействие должны быть представлены в виде POJO для java и data class
для kotlin.
В readme должен быть описан процесс запуска приложения и необходимые параметры конфигурации.
Качество кода и выбранная внутренняя структура компонентов/сервисов также оценивается.
Использование языка Kotlin будет преимуществом (но не является обязательным).

**Важно! Нельзя использовать готовые библиотеки-реализации VkApi для Java.**

При реализации может потребоваться использование внешних https адресов для локальной машины. Для этого можно
использовать [ngrok](https://ngrok.com/).

