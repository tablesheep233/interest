package org.table.aop.api.advisor.introduction.declareparents;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;
import org.table.aop.api.service.ByeService;
import org.table.aop.api.service.ByeServiceImpl;

@Aspect
@Component
public class DeclareParentsAspect {

    @DeclareParents(value = "org.table.aop.api.service.HelloService+", defaultImpl = ByeServiceImpl.class)
    public ByeService byeService;
}
