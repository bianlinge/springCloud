package com.dove.mongodb.mongobase.mongoRepository;

import com.dove.mongodb.mongobase.common.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by E1041 on 2020/3/9.
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
	//方法的命名规则要遵守
	List<Person> findByName(String name);
	Optional<Person> findPersonByNameAndSex(String name, String sex);
}
