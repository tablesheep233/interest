package org.table.ioc.nounique.priority;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.table.ioc.nounique.base.OssService;

import javax.annotation.Priority;


@Service
@Priority(2)
@Slf4j
public class PriorityLocalOssService implements OssService {
    @Override
    public void upload(Object object) {
        log.info("priority local oss upload");
    }
}
