cmake_minimum_required(VERSION 3.9)
project(project1)
include(ExternalProject)
find_package(Threads)
find_package(Git QUIET)

# Set compiler flags.
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -pedantic -pedantic-errors -g")

add_executable(main HireMe.C)
