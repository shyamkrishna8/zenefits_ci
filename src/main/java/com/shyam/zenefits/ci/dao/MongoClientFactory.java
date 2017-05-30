package com.shyam.zenefits.ci.dao;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

public class MongoClientFactory {

	private static final String HOST = "localhost";
	private static final int PORT = 27017;
	private MongoClient client = null;

	public final static MongoClientFactory INSTANCE = new MongoClientFactory();

	public MongoClientFactory() {
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		MongoClientOptions options = builder.connectionsPerHost(10).build();
		String serverUrl = HOST + ":" + PORT;
		try {
			client = new MongoClient(serverUrl, options);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public MongoClient getClient() {
		return client;
	}

	public void setClient(MongoClient client) {
		this.client = client;
	}

}
