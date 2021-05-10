# N26Blockchain
N26 Coding challenge app to demonstrate coding skills.



### Following technologies are used to fulfill expectations:
- Kotlin programming language and kotlin android extensions.
- MVVM Pattern
- Dependency injection with Dagger2.
- RxKotlin
- Retrofit, OkHttp3
- Bitrise for CI
* Android Architecture Components: ViewModel, LiveData, Lifecycle.
* Reactiveness: RxJava2
* Dependency injection: Dagger 2.
* Network: Retrofit2, OkHttp3.
* Testing: JUnit, MockK, MockWebServer, Espresso.
* And: Gson, ConstraintLayout, Options, MPAndroidChart.

###
![N26Coin](https://user-images.githubusercontent.com/16627604/57197241-54681c80-6f65-11e9-8b9d-3046b845a322.png)

Download the App clicking on this link: http://www.gaelmarhic.com/n26coin/N26Coin.apk

During the development of this challenge I gained invaluable knowledge from the articles and open source projects from people listed below:
https://proandroiddev.com/sharing-build-logic-with-kotlin-dsl-203274f73013
https://medium.com/android-dev-hacks/kotlin-dsl-gradle-scripts-in-android-made-easy-b8e2991e2ba
https://pedrookawa.medium.com/clean-architecture-with-dynamic-features-and-hilt-dagger2-pt-1-d1be41a44b12
https://github.com/MojRoid/memes
https://medium.com/insiden26/practical-example-using-reactive-clean-architecture-approach-8a2436ea76b4
https://robinhood.engineering/android-inter-module-navigation-with-dagger-b808c4579067
https://proandroiddev.com/navigation-with-dynamic-feature-modules-48ee7645488
https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055
Other References:
https://developer.android.com/training/dependency-injection/hilt-multi-module

//Notes
//instead of passing retrofit and shared preferences to other modules if we pass interface and implement it in the app, other modules won't be dependent on android or retrofit since they already depend on app where interface lies.
//Kotlin DSL and Dagger didn't leave any possible way to use data and domain libraries as java library thus they had to be implemented as com.android.library