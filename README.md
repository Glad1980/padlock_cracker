# Padlock Cracker

This project provides a solution to crack a metaphorical padlock using various connector implementations. The padlock has several features, including a numpad where each button is used exactly once, and expensive write operations to the input buffer. The project aims to provide a modular design to allow for future extensions, such as connecting the padlock using TCP sockets, RESTful APIs, or CLI programs.

## Features

- The solution is designed to be easily extendable with different types of connectors.
- Java Object Connector, A connector implementation that communicates with the padlock as a Java object.
- RESTful API Connector that communicates with the padlock via an RESTful API, is ready to be added.
- TCP Connectort communicates with the padlock via an TCP sockets, is ready to be added.
- To add a new connector, you should code a new connector that implements the IPadlockConnector Interface and pass it as a parameter to IPadlockCracker. The PadlockCracker implementation should not be modified.
- Efficiency: The solution attempts to minimize the cost of changing the input buffer by checking for the nearest permutation used,
   best memory write for each padlock solved with size s, permutations n = s!, then (n * 2) + (n - 2) memory write for the worst case
     example, size = 4, then s! = 24, then memory write = 24 * 2 + 2 = 50
- Since the Numpad size had the same permutations, a Permutations Cache (local cache) was implemented to increase performance.

## Project Structure

- `src/main/java/com/cleverthis/interview/padlock/PadlockImpl.java`: The padlock implementation.
- `src/main/java/com/cleverthis/interview/connect/PadlockObjectConnector.java`: The Java object connector.
- `src/main/java/com/cleverthis/interview/connect/PadlockRestConnector.java`: The RESTful API connector.
- `src/main/java/com/cleverthis/interview/connect/PadlockTcpConnector.java`: The TCP connector.
- `src/main/java/com/cleverthis/interview/crack/PadlockCracker.java`: The padlock cracker implementation.
- `src/main/java/com/cleverthis/interview/crack/PermutationCache.java`: To Cache the Permutations for used numpad size.
- `src/main/java/com/cleverthis/interview/crack/IPadlockCracker.java`: The padlock cracker interface.
- `src/main/java/com/cleverthis/interview/Solution.java`: The solution class to solve the padlock using different connectors.
- `src/test/java/com/cleverthis/interview/SolutionTest.java`: Unit tests for the solution.

## Usage

### Running the Solution

To run the solution with the Java object connector, use the class Solution.java with the proper connector, Currently, Only a simple Java Object connector was implemented.


## Build

This repo uses Gradle to build and test.
There is a unit test boilerplate and a gradle task configured to
automatically test and evaluate the code when you push your commits.

The `SolutionTestBase` is an abstract class for other tests.
It tests the correctness of your solution and don't care about the run time.
See `SolutionTest` for how to use that.

The `PerformanceAnalyze` is not a unit test, but it do analyze roughly how
fast your solution is. You need to fill in the `solve` method before you run it.

Use `./gradlew test` to run all unit test configured in the project,
and use `./gradlew runPerformanceTest` to get an analysis.

> Note: You don't have to have a local gradle installation.
> The `gradlew` script will download one for you.
> Just install a valid jdk (version >= 8) and very thing should be fine.

# padlock_cracker
This is an padlock cracker project
# padlock_cracker
This is an padlock cracker project
