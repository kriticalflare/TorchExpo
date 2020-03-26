<h1 align="center">
 <img src="art/pytorch_logo.png" width="50%">
</h1>
<p align="center">
 Android application with collection of machine learning experiments using PyTorch Android API
 <br />
 <br />
 <img src="https://img.shields.io/github/license/prabhuomkar/Flare?style=flat-square"
    alt="License">
 <img src="https://img.shields.io/badge/pytorch_android-1.4-%23ee4c2c?style=flat-square"
    alt="PyTorch Android">
</p>

<!-- Table of Contents !-->
* [About PyTorch Mobile](#about-pytorch-mobile)
  * [PyTorch Android API](#pytorch-android-api)
* [About](#about)
  * [Built With](#built-with)
  * [Project Structure](#project-structure)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Contributing](#contributing)
* [License](#license)

## About PyTorch Mobile
PyTorch Mobile is an experiment release that provides APIs to build an end-to-end workflow
from Python to deployment on iOS and Android. _[Read more](https://pytorch.org/mobile/home/)_

<p align="center">
	<img src="art/flow.jpg" width="75%">
</p>

### PyTorch Android API
PyTorch Mobile provides Java APIs for integration of Machine Learning models built with Python
into an Android application. It is as simple as importing and using any other Android library. You
can add PyTorch's Gradle dependencies and can run inferences.
_[Read more](https://pytorch.org/mobile/android/)_

## About
Flare is an android application which contains collection of machine learning experiments
built using PyTorch and its Android API. This application will allow you to try tasks like Image
Classification with State-Of-The-Art models _(like AlexNet, ResNet, etc.)_.

### Built With
* [PyTorch](https://pytorch.org) & [PyTorch Android API](https://pytorch.org/mobile/android/)

### Project Structure
* [ui](app/src/main/java/io/github/prabhuomkar/flare/ui) - 
Contains UI related code like activities and fragments
  * [activities](app/src/main/java/io/github/prabhuomkar/flare/ui/activities) - 
  Contains all android activities created
  * [adapters](app/src/main/java/io/github/prabhuomkar/flare/ui/adapters) - 
  Contains all list adapters
  * [fragments](app/src/main/java/io/github/prabhuomkar/flare/ui/fragments) - 
  Contains all android fragments used
* [datasets](app/src/main/java/io/github/prabhuomkar/flare/datasets) - 
Contains classifier functions and target classes for datasets
* [helpers](app/src/main/java/io/github/prabhuomkar/flare/helpers) - 
Contains constants, helper functions for actions related to UI, Fragments, etc.
* [models](app/src/main/java/io/github/prabhuomkar/flare/models) - 
Contains data models for several lists, fragments to display on the UI
* [playground](app/src/main/java/io/github/prabhuomkar/flare/playground) - 
Contains code where UI, datasets and everything comes together for action

## Getting Started

### Prerequisites
* [Python 3](https://www.python.org/download/releases/3.0/)
* [Java 1.8](https://www.java.com/en/download/)
* [Android Studio](https://developer.android.com/studio)

### Installation
* Git clone this/forked repository
```shell script
git clone git@github.com:prabhuomkar/Flare.git
OR
git clone git@github.com:<GITHUB_USERNAME>/Flare.git
```
* For Android Stuff:
  * Open this project with **Android Studio**
  * Build the project by installing Gradle dependencies
* For Machine Learning Stuff:
  * Install the Python dependencies:
    ```shell script
    cd models
    pip install -r requirements.txt
    ```
## Usage
* For downloading and converting pre-trained models into torchscript module:
```shell script
cd models
python <model_name>.py
```

## Contributing
We appreciate all contributions. If you are planning to contribute back bug-fixes, please do so
without any further discussion.

If you plan to contribute new features, utility functions or any application upgrades, please first
open an issue and discuss the feature with us. Sending a PR without discussion might end up
resulting in a rejected PR, because we might be taking the project in a different direction
than you might be aware of.

Please refer our [Contribution Guide](CONTRIBUTING.md) for more details.

## License
