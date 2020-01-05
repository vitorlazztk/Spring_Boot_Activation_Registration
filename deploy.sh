#!/bin/bash
cd /root/java/Spring_Boot_Activation_Registration
echo Leszedem a GIT-ről a legfrisebb verziót
git pull
echo Kezdem a buildelést!
mvn clean install
echo Buildelés vége!
echo jar másolása
mv target/sec-0.1.jar ./
echo Leállítom az aktuális java alkalmazást
pkill java
echo Indítom az új verziót
nohup java -jar sec-0.1.jar &
