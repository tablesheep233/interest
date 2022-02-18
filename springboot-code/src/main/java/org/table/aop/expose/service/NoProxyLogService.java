package org.table.aop.expose.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.table.aop.expose.model.Log;

@Service
public class NoProxyLogService implements ILogService {
    @Override
    public void collect(Log log) {
        ((ILogService) AopContext.currentProxy()).save(log);
    }

    @Override
    public void save(Log log) {
        System.out.println("save log");;
    }
}
