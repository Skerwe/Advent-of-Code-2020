# Advent of Code 2020

My attempt at the Advent of Code for 2020 using Java

```text
              _                 _            __    _____          _        ___   ___ ___   ___
     /\      | |               | |          / _|  / ____|        | |      |__ \ / _ \__ \ / _ \
    /  \   __| |_   _____ _ __ | |_    ___ | |_  | |     ___   __| | ___     ) | | | | ) | | | |
   / /\ \ / _` \ \ / / _ \ '_ \| __|  / _ \|  _| | |    / _ \ / _` |/ _ \   / /| | | |/ /| | | |
  / ____ \ (_| |\ V /  __/ | | | |_  | (_) | |   | |___| (_) | (_| |  __/  / /_| |_| / /_| |_| |
 /_/    \_\__,_| \_/ \___|_| |_|\__|  \___/|_|    \_____\___/ \__,_|\___| |____|\___/____|\___/

```

![Made with Java](https://forthebadge.com/images/badges/made-with-java.svg)
![GitHub](https://img.shields.io/github/license/Skerwe/Advent-of-Code-2021?style=for-the-badge)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for testing purposes.

### Prerequisites

You will need Java 11 or higher installed and configured on your system PATH.  
It's best to use the [OpenJDK][openjdk], Windows users can get binaries from [AdoptOpenJDK][adoptopenjdk].

### Installing

Clone the repository:  
`git clone https://github.com/Skerwe/Advent-of-Code-2020.git`

1. In bash/terminal/command line, cd into the project directory:  
    `cd Advent-of-Code-2020`
2. Compile the application:  
   `gradlew compileJava`
3. Execute the application by passing in the full name of the main class to run:
   1. To run Day 15: `gradlew -PmainClass=za.web.skerwe.adventofcode2020.Day15 run`
   2. To run Day 16: `gradlew -PmainClass=za.web.skerwe.adventofcode2020.Day16 run`

The final output of the challenge is printed to the console or the log file for the specific day under the *logs* folder.

### Running Tests

1. Compile the tests:  
    `gradlew compileTestJava`
2. Execute the tests:  
    `gradlew test`

## This project was built with

- [Java](https://www.java.com/en/) programming language
- [Gradle][gradle] build tool
- [JUnit Jupiter][junit] (JUnit 5) testing framework
- [Apache Log4j 2](https://logging.apache.org/log4j/2.x/) logging framework

## License

The source code is free -- see the [LICENSE](LICENSE) file for details

[openjdk]: https://openjdk.java.net/
[adoptopenjdk]: https://adoptopenjdk.net/
[gradle]: https://gradle.org/
[junit]:  https://junit.org/junit5/
