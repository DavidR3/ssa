cmake_minimum_required(VERSION 3.6)
project(CplusplusModule)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        CmakeLists.txt test/testgtest.cpp test/testgtest.h)


# Setup testing
enable_testing()


include(${CMAKE_HOME_DIRECTORY}/conanbuildinfo.cmake)
conan_basic_setup(TARGETS)

# Add test cpp file
add_executable( runUnitTests test/testgtest.cpp src/source/Savings.cpp src/include/Savings.h)
# Link test executable against gtest & gtest_main
target_link_libraries(runUnitTests gtest gtest_main ${CONAN_LIBS})
add_test( runUnitTests runUnitTests ${SOURCE_FILES} )

