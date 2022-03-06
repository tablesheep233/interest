package org.table.ioc.nounique.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("local")
@Slf4j
public class LocalOssService implements OssService {

    @Override
    public void upload(Object object) {
        log.info("local oss upload");
    }
}
