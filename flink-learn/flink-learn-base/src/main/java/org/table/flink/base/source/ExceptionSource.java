package org.table.flink.base.source;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExceptionSource implements SourceFunction<ExceptionMsg> {

    private volatile AtomicBoolean running;

    public ExceptionSource() {
        this.running = new AtomicBoolean(true);
    }

    @Override
    public void run(SourceContext<ExceptionMsg> ctx) throws Exception {
        while (running.get()) {
            ctx.collect(ExceptionMsg.Factory.generate());
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100, 500));
        }
    }

    @Override
    public void cancel() {
        running.set(false);
    }
}
