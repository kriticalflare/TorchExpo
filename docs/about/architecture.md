# Architecture

### Android
TODO: Update before v1.2.0 release i.e. Kotlin rewrite with MVVM architecture

#### Project Structure
* [ui](https://github.com/prabhuomkar/TorchExpo/blob/master/app/src/main/java/io/github/prabhuomkar/flare/ui) - 
Contains UI related code like activities and fragments
  * [activities](https://github.com/prabhuomkar/TorchExpo/blob/master/app/src/main/java/io/github/prabhuomkar/flare/ui/activities) - 
  Contains all android activities created
  * [adapters](https://github.com/prabhuomkar/TorchExpo/blob/master/app/src/main/java/io/github/prabhuomkar/flare/ui/adapters) - 
  Contains all list adapters
  * [fragments](https://github.com/prabhuomkar/TorchExpo/blob/master/app/src/main/java/io/github/prabhuomkar/flare/ui/fragments) - 
  Contains all android fragments used
  * [playground](https://github.com/prabhuomkar/TorchExpo/blob/master/app/src/main/java/io/github/prabhuomkar/flare/ui/playground) - 
  Contains code where UI, datasets and everything comes together for action
* [engine](https://github.com/prabhuomkar/TorchExpo/blob/master/app/src/main/java/io/github/prabhuomkar/flare/engine) - 
Contains main inferencing functions and target classes for several tasks
* [helpers](https://github.com/prabhuomkar/TorchExpo/blob/master/app/src/main/java/io/github/prabhuomkar/flare/helpers) - 
Contains constants, helper functions for actions related to UI, Fragments, etc.
* [models](https://github.com/prabhuomkar/TorchExpo/blob/master/app/src/main/java/io/github/prabhuomkar/flare/models) - 
Contains data models for several lists, fragments to display on the UI

#### Release Process
Android application is also released manually by maintainer currently and changelog can be found [here](https://prabhuomkar.github.io/TorchExpo/#/changelog)   
Reach out to [Omkar Prabhu](mailto:prabhuomkar@pm.me) if you have any queries, report any issue, etc.    
Feel free to send a PR for [Issue #2](https://github.com/prabhuomkar/TorchExpo/issues/2) - Automating the process of PlayStore release 

### PyTorch Models
TODO: Update before v1.1.0 release

#### Project Structure
* [models](models/) - Contains scripts and models for converting into torchscript modules
  * [>task_name<](models/image_classification) - Contains all models specific to this **task**
  * [outputs](models/outputs) - Contains all output generated using scripts. These are uploaded on Dropbox

#### Release Process
Models are usually not versioned, but they are released manually.  
Feel free to send a PR for automating the process of model creation and uploading on Dropbox.