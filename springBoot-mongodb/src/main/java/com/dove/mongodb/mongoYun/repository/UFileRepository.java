package com.dove.mongodb.mongoYun.repository;

import com.dove.mongodb.mongoYun.entity.UFile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UFileRepository extends MongoRepository<UFile, String> {
    Optional<UFile> findById(String id);
}
