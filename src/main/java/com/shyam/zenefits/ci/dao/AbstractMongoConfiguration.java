package com.shyam.zenefits.ci.dao;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;

@Service
public class AbstractMongoConfiguration {

	private static final String DATABASE_NAME = "dbotica";
	private static MongoOperations mongoOperations;
	private static String COLLECTION;

	public AbstractMongoConfiguration() {
		super();
		setMongoOperations(MongoClientFactory.INSTANCE.getClient(), DATABASE_NAME);
	}

	public String getCOLLECTION() {
		return COLLECTION;
	}

	public void setCOLLECTION(String cOLLECTION) {
		COLLECTION = cOLLECTION;
	}

	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public static void setMongoOperations(MongoClient client, String databaseName) {
		try {

			mongoOperations = (MongoOperations) new MongoTemplate(client, databaseName);
		} catch (Exception ex) {
			// Throw error here
		}
	}

	public String getDATABASE_NAME() {
		return DATABASE_NAME;
	}

}
