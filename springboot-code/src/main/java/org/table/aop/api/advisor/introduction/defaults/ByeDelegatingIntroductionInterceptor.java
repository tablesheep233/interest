package org.table.aop.api.advisor.introduction.defaults;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.table.aop.api.service.ByeService;

public class ByeDelegatingIntroductionInterceptor extends DelegatingIntroductionInterceptor implements ByeService {
    @Override
    public void bye() {
        System.out.println("bye bye");
    }
}
