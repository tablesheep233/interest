package org.table.flink.base.transformation;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class WordSource implements SourceFunction<String> {

    private final String[] alphabets;
    private final int size;

    private volatile AtomicBoolean running;

    public WordSource() {
        this.alphabets = new String[]{"a", "b", "c", "d", "e"};
        this.size = alphabets.length;
        this.running = new AtomicBoolean(true);
    }

    public WordSource(String[] alphabets) {
        this.alphabets = alphabets;
        this.size = alphabets.length;
        this.running = new AtomicBoolean(true);
    }

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        while (running.get()) {
            ctx.collect(generate());
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100, 500));
        }
    }

    @Override
    public void cancel() {
        running.set(false);
    }

    private String generate() {
        int c = ThreadLocalRandom.current().nextInt(1, 9);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < c; i++) {
            builder.append(alphabets[ThreadLocalRandom.current().nextInt(0, size)]).append(",");
        }
        return builder.toString();
    }
}
