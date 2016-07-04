#!/bin/bash
kafkaPort=$1
echo $1
java -jar ./../target/crazy.cricket-consumer.jar $1 &
java -jar ./../target/crazy.cricket-driver.jar &
