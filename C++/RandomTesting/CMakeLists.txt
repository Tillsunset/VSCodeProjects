cmake_minimum_required(VERSION 3.0.0)
project(RandomTesting VERSION 0.1.0)

# Set compiler flags.
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -pedantic -pedantic-errors -g")

add_executable(RandomTesting main.cpp)
