package com.kaluzny;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * Simple application that shows how to use Azure Cosmos DB with the MongoDB API
 * and Java.
 *
 */
public class Program {

	public static void main(String[] args) {
		/*
		 * Replace connection string from the Azure Cosmos Portal
		 */
		//MongoClientURI uri = new MongoClientURI(
		//		"mongodb://copec:DoiT7RVNZn0Gx6vPAii9Sj8BSrpJGs7OrSJHj6EJ8ngVLBV0871DcZz606GOKkHDox5BGY2ipHlk8mSYqgcaog==@copec.documents.azure.com:10255/?ssl=true&replicaSet=globaldb");

		String user = "copec";
		String database2 = "globaldb";
		char[] password = "copec:DoiT7RVNZn0Gx6vPAii9Sj8BSrpJGs7OrSJHj6EJ8ngVLBV0871DcZz606GOKkHDox5BGY2ipHlk8mSYqgcaog".toCharArray();

		MongoCredential credential = MongoCredential.createCredential(user, database2, password);
		MongoClient mongoClient2 = new MongoClient(new ServerAddress("copec.documents.azure.com", 10255), Arrays.asList(credential));

		//mongocl
		//.getDatabase("admin")
		//.runCommand(new Document("ping", 1));

		
		MongoDatabase db = mongoClient2.getDatabase(database2);
		MongoCollection<Document> collection2 = db.getCollection("names");
		System.out.println("connecting to host....." + mongoClient2);
		System.out.println("connecting to database....." + collection2);

		//MongoClient mongoClient = null;
		try {
			//mongoClient = new MongoClient(uri);

			// Get database
			MongoDatabase database = mongoClient2.getDatabase("db");

			// Get collection
			MongoCollection<Document> collection = database.getCollection("coll");

			// Insert documents
			Document document1 = new Document("fruit", "apple");
			collection.insertOne(document1);

			Document document2 = new Document("fruit", "mango");
			collection.insertOne(document2);

			// Find fruits by name
			Document queryResult = collection.find(Filters.eq("fruit", "apple")).first();
			System.out.println(queryResult.toJson());

			System.out.println("Completed successfully");

		} finally {
			if (mongoClient2 != null) {
				mongoClient2.close();
			}
		}
	}
}
