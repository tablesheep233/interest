package org.table.flink.base.sink;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * create table word_count
 * (
 *     word varchar(10) not null primary key,
 *     frequency int default 0 not null
 * );
 *
 * @author: tablesheep
 */
public class MySQLSink extends RichSinkFunction<Tuple2<String, Integer>> {

    private Connection connection;
    private PreparedStatement stat;

    @Override
    public void open(Configuration parameters) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database", "username", "password");
        stat = connection.prepareStatement("INSERT INTO word_count (word, frequency) VALUES (?,?) " +
                "ON DUPLICATE KEY UPDATE frequency = ?");
    }

    @Override
    public void invoke(Tuple2<String, Integer> value, Context context) throws Exception {
        stat.setString(1, value.f0);
        stat.setInt(2, value.f1);
        stat.setInt(3, value.f1);
        stat.execute();
    }

    @Override
    public void close() throws Exception {
        if (stat != null) {
            stat.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
