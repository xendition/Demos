package com.michael.demo.jdk.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * JMH 测试样例
 * <pre>
 *      BenchmarkMode - JMH进行测试时使用的模式
 *      OutputTimeUnit - 测试结果使用的时间单位
 *      Warmup - 测试前的预热配置
 *      Threads - 测试时使用多少个线程
 *      Measurement - 度量值
 *  </pre>
 *
 * @author Michael
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Threads(20)
@Measurement(iterations = 10, time = 5)
public class JmhDemo2 {


    private static AtomicLong count = new AtomicLong();
    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhDemo2.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void AtomicLongTest() {
        count.getAndIncrement();
    }

    @Benchmark
    public void LongAdderTest() {
        longAdder.increment();
    }
}
