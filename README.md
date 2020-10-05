# Simple GitHub

## Introduction
&nbsp;
![empty](https://i.ibb.co/w4dSrSN/rsz-screenshot-1601886792.png) &nbsp; &nbsp; ![search](https://i.ibb.co/H2PXVnw/rsz-screenshot-1601886822.png) &nbsp;

Simple GitHub is a demo app which shows list of GitHub users, using the API provided by [GitHub REST API](https://docs.github.com/en/free-pro-team@latest/rest). User can perform search by typing username at the given SearchView. Endless scrolling is also implemented to ensure user can load the next set of users.

The code is fully written in Kotlin.

## Rate Limit & Caching Mechanism
As user remains unauthorized, there is a rate limit where user can only send up to **10 requests per minute**. Considering this limitation, a reliable caching mechanism is implemented where:

* Each request is cached by **query and page**.
* When a request is being cached, it is also given a *time-to-live* period of **5 minutes**.
* If a request has been cached for 5 minutes or more, `UserRepository` will get data from **Network** instead of Local cache.

## Architecture
The project uses **Clean Architecture** design which introduces separation into three different layers: *Data*, *Domain*, and *Presentation/UI Layer*.

UI Layer uses MVP, where the Presenter extends ViewModel, and communicated through a Contract with the View. The Presenter also contains **View State Pattern** in addition of Presenter and View, to greatly reduce numbers of callbacks. Thus, ensuring a single source-of-truth for View (through `render()` method).

## Methodology
This app was developed using BDD approach which abides *Red-Green-Refactor Principle* from TDD. Unit testing was created using both Fakes and Mocks.

## Tech Stack
TMDB app was created using
* **Language**: Kotlin 1.4.10
* **Gradle**: Kotlin DSL
* **Dependency Injenction**: Hilt
* **Http client**: Retrofit
* **Converter & Serializer**: Kotlinx Serialization
* **Async**: Flow (Coroutines)
* **Persistent Storage**: Room
* **Unit Testing**: Mockito (for Stubbing) & Hamcrest (for Assertion)