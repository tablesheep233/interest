package org.table.ioc.nounique.priority;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.table.ioc.nounique.base.OssService;

import javax.annotation.Priority;


@Service
@Priority(1)
@Slf4j
public class PriorityMinioOssService implements OssService {
    @Override
    public void upload(Object object) {
        log.info("priority minio oss upload");
    }
}
