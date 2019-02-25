1)Download project from GIT following url
https://github.com/vivekdubeydeveloper/webcrawler
2)Unzip the project.
3)Open eclipse File ->Import->Maven->Exisiting Maven Projects->Give path of POM File->click on finish button
4)Check eclipse JDK version should be 1.8 and compiler version also 1.8
5)Go to src->main-resource folder open log4j.properties ,Give correct path to property log4j.appender.file.File
6)Go to src->main-resource folder open application.properties ,Give correct path to property output.file.absolute.path,here final output would be generated
7)Right click on project run as-Maven clean install(build should be successful)
8)Click on Eclipse project menu then click on clean.
9)Go to package com.code.webcrawler and run the class WebCrawlerApp as java application,it will ask for url.Give the below URL as input
http://www.prudential.co.uk

.After the completion see the output json in output folder of step 6.It will process 5 links.

10)For complete process Go to src->main-resource folder open application.properties change the value max.noofpage.crawl=0.Repeat step 9.

Please read application.properties file comments for any extra configuaration.
