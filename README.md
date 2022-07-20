# COVID19 

**Covid19** is an Android app that shows the latest information about the COVID19 pandemic. It uses modern Android development tools and practices to create a user-friendly and reliable app. This app is for Android developers who want to learn and improve their skills. 📱

## Screenshots
<h4 align="center">
<img src="screens/countries-dark.png" width="25%">
<img src="screens/countries-light.png" width="25%">
<img src="screens/faqs-dark.png" width="25%">
<img src="screens/faqs-light.png" width="25%">
<img src="screens/news-dark.png" width="25%">
<img src="screens/news-light.png" width="25%">
<img src="screens/overview-dark.png" width="25%">
<img src="screens/overview-light.png" width="25%">
<img src="screens/settings-dark.png" width="25%">
<img src="screens/settings-light.png" width="25%">

## Features
- View the **Total COVID19 cases worldwide** from a reliable [API](https://github.com/NovelCOVID/API) source.
- Receive notifications of the total cases every hour using WorkManager.
- Search for COVID19 numbers for any country using Retrofit and Moshi.
- Visualize the statistics on linear charts using LiveData and DataBinding.
- Read COVID19 related news from the W.H.O. and Google News RSS using Coroutines and Flow.
- Access the app offline using Cache.

*It uses `PeriodicWorkManager` which is scheduled at the first run of an app. After that, `Worker` will execute after every one hour of interval and will show notification on Android's system tray.*

## Tech Stack 
- [Kotlin](https://kotlinlang.org/) - The official and first-class programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For managing concurrency and asynchronous tasks.
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- [Koin](https://start.insert-koin.io/) - Dependency Injection Framework (Kotlin)
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - An API that makes it easy to schedule deferrable, asynchronous tasks that are expected to run even if the app exits or device restarts.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android

# Package Structure
     com.elhady.covid19                         # Root Package
    .
    ├── data                                    # For data handling.
    │   ├── model                               # Model classes.
    │   ├── local                               # Local Persistence Database.
    |   ├── remote                              # Remote Data Handlers for remote end point..  
    │   └── repository                          # Single source of data.
    |      
    ├── di                                      # Dependency Injection             
    │   ├── builder                             # Activity Builder
    │   ├── component                           # DI Components       
    │   └── module                              # DI Modules
    |             
    ├── ui                                      # UI
    │   ├── adapter                             # Adapters Package RecyclerView Adapter with ViewHolder
    │   ├── binding                             # BindingAdapter                       
    │   ├── view                                # Activity/View layer
    │   │   ├── countries                       # Country Screen Activity & ViewModel
    |   │   │   ├─ Fragment                     # Fragment
    |   │   │   └─ viewmodel                    # ViewModel for Country Fragmnet
    │   │   ├── faqs                            # Faqs Screen Fragment and ViewModel
    |   │   │   ├─ Fragment                     # Fragment
    |   │   │   └─ viewmodel                    # ViewModel for Faqs Fragmnet 
    │   │   ├── global                          # Global Screen Fragment and ViewModel
    |   │   │   ├─ Fragment                     # Fragment
    |   │   │   └─ viewmodel                    # ViewModel for Global Fragmnet 
    │   │   ├── news                            # News Screen Fragment and ViewModel
    |   │   │   ├─ Fragment                     # Fragment
    |   │   │   └─ viewmodel                    # ViewModel for News Fragmnet 
    │   │   ├── settings                        # Settings Screen Fragment and ViewModel
    |   │   │   ├─ Fragment                     # Fragment
    |   │   │   └─ viewmodel                    # ViewModel for Settings Fragmnet 
    │   │   ├── worker             
    |   │   │   ├─ NotificationWorker           # Worker class
    |   │   │   └─ NotificationWorkerManager    # class manager
    |       
    └── utils                                   # Utility Classes / Kotlin extensions
    
    
## Architecture
This app follows the [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


## Contributing
If you want to contribute to this project, please check the [open issues](https://github.com/islamelhady/Covid19/issues) and feel free to pick something up.

## API Sources
This app relies on these data sources:
 - [Tne Novel Covid API](https://github.com/NovelCOVID/API)
 - [COVID19 News API](https://github.com/einnar82/covid19-news-api) 


## Find this repository useful? 
Support it by joining __[stargazers](https://github.com/islamelhady/covid19/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/islamelhady)__ me for my next creations

**Contributed By:** [islam elhady](https://github.com/islamelhady)

[![Linkedin](https://img.shields.io/badge/-linkedin-grey?logo=linkedin)](https://www.linkedin.com/in/islamelhady/)
