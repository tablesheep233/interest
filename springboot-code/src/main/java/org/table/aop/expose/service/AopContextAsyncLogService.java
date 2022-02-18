package org.table.aop.expose.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.table.aop.expose.model.Log;
import org.table.aop.expose.repository.LogRepository;

@Service
public class AopContextAsyncLogService implements ILogService {

    @Autowired
    private LogRepository logRepository;

    @Async
    @Override
    public void collect(Log log) {
        ((ILogService) AopContext.currentProxy()).save(log);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Log log) {
        logRepository.save(log);
    }
}
