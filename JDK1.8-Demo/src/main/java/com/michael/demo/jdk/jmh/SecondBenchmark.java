// package com.michael.demo.jdk.jmh;
//
// import org.openjdk.jmh.annotations.*;
// import org.openjdk.jmh.runner.Runner;
// import org.openjdk.jmh.runner.RunnerException;
// import org.openjdk.jmh.runner.options.Options;
// import org.openjdk.jmh.runner.options.OptionsBuilder;
//
// import java.util.concurrent.TimeUnit;
//
// @BenchmarkMode(Mode.All)
// @OutputTimeUnit(TimeUnit.MILLISECONDS)
// @State(Scope.Benchmark)
// public class SecondBenchmark {
//     @Param({"100000"})
//     private int length;
//
//     private int[] numbers;
//     private Calculator singleThreadCalc;
//     private Calculator multiThreadCalc;
//
//     public static void main(String[] args) throws RunnerException {
//         Options opt = new OptionsBuilder()
//                 .include(SecondBenchmark.class.getSimpleName()) // .include("JMHF.*") 可支持正则
//                 .forks(0)
//                 .warmupIterations(2)
//                 .measurementIterations(2).threads(10)
//                 .build();
//
//         new Runner(opt).run();
//     }
//
//     @Benchmark
//     public long singleThreadBench() {
//         return this.singleThreadCalc.sum(this.numbers);
//     }
//
//     @Benchmark
//     public long multiThreadBench() {
//         return this.multiThreadCalc.sum(this.numbers);
//     }
//
//     @Setup(Level.Trial)
//     public void prepare() {
//         int n = this.length;
//         this.numbers = new int[n];
//         for (int i = 0; i < n; i++) {
//             this.numbers[i] = i;
//         }
//         this.singleThreadCalc = new SinglethreadCalculator();
//         this.multiThreadCalc = new MultithreadCalculator(Runtime.getRuntime().availableProcessors());
//     }
//
//
//     @TearDown
//     public void shutdown() {
//         this.singleThreadCalc.shutdown();
//         this.multiThreadCalc.shutdown();
//     }
// }