package com.dove.mongodb;

import com.google.gson.internal.$Gson$Preconditions;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;

public class MorphiaTest {
    public static void main(String[] args) {
       final Morphia morphia = new Morphia();
        Datastore datastore = morphia.createDatastore(new MongoClient("127.0.0.1",27017), "test");

        Person person = new Person();
        person.setName("morphiaTest");
        person.setAge("30");
        person.setPhone("1111122222");

        Key<Person> saveid = datastore.save(person);
        System.out.println(saveid.getId());

        DBCollection collection = datastore.getCollection(Person.class);

        DBCursor dbObjects = collection.find();

        for (DBObject dbObject : dbObjects) {
            System.out.println(dbObject);
        }

    }
}
