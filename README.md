# Kitties

### Initial configuration
Make a copy of the `api.properties.example` file in the project root and change the name to `api.properties`.
Then copy the API Key and place it between the `""`. 
A key can be generated from the website: https://thecatapi.com/

## Structure of the Application

The application follows a modular organization structure. The main reason this organization was implemented was to ensure scalability, ecapsulation, testability and to improve compilation times.

This organization by modules followed the guidelines mentioned in the Android documentation
https://developer.android.com/topic/modularization .

### Build
In order to simplify the build.gradle.kts files of the different modules, a number of Gradle Plugins have been created which can be found in the `build-logic` module.
Through these plugins it is possible to create a configuration that can be reused by the different configuration files of each module and in this way reuse the configurations and ensure that the configurations of each module are simpler.

https://docs.gradle.org/current/samples/sample_convention_plugins.html
https://github.com/android/nowinandroid/tree/main/build-logic


## Offline First
The application follows an offline first app pattern, the main reason for this choice being to guarantee uninterrupted access to essential features, even when not connected to the Internet. This approach guarantees a more reliable and responsive application, especially in scenarios where network connectivity can be intermittent or unreliable.
https://developer.android.com/topic/architecture/data-layer/offline-first


In addition, the Single Source of Truth (SSO) standard was used to ensure that the data is consistent regardless of whether the application is used online or offline.
https://developer.android.com/topic/architecture#single-source-of-truth



