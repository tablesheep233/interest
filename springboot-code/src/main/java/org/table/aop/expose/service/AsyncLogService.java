package org.table.aop.expose.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.table.aop.expose.model.Log;
import org.table.aop.expose.repository.LogRepository;

@Service
public class AsyncLogService implements ILogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void collect(Log log) {
        ((ILogService)AopContext.currentProxy()).asyncSave(log);
    }

    @Async
    @Override
    public void asyncSave(Log log) {
        logRepository.save(log);
    }
}
