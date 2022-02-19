package org.table.flink.base.source;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class CustomSourceApp {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.addSource(new ExceptionSource()).map(new MapFunction<ExceptionMsg, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(ExceptionMsg value) throws Exception {
                return Tuple2.of(value.getType(), 1);
            }
        }).keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
            @Override
            public String getKey(Tuple2<String, Integer> value) throws Exception {
                return value.f0;
            }
        }).sum(1).print();

        env.execute(CustomSourceApp.class.getSimpleName());
    }
}
