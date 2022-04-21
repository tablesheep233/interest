package org.table.aop.api.service;

import org.springframework.stereotype.Component;

@Component
public class ByeServiceImpl implements ByeService {
    @Override
    public void bye() {
        System.out.println("bye bye");
    }
}
