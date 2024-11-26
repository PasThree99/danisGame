#!/bin/bash

rm -rf ./*.class

# files=$(ls -la | grep .java | awk '{print $9}')

# for file in $files; do 
#     javac $file
#     if [ $? -ne 0 ]; then
#         echo "ERROR: failed to compile $file"
#         exit 1
#     fi
# done


file="Objective.java"
javac $file
if [ $? -ne 0 ]; then
    echo "ERROR: failed to compile $file"
    exit 1
fi

file="Selector.java"
javac $file
if [ $? -ne 0 ]; then
    echo "ERROR: failed to compile $file"
    exit 1
fi

file="Block.java"
javac $file
if [ $? -ne 0 ]; then
    echo "ERROR: failed to compile $file"
    exit 1
fi

file="GamePanel.java"
javac $file
if [ $? -ne 0 ]; then
    echo "ERROR: failed to compile $file"
    exit 1
fi

file="GameFrame.java"
javac $file
if [ $? -ne 0 ]; then
    echo "ERROR: failed to compile $file"
    exit 1
fi

file="Game.java"
javac $file
if [ $? -ne 0 ]; then
    echo "ERROR: failed to compile $file"
    exit 1
fi



java Game