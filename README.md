# NumSort
This project provides:
1. **RandGen**: a uitility to generate random numbers. 
2. **NumSort**: a test class which benchmarks the time it takes to read a file containing random numbers, sort them and then store the sorted list in an output file. This is part of testing I am doing to compare language performance. I have added similar projects for rust and haskell. 

To compile the project run 

```mvn clean install```

To run **RanGen**: 

```java -classpath target/NumSort-1.0-SNAPSHOT.jar org.aha.RandGen 1000000```

It will generate a file in tmp folder containing random numbers. 

To run **NumSort**: 

```java -classpath NumSort-1.0-SNAPSHOT.jar org.aha.NumSort /tmp/10000000.random```

