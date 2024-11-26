#!/bin/bash

# This probably wont work on windows.
# This is basically a helper program to remove all the old
# clasess, crate them from scratch and if everything 
# succeeded, run the game. 


rm -rf ./*.class

file="Score.java"
javac $file
if [ $? -ne 0 ]; then
    echo "ERROR: failed to compile $file"
    exit 1
fi

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