package com.student.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class StudentService {

    private final MongoTemplate mongoTemplate;

    public StudentService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Insert single document
    public String addOne(String json) {
        Document doc = Document.parse(json);
        mongoTemplate.getCollection("students").insertOne(doc);

        String id = doc.getObjectId("_id").toHexString();

        return id;  
    }

    // Get all documents
    public List<Document> getAll() {
        return mongoTemplate.getCollection("students")
                .find()
                .into(new java.util.ArrayList<>());
    }

    // Upload JSON file containing array of objects
    public int uploadJsonFile(MultipartFile file) {
        try {
           
            String json = new String(file.getBytes(), StandardCharsets.UTF_8);

         
            List<Document> documents =
                    Document.parse("{\"items\":" + json + "}")
                            .getList("items", Document.class);

          
            MongoCollection<Document> collection = mongoTemplate.getCollection("students");
            collection.insertMany(documents);

            return documents.size();

        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON file: " + e.getMessage());
        }
    }
}
