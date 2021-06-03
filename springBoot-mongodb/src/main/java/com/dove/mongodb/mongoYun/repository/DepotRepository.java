package com.dove.mongodb.mongoYun.repository;

import com.dove.mongodb.mongoYun.entity.OFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class DepotRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public String putFile(OFile oFile) {
        return putFile(oFile, null);

    }

    public String putFile(OFile oFile, String alias) {
        return "";

    }
}
