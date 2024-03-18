package org.arep;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.*;

import static spark.Spark.*;

public class LogService {

    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/logservice", (req, res) -> {
            String val = req.queryParams("message");
            return logMessage(val);
        });
    }
    private static final String MONGO_URI = "mongo";
    private static final String DATABASE_NAME = "mydb";
    private static final String COLLECTION_NAME = "log";

    public static String logMessage(String val) {
        try (MongoClient client = new MongoClient(MONGO_URI,27017)) {
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document doc = new Document("message", val)
                    .append("timestamp", new Date());
            collection.insertOne(doc);

            FindIterable<Document> cursor = collection.find()
                    .sort(Sorts.descending("timestamp"))
                    .limit(10);

            List<Map<String, Object>> logList = new ArrayList<>();
            for (Document document : cursor) {
                Map<String, Object> logEntry = new HashMap<>();
                logEntry.put("message", document.getString("message"));
                logEntry.put("timestamp", document.getDate("timestamp"));
                logList.add(logEntry);
            }

            Gson gson = new Gson();
            String jsonResponse = gson.toJson(logList);

            return jsonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al registrar el log en MongoDB: " + e.getMessage();
        }
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }
}
