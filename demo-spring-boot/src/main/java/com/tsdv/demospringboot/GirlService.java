package com.tsdv.demospringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GirlService {
    @Autowired
    private GirlRepository girlRepository;

    public Girl getRandomGirl() {
        // Random một string có độ dài quy định, sử dụng thư viện Apache Common Lang
        String name = RandomStringUtils.randomAlphanumeric(10).toLowerCase();
        return girlRepository.getGirlByName(name);
    }
}
