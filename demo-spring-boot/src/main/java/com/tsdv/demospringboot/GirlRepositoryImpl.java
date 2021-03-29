package com.tsdv.demospringboot;

import org.springframework.stereotype.Repository;

@Repository
public class GirlRepositoryImpl implements GirlRepository{

    @Override
    public Girl getGirlByName(String name) {
        return new Girl(name);
        // thực tế là sẽ phải query với DB
    }
}
