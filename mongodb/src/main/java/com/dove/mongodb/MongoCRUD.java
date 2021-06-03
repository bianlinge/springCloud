package com.dove.mongodb;

import com.google.gson.internal.$Gson$Preconditions;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

public class MongoCRUD {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");

        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> personCollection = database.getCollection("person");
        //findAll
        FindIterable<Document> documents = personCollection.find();
        for (Document document : documents) {
            System.out.println(document.toJson());
        }

        MongoCursor<Document> iterator = documents.iterator();
        System.out.println("============================");
        while (iterator.hasNext()) {
            Document next = iterator.next();
            System.out.println(next);
        }

        long l = personCollection.countDocuments();
        System.out.println("总数： "+l);

        //条件查询
        BasicDBObject bsonParam = new BasicDBObject();
        bsonParam.append("name", "AAAAA");
        FindIterable<Document> people = personCollection.find(bsonParam);

        for (Document person : people) {
            System.out.println(person);
        }



       /* Bson filterbson = Filters.eq("phone", "111111111112222222222");

        FindIterable<Document> documents1 = personCollection.find(filterbson);
        for (Document document : documents1) {
            System.out.println(document);
        }

        Document document = new Document();
        document.put("phone","111111111112222222222");
        personCollection.replaceOne(filterbson, document);
        mongoClient.close();*/

        Document document = new Document();
        Document document1 = new Document();
        document1.put("name","tom");
        document.put("$set",document1);

        Bson phone = Filters.eq("phone", "111111111112222222222");
        personCollection.updateOne(phone,document);
    }


}
