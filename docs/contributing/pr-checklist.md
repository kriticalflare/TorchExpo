# Pull Request Checklist

### Android
* You have linted the code using gradle `ktlintCheck` task and used the `ktlintFormat` task to format the code according to [kotlin style guide](https://github.com/pinterest/ktlint#standard-rules).
It is recommended that you install the Git pre-commit hook if you are on Linux/Mac OS to ensure all code being pushed builds and adheres to the code style guidelines. Use the Gradle `addKtlintCheckGitPreCommitHook` task to install the git pre-commit hook.
* TODO: Process followed as given in Getting Started section for Task/Model
* TODO: Model hosted on Dropbox and url shortener link is available
* TODO: UI cross-check and build check

### PyTorch Models
* Your `model` is added in the appropriate `<task_name>.ipynb`. If the `<task_name>.ipynb` doesn't exist, you have created a new notebook with `snake_case` naming convention.
* You have tested your model output file locally and attached a `GitHub gist` or `Google Colab Notebook` link of the working demo in the PR.
* You have added the entry of your model in `docs/about/models.md` under appropriate table and section.
