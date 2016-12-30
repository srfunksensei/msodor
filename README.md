# msodor

Simple application used for reading MS Office documents. 

## Getting started

Idea behind the application lays in faster extraction of information from MS Office document(s) that follow certain pattern structure. Ideally application should be used for extraction of meaningful information from large data sets.

## Prerequisites

1. Java 8
2. Maven 3.3 (or higher)

## How to run application

To be able to see the application in action you must follow these steps:

1. run `mvn clean install package`
2. run `java -jar msodor-<version> <file-name>`

  * current `version` is 0.0.1
  * `file-name` is the name of the file which you wish to process

For demo purposes you can use the following [document](http://zagreb.arhiv.hr/hr/hda/kinoteka/Hrvatski_DGM.doc) without Table of Content.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.