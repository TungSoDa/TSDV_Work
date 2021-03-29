package com.tsdv.demospringboot;

public interface GirlRepository {
    // TODO: search Girl from DB with name
    Girl getGirlByName(String name);
}
