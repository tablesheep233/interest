package org.table.flink.base.source;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class ExceptionMsg implements Serializable {
    private static final long serialVersionUID = -6849794470754667710L;

    private String type;

    private String msg;

    public static final class Factory {

        private static final List<String> types = List.of("IllegalArgument", "BadRequest", "DataNotFound");

        public static ExceptionMsg generate() {
            ExceptionMsg msg = new ExceptionMsg();
            msg.type = types.get(ThreadLocalRandom.current().nextInt(0, 3));
            msg.msg = RandomStringUtils.randomAlphabetic(6);
            return msg;
        }
    }
}
