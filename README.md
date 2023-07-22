# A sample NextGen connect Plugin project

A sample [NextGen Connect](https://github.com/nextgenhealthcare/connect) plugin.

This repository is used in [this guide on writing Mirth plugins](https://github.com/kpalang/mirth-plugin-guide).

---

## Installation
1. [Install Java](https://www.javatpoint.com/javafx-how-to-install-java)
1. [Install Maven](https://www.javatpoint.com/how-to-install-maven)
1. Run `git clone https://github.com/kpalang/mirth-sample-plugin`
1. Navigate to `mirth-sample-plugin/`
1. Run `mvn install` to install dependencies to local cache
1. Run `mvn clean package` to verify the build works
1. Try to install the sample plugin by getting the `.zip` archive from `mirth-sample-plugin/distribution/target`
---

## Usage

Use this repository as a base to develop your own plugins.

**This repository showcases use of [mirth-plugin-maven-plugin](https://github.com/kpalang/mirth-plugin-maven-plugin) to generate `plugin.xml` file!**

- Any external libraries that you might want to use in the plugin at runtime, go into `libs/runtime/{type}`
- Any external libraries that you might want to use at compiletime, go into `libs/compiletime/{type}`

---
## TODO
* More simplification

# Pull requests are always welcome
