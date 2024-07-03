# A sample NextGen connect Plugin project

A sample [NextGen Connect](https://github.com/nextgenhealthcare/connect) plugin.

This repository is used in [this guide on writing Mirth plugins](https://github.com/kpalang/mirth-plugin-guide).

## Installation
1. [Install Java](https://www.javatpoint.com/javafx-how-to-install-java)
2. [Install Maven](https://www.javatpoint.com/how-to-install-maven)
3. Run `git clone https://github.com/kpalang/mirth-sample-plugin`
4. Navigate to `mirth-sample-plugin/`
5. Run `./build.sh` to verify the build works
6. Try to install the sample plugin by getting the `sampleplugin.zip` archive from your project root
---

## Usage

> [!TIP]
> This repository is best used as a template by clicking the green "Use this template" button in the top right corner.
> Using as a template makes your repository not pick up the changes in the base repo thus avoiding file mismatches. 

**This repository showcases use of [mirth-plugin-maven-plugin-kt](https://github.com/kpalang/mirth-plugin-maven-plugin-kt) to generate `plugin.xml` file!**

- Any external libraries that you might want to use in the plugin at runtime, go into `libs/runtime/{type}`
- Any external libraries that you might want to use at compiletime, go into `libs/compiletime/{type}`

### You can choose between two methods when signing your plugin jarfiles
#### Method 1 - maven-jarsigner-plugin in `pom.xml`
Using the `maven-jarsigner-plugin` requires you uncomment this section [here](./pom.xml:122).
Additionally, you must comment out or remove the [Method 2 section](./build.sh:63) in `build.sh`

#### Method 2 - external script
When using external signing, leave the [section](./pom.xml:122) in `pom.xml` commented or delete
it and comment out or remove the [Method 1 section](./build.sh:52) in `build.sh`

---
# Pull requests are always welcome
