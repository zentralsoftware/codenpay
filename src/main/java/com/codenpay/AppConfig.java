package com.codenpay;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class AppConfig {
	private Logger logger = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	public MongoTemplate mongoTemplate() throws IOException {
		String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		if (host == null) {
			host = "localhost";
			MongoClient mongo = new MongoClient("localhost", 27017);
			MongoTemplate mongoTemplate = new MongoTemplate(mongo, "test");
			return mongoTemplate;
		}
		String strPort = System.getenv("OPENSHIFT_MONGODB_DB_PORT");
		int port = 27017;
		if (strPort != null)
		{
			port = Integer.parseInt(strPort);
		}
		String database = System.getenv("OPENSHIFT_APP_NAME");	
		if (database == null) {
			database = "test";
		}
		String user = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
		if (user == null) {
			user = "test";
		}
		String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
		if (password == null) {
			password = "password";
		}		
		StringBuffer sb = new StringBuffer();
		sb.append("mongodb://");
		sb.append(user);
		sb.append(":");
		sb.append(password);
		sb.append("@");
		sb.append(host);
		sb.append(":");
		sb.append(port);
		sb.append("/");
		sb.append(database);
		logger.debug("mongodb URI: " + sb.toString());
		MongoClient mongo = new MongoClient(host, port);
		UserCredentials credential = new UserCredentials(user, password);
		MongoTemplate mongoTemplate = new MongoTemplate(mongo, database, credential);
		return mongoTemplate;
	}
 	
	@Bean
	public HttpClient httpClient()
	{
		HttpClient client = HttpClientBuilder.create().build();
		return client;
	}

	@Bean
	public Authentication authentication()
	{
		Authentication authentication = new Authentication();
		authentication.setUserId("8a8294184b4f2868014b4f86f767015d");
		authentication.setPassword("F8T7N4PD");
		authentication.setEntityId("8a8294184b4f2868014b4f87bf160173");
		return authentication;
	}
	
	
}
