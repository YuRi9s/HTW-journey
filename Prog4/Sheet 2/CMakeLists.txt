cmake_minimum_required(VERSION 3.28)
project(untitled2)

set(CMAKE_CXX_STANDARD 26)

add_executable(untitled2 main.cpp
        Article.h
        Article.cpp
        Storehouse.h
        Storehouse.cpp
        City.h
        City.cpp)
