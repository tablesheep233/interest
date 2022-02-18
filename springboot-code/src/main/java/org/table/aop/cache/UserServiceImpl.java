package org.table.aop.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Cacheable(cacheNames = "USER", key = "#id")
    @Override
    public User load(Long id) {
        log.info("load user " + id);
        User user = new User();
        user.setName(RandomStringUtils.randomAlphanumeric(3));
        return user;
    }
}
