package com.dove.mongodb;

import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.internal.MongoClientImpl;
import org.bson.Document;

import java.util.Arrays;

public class MongoClientTest {
    public static void main(String[] args) {

        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");

        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> personCollection = database.getCollection("person");

        FindIterable<Document> documents = personCollection.find();

        for (Document document : documents) {
            System.out.println(document);
        }

        /*Document doc = new Document("name", "dove")
                .append("age", 18)
                .append("hobby", Arrays.asList("swim", "java", "palying"))
                .append("info", new Document("high", 171).append("weigh", 102));

        personCollection.insertOne(doc);*/
        mongoClient.close();
    }
}
