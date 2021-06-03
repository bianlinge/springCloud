package com.dove.mongodb.mongobase.mongoTemplate;

import com.alibaba.fastjson.JSONObject;
import com.dove.mongodb.mongobase.common.BaseController;
import com.dove.mongodb.mongobase.common.Person;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by E1041 on 2020/3/9.
 */
@RestController
@Api(tags = "MongoDb")
public class MongoTemplateController extends BaseController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/save")
    public JSONObject save(@RequestBody Person person) {
        try {
            mongoTemplate.save(person);
            return isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return isError();
        }

    }

    @PostMapping("/delete")
    public JSONObject delete(@RequestBody Person person) {
        DeleteResult remove = mongoTemplate.remove(person);
        long deletedCount = remove.getDeletedCount();
        if (deletedCount > 0) {
            return isSuccess(person);
        } else {
            return isError();
        }
    }

    @PostMapping("/insert")
    public JSONObject insert(@RequestBody Person person) {
        try {
            mongoTemplate.insert(person);
            return isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return isError();
        }

    }

    @GetMapping("/findById")
    public JSONObject findById(String id) {
        Person byId = mongoTemplate.findById(id, Person.class);
        return isSuccess(byId);
    }

    @GetMapping("/findAll")
    public JSONObject findAll() {
        List<Person> all = mongoTemplate.findAll(Person.class);
        return isSuccess(all);
    }

    @PostMapping("/update")
    public JSONObject update(@RequestBody Person person) {

        Query query = new Query(Criteria.where("_id").is(person.getId()));
        Update update = new Update();
        update.set("name", person.getName());
        update.set("age", person.getAge());
        UpdateResult upsert = mongoTemplate.upsert(query, update, Person.class);
        long modifiedCount = upsert.getModifiedCount();
        if (modifiedCount > 0) {
            return isSuccess();
        } else {
            return isError();
        }

    }
}
