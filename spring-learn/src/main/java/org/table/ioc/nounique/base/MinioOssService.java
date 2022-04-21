package org.table.ioc.nounique.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("minio")
@Slf4j
public class MinioOssService implements OssService {
    @Override
    public void upload(Object object) {
        log.info("minio oss upload");
    }
}
