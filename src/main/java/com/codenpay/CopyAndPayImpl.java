package com.codenpay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("copyAndPay")
public class CopyAndPayImpl implements CopyAndPay
{
	private Logger logger = LoggerFactory.getLogger(CopyAndPayImpl.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private AppConfig appConfig;

	@Override
	public CheckoutResponse checkout(CheckoutRequest request) throws IOException {
		String url = "https://test.oppwa.com/v1/checkouts";

		HttpClient client = appConfig.httpClient();		
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", "codenpay/faisal.adnan@gmail.com");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("authentication.userId", "8a8294184b4f2868014b4f86f767015d"));
		urlParameters.add(new BasicNameValuePair("authentication.password", "F8T7N4PD"));
		urlParameters.add(new BasicNameValuePair("authentication.entityId", "8a8294184b4f2868014b4f87bf160173"));
		urlParameters.add(new BasicNameValuePair("paymentType", "DB"));
		urlParameters.add(new BasicNameValuePair("amount", "99.95"));
		urlParameters.add(new BasicNameValuePair("currency", "EUR"));
		urlParameters.add(new BasicNameValuePair("createRegistration", "true"));
		urlParameters.add(new BasicNameValuePair("testMode", "EXTERNAL"));		

		CheckoutResponse resp;
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(post);	
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity);
		logger.info(str);
		resp = objectMapper.readValue(str, CheckoutResponse.class);
		return resp;
	}

	@Override
	public StatusResponse checkStatus(String checkoutId) throws IOException {		
		StringBuffer sbUrl = new StringBuffer();
		String rootUrl = "https://test.oppwa.com/v1/checkouts";
		sbUrl.append(rootUrl);
		sbUrl.append("/");
		sbUrl.append(checkoutId);
		sbUrl.append("/");
		sbUrl.append("payment");
		
		HttpClient client = appConfig.httpClient();		
		HttpGet get = new HttpGet(sbUrl.toString());

		// add header
		get.setHeader("User-Agent", "codenpay/faisal.adnan@gmail.com");

		StatusResponse resp;
		HttpResponse response = client.execute(get);	
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity);
		logger.info(str);
		resp = objectMapper.readValue(str, StatusResponse.class);
		return resp;
	}

	public PaymentResponse payments(PaymentRequest request) throws IOException {
		throw new IOException("Not supported");
	}

	@Override
	public CheckoutResponse oneclick(CheckoutRequest request) throws IOException {
		Authentication authentication = request.getAuthentication();
		BasicPayment basicPayment = request.getBasicPayment();
		List<String> registrationIds = request.getRegistrationIds();

		String url = "https://test.oppwa.com/v1/checkouts";

		HttpClient client = appConfig.httpClient();		
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", "codenpay/faisal.adnan@gmail.com");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("authentication.userId", authentication.getUserId()));
		urlParameters.add(new BasicNameValuePair("authentication.password", authentication.getPassword()));
		urlParameters.add(new BasicNameValuePair("authentication.entityId", authentication.getEntityId()));
		urlParameters.add(new BasicNameValuePair("paymentType", basicPayment.getPaymentType()));
		urlParameters.add(new BasicNameValuePair("amount", basicPayment.getAmount()));
		urlParameters.add(new BasicNameValuePair("currency", basicPayment.getCurrency()));
		for (int i=0;i<registrationIds.size();i++)
		{
			urlParameters.add(new BasicNameValuePair("registrations["+i+"].id", registrationIds.get(i)));
		}
		urlParameters.add(new BasicNameValuePair("createRegistration", "true"));

		CheckoutResponse resp;
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(post);	
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity);
		logger.info(str);
		resp = objectMapper.readValue(str, CheckoutResponse.class);
		return resp;
	}
}
