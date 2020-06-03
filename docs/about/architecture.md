# Architecture

### Android

#### Project Structure
TODO: Android Project Directory Structure

#### Release Process
Android application is also released manually by maintainer currently and changelog can be found [here](https://prabhuomkar.github.io/TorchExpo/#/changelog)   
Reach out to [Omkar Prabhu](mailto:prabhuomkar@pm.me) if you have any queries, report any issue, etc.    
Feel free to send a PR for [Issue #2](https://github.com/prabhuomkar/TorchExpo/issues/2) - Automating the process of PlayStore release 

### PyTorch Models
* [models](models/) - Contains notebooks for generating torchscript modules. All the notebooks are specific to tasks and compiled all together during the release.  
Output files _(`.pt` files)_ are uploaded to Dropbox manually during the application release process.

#### Release Process
Models are usually not versioned, but they are released manually.  
Feel free to send a PR for automating the process of model creation and uploading on Dropbox.