# Setup & Guidelines

### Android Studio Setup

#### Importing Project

#### Building Gradle Dependencies

[Read more](/contributing/pr-checklist#android) about PR Checklist for Android Application Code

### PyTorch Setup

#### Virtual Environment
Follow the steps to replicate the environment for generating torchscript modules locally/online.
* Create a Python 3.6+ virtual environment by running `python3 -m venv torchexpo`
* Activate the environment by running `source bin/torchexpo`
* Change the directory to `models` and run `pip install -r requirements` to install all dependencies

#### Running Conversion Scripts
Currently, all conversion scripts (notebooks) are ran on CPU to avoid any device inconsistency after conversion for Android.  
You can spin up the jupyter notebook as its done usually and run the required cells to create the outputs in the same directory.


### Common Issues & Solutions
* [Gradle Error & Its Solution]()
* [PyTorch XYZ Error & Its Solution]()
* [Add More Error & Its Solution]()
* [Add More Error & Its Solution]()