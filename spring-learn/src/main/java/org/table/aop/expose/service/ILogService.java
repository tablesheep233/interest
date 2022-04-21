package org.table.aop.expose.service;

import org.table.aop.expose.model.Log;

public interface ILogService {

    void collect(Log log);

    default void save(Log log) {
    }

    default void asyncSave(Log log) {
    }
}
