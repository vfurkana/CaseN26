# N26BC (N26 Blockchain)
N26 Coding challenge app to demonstrate coding skills.

## Challenge Criteria
Must-haves:
- 100% in Kotlin - use Kotlin’s potential to make the code less verbose and more readable.
- Good project structure - well structured packages, files and resources that will scale as the project grows.
- Attention to detail - tidy code that is easy to read, consistent formatting, meaningful naming conventions, no
IDE warnings, no unused code, etc.
- Architecture design that promotes SOLID principles.
- Dependency injection.
- <strike> All code containing logic should be tested.</strike> (Not Complete)
- <strike> Test suite must include instrumentation tests.</strike> (Missing)
- Efficient and responsive layout design.
- <strike>Gracefully handled loading/error states.</strike> (Not Complete)

Good-to-haves:
- Good UX and well polished UI
- Multi-modular project structure
- <strike>CI Integration (e.g run tests, build apk)</strike> Missing
----
### Stack
- Kotlin
- Kotlin DSL
- Android Dynamic Features
- Dependency injection with Hilt + Dagger2.
- MVVM Pattern
- Coroutines & Flow
- Retrofit, OkHttp3, Gson
- Android Architecture Components: ViewModel, LiveData, Lifecycle.
- Material Components
- Testing: JUnit, Mockito, Truth
- MPAndroidChart.

#### Building instructions
No specific instructions. Apk is on top level directory of the project.

#### Area of improvement
Navigation between the dynamic features are handled poorly with reflection. Better solutions should be applied.

All dynamic features are install-time but on-demand approach can be easily implemented.

Providing Retrofit and SharedPref. to modules create dependency on Android and Retrofit library in the module. Could be avoided by providing interfaces instead.

Only unit tests are present. And it's only for single feature. Could be expanded to project together with instrumentation tests.

Dagger2 implementation could be simplifed.

And tons of others. At the end of the day It's Android always room for deprecating new stuff.


#### Personal takeaways
Would not involve KotlinDSL in my projects for a long while. Kotlin DSL's incompability with other libraries and searching web for workarounds here and there didn't only cost me tons of time and it was exhausting to struggle with gradle scripts.

Android Dynamic Features may not worth the effort yet.

### Acknowledgments
During the development of this challenge I gained invaluable knowledge from the articles and open source projects from people listed below:
- https://proandroiddev.com/sharing-build-logic-with-kotlin-dsl-203274f73013
- https://medium.com/android-dev-hacks/kotlin-dsl-gradle-scripts-in-android-made-easy-b8e2991e2ba
- https://pedrookawa.medium.com/clean-architecture-with-dynamic-features-and-hilt-dagger2-pt-1-d1be41a44b12
https://medium.com/insiden26/practical-example-using-reactive-clean-architecture-approach-8a2436ea76b4
- https://github.com/MojRoid/memes
- https://robinhood.engineering/android-inter-module-navigation-with-dagger-b808c4579067
- https://proandroiddev.com/navigation-with-dynamic-feature-modules-48ee7645488
- https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055