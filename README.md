[![Build Status](https://travis-ci.org/kasirgalabs/ETUmulator.svg?branch=master)](https://travis-ci.org/kasirgalabs/ETUmulator)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/b79a64268c3b4ab38699a5780e773302)](https://www.codacy.com/app/RootG/ETUmulator?utm_source=github.com&utm_medium=referral&utm_content=kasirgalabs/ETUmulator&utm_campaign=Badge_Coverage)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b79a64268c3b4ab38699a5780e773302)](https://www.codacy.com/app/RootG/ETUmulator?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=kasirgalabs/ETUmulator&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://www.versioneye.com/user/projects/58b1886d7b9e15004de85395/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/58b1886d7b9e15004de85395)

# ETUmulator
ETUmulator is a Thumb-2 assembly language emulator written in Java. It is written with portability in mind, with builds actively maintained for Linux. At this moment, ETUmulator only emulates a subset of Thumb-2 instruction set.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
We use [Gradle](https://gradle.org/) to automate just about everything. As long as you have a [Java SE Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) version **8u60** or above running, you are well on your way to get a development environment.<br/>
<br/>
*Since we have already included a Gradle wrapper in the repository, installing Gradle is an optional process.*

### Installing

###### For linux:
Open a terminal by pressing "CTRL + ALT + T"<br/>
From your terminal, change the current directory on your local machine where you want to get a development environment.
Example to change current directory to Desktop: 
```
cd ~/Desktop
```
then clone the repository if you have [Git](https://git-scm.com/):
```
git clone https://github.com/kasirgalabs/ETUmulator.git
```
or [download](https://github.com/kasirgalabs/ETUmulator/archive/master.zip) zip file contaning the repository and extract it to your Desktop.
<br/>
After above steps you should have `ETUmulator` or `ETUmulator-master` directory under your Desktop.
Execute below commands:<br/>
```
cd ETUmulator
```
or
```
cd ETUmulator-master
```
then
```
./gradlew build -x test
```
**That's really it!**<br/>
<br/>
If you want the project set up in NetBeans (our favored IDE), just install [NetBeans Gradle](http://plugins.netbeans.org/plugin/44510/gradle-support) plugin and open ETUmulator from NetBeans. Then you get a bunch of run configurations and other stuff for free!

### Running
Execute the below command:
```
./gradlew jar
```
then you will be able to run ETUmulator from your terminal by typing `java -jar build/libs/ETUmulator.jar`<br/>
or navigate to `build/libs` directory and double click on the `ETUmulator`

### Running the tests
Open a terminal in the root of the project directory, then execute the command:
```
./gradlew check
```
