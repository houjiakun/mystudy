package com.study.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MongodbTest {
    public static void main(String[] args) {
 final String uriString = "mongodb://192.168.61.133:27017/test";
        MongoClientURI uri = new MongoClientURI(uriString);
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase mongoDB = mongoClient.getDatabase("test");

        MongoCollection<org.bson.Document> collection = mongoDB.getCollection("user");
        Document user1 = new Document("name", "zhangsan").append("age",18).append("createDate", new Date());
        Document user2 = new Document("name", "lisi").append("age",19).append("createDate", new Date());
        Document user3 = new Document("name", "wangwu").append("age",20).append("createDate", new Date());
        List<Document> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);

        collection.insertMany(list);
        Bson bson = new Document("name", "1");
        Bson bson1 = new Document("age", "21");
        collection.updateOne(bson, bson1);



        MongoClient mongoClient1 = new MongoClient( "192.168.61.133" , 27017 );

        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient1.getDatabase("test");
        MongoCollection<Document> collection1 = mongoDatabase.getCollection("user");

        Filters.all("name","lisi");
           collection1.updateMany(Filters.all("name","li", "si"), new Document("$set", new Document("age",60)));

        FindIterable<Document> documents = collection1.find();
        for (Document document : documents) {
            System.out.println(document.get("name") + "" + document.get("age"));
        }

        FindIterable<Document> documents1 = collection1.find(Filters.eq("name", "lisi"));
        for (Document document : documents1) {
            System.out.println(document.get("name") + "" + document.get("age"));
        }

        List<Integer> list1 = Arrays.asList(1,2,3);
        Integer integer = list1.stream().reduce(Integer::sum).get();
        System.out.println(integer);

    }



}
