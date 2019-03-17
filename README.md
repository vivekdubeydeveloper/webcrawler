1)Download project from GIT following url
https://github.com/vivekdubeydeveloper/webcrawler
2)Unzip the project.
3)Open eclipse File ->Import->Maven->Exisiting Maven Projects->Give path of POM File->click on finish button
4)Check eclipse JDK version should be 1.8 and compiler version also 1.8
5)Go to src->main-resource folder open log4j.properties ,Give correct path to property log4j.appender.file.File
6)Go to src->main-resource folder open application.properties ,Give correct path to property output.file.absolute.path,here final output would be generated
7)Right click on project run as-Maven clean install(build should be successful),ensure maven dependency has been downloaded completly.
8)Click on Eclipse project menu then click on clean.
9)Go to package com.code.webcrawler and run the class WebCrawlerApp as java application,it will ask for url.Give the below URL as input
http://www.prudential.co.uk

.After the completion see the output json in output folder of step 6.It will process 5 links.

10)For complete process Go to src->main-resource folder open application.properties change the value max.noofpage.crawl=0.Repeat step 9.

11)I have covered general test cases for Webcrawler application.For running test cases Junit jar should be in build path.
Right click on WebCrawlerAppTest and run as Junit test.(For details read comments in class).See result in Junit tab.


Please read application.properties file comments for any extra configuaration.

Possible Improvements(if get more time):
1)I can make it multithreaded so performance would be better.
2)Instead of writing summary once we can write small xml or json which would be  optimize memorywise. 
2)I can sotre data in xml and database which we can use for some analysis or track future changes.
4)Since I have done coding in one day so we can little bit improve coding statndard also i.e. we can store string literals in constants.
