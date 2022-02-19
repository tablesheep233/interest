package org.table.flink.base.transformation;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoFlatMapFunction;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.util.Collector;

public class TransformationApp {

    public static FlatMapFunction<String, Tuple2<String, Integer>> WORD_SPLIT_FLATMAP_FUNCTION = new FlatMapFunction<String, Tuple2<String, Integer>>() {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            for (String s : value.split(",")) {
                out.collect(Tuple2.of(s, 1));
            }
        }
    };

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//        flatMap(env);
//        reduce(env);
//        connect(env);
        coFlatMap(env);
//        union(env);

        env.execute(TransformationApp.class.getSimpleName());
    }

    private static void flatMap(StreamExecutionEnvironment env) {
        DataStreamSource<String> streamSource = env.addSource(new WordSource());

        streamSource.flatMap(WORD_SPLIT_FLATMAP_FUNCTION).print();
    }

    private static void reduce(StreamExecutionEnvironment env) {
        DataStreamSource<String> streamSource = env.addSource(new WordSource());

        streamSource.flatMap(WORD_SPLIT_FLATMAP_FUNCTION).keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
            @Override
            public String getKey(Tuple2<String, Integer> value) throws Exception {
                return value.f0;
            }
        }).reduce(new ReduceFunction<Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {
                return Tuple2.of(value1.f0, value1.f1 + value2.f1);
            }
        }).print();
    }

    private static void connect(StreamExecutionEnvironment env) {
        DataStreamSource<String> source1 = env.addSource(new WordSource(new String[]{"a", "b", "c"}));
        DataStreamSource<String> source2 = env.addSource(new WordSource(new String[]{"1", "2", "3"}));

        source1.flatMap(WORD_SPLIT_FLATMAP_FUNCTION).connect(source2).map(new CoMapFunction<Tuple2<String, Integer>, String, String>() {
            @Override
            public String map1(Tuple2<String, Integer> value) throws Exception {
                return value.toString();
            }

            @Override
            public String map2(String value) throws Exception {
                return value;
            }
        }).print();
    }

    private static void coFlatMap(StreamExecutionEnvironment env) {
        DataStreamSource<String> source1 = env.addSource(new WordSource(new String[]{"a", "b", "c"}));
        DataStreamSource<String> source2 = env.addSource(new WordSource(new String[]{"1", "2", "3"}));

        source1.flatMap(WORD_SPLIT_FLATMAP_FUNCTION).connect(source2).flatMap(new CoFlatMapFunction<Tuple2<String, Integer>, String, String>() {
            @Override
            public void flatMap1(Tuple2<String, Integer> value, Collector<String> out) throws Exception {
                out.collect(String.valueOf(value.f1));
            }

            @Override
            public void flatMap2(String value, Collector<String> out) throws Exception {
                out.collect(value);
            }
        }).print();
    }

    private static void union(StreamExecutionEnvironment env) {
        DataStreamSource<String> source1 = env.addSource(new WordSource(new String[]{"a", "b", "c"}));
        DataStreamSource<String> source2 = env.addSource(new WordSource(new String[]{"1", "2", "3"}));

        //error
//        source1.flatMap(WORD_SPLIT_FLATMAP_FUNCTION).union(source2).print();
        source1.union(source2).print();

    }


}
