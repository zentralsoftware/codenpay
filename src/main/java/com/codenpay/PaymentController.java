   package com.codenpay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.WriteResult;

@Controller
public class PaymentController {

	@Autowired
	private CopyAndPay copyAndPay;
	
	@Autowired
	private AppConfig mongoConfig;	

	@Autowired
	private TransactionRepository transactionRepository;

	@RequestMapping("/checkout")
	public ModelAndView checkout() throws IOException {
		CheckoutRequest request = new CheckoutRequest();
		request.setAuthentication(mongoConfig.authentication());
		BasicPayment basicPayment = new BasicPayment();
		basicPayment.setAmount("99.95");
		basicPayment.setCurrency("EUR");
		basicPayment.setPaymentType("DB");
		basicPayment.setPaymentBrand("");
		request.setBasicPayment(basicPayment);
		CheckoutResponse response = copyAndPay.checkout(request);		

		Transaction transaction = new Transaction();
		String identifier = Base64Utils.encodeToString(response.getId().getBytes());
		transaction.setIdentifier(identifier);
		transaction.setTimestamp(System.currentTimeMillis());
		transaction.setCheckoutId(response.getId());
		transaction.setAmount(basicPayment.getAmount());
		transaction.setBrand(basicPayment.getPaymentBrand());
		transaction.setCurrency(basicPayment.getCurrency());
		transaction.setType(basicPayment.getPaymentType());
		transaction.setBasicPayment(basicPayment);
		mongoConfig.mongoTemplate().save(transaction);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("checkout");
		mv.addObject("transaction", transaction);
		mv.addObject("checkoutid", response.getId());
		mv.addObject("identifier", identifier);
		mv.addObject("brands", "VISA MASTER AMEX DISCOVER MAESTRO VISAELECTRON JCB HIPERCARD CARTEBLEUE DANKORT DINERS VPAY");
		return mv;
	}	
	
	@RequestMapping("/checkstatus/{identifier}")
	public ModelAndView checkstatus(@PathVariable String identifier) throws IOException {
		
		Query query = new Query(Criteria.where("identifier").is(identifier));
		Transaction transaction = mongoConfig.mongoTemplate().findOne(query, Transaction.class);
		if (transaction == null)
		{
			ModelAndView denied = new ModelAndView();
			denied.setViewName("denied");
			denied.addObject("code", "");
			denied.addObject("description", "Transaction does not exist");
			return denied;			
		}
		StatusResponse response = copyAndPay.checkStatus(transaction.getCheckoutId());
		Result result = response.getResult();
		
		if (result.getCode().startsWith("000"))
		{
			Update update = new Update().set("statusId", response.getId())
				.set("statusResult", response.getResult())
				.set("card", response.getCard())							
				.set("brand", response.getPaymentBrand())
				.set("basicPayment.paymentBrand", response.getPaymentBrand())
				.set("registrationId", response.getRegistrationId());
			mongoConfig.mongoTemplate().updateFirst(query, update, Transaction.class);
			registerToken(response.getRegistrationId());
			ModelAndView success = new ModelAndView();
			success.setViewName("success");
			success.addObject("id", response.getId());
			success.addObject("amount", transaction.getAmount());
			success.addObject("currency", transaction.getCurrency());
			success.addObject("brand", response.getPaymentBrand());
			return success;
		} else
		{
			Update update = new Update().set("statusId", response.getId())
				.set("statusResult", response.getResult())
				.set("card", response.getCard())							
				.set("brand", response.getPaymentBrand())
				.set("basicPayment.paymentBrand", response.getPaymentBrand());
			mongoConfig.mongoTemplate().updateFirst(query, update, Transaction.class);			
			ModelAndView denied = new ModelAndView();
			denied.setViewName("denied");
			denied.addObject("code", result.getCode());
			denied.addObject("description", result.getDescription());
			return denied;
		}
	}		

	@RequestMapping("/list")
	public ModelAndView list() throws IOException {
		Query query = new Query();
		query.addCriteria(Criteria.where("statusResult"));
		query.with(new Sort(Sort.Direction.DESC, "timestamp"));
		//List<Transaction> list = mongoConfig.mongoTemplate().find(query, Transaction.class);
		List<Transaction> list = transactionRepository.findByStatusResultNotNull(new Sort(Sort.Direction.DESC, "timestamp"));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("transactions");
		mv.addObject("transactions", list);
		return mv;
	}

	@RequestMapping("/tokenlist")
	public ModelAndView tokenlist() throws IOException {
		List<Token> list = mongoConfig.mongoTemplate().findAll(Token.class);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("token");
		mv.addObject("tokens", list);
		return mv;
	}	

	@RequestMapping("/oneclick")
	public ModelAndView oneclick() throws IOException {
		CheckoutRequest request = new CheckoutRequest();
		request.setAuthentication(mongoConfig.authentication());
		BasicPayment basicPayment = new BasicPayment();
		basicPayment.setAmount("99.95");
		basicPayment.setCurrency("EUR");
		basicPayment.setPaymentType("DB");
		basicPayment.setPaymentBrand("");
		request.setBasicPayment(basicPayment);
		List<Token> list = mongoConfig.mongoTemplate().findAll(Token.class);
		List<String> registrationIds = new ArrayList<String>();
		for (Token token : list)
		{
			registrationIds.add(token.getRegistrationId());
		}
		request.setRegistrationIds(registrationIds);

		CheckoutResponse response = copyAndPay.oneclick(request);
		
		Transaction transaction = new Transaction();
		String identifier = Base64Utils.encodeToString(response.getId().getBytes());
		transaction.setIdentifier(identifier);
		transaction.setCheckoutId(response.getId());
		transaction.setAmount(basicPayment.getAmount());
		transaction.setBrand(basicPayment.getPaymentBrand());
		transaction.setCurrency(basicPayment.getCurrency());
		transaction.setType(basicPayment.getPaymentType());
		transaction.setBasicPayment(basicPayment);
		mongoConfig.mongoTemplate().save(transaction);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("oneclick");
		mv.addObject("transaction", transaction);
		mv.addObject("checkoutid", response.getId());
		mv.addObject("identifier", identifier);
		mv.addObject("brands", "VISA MASTER AMEX DISCOVER MAESTRO VISAELECTRON JCB HIPERCARD CARTEBLEUE DANKORT DINERS VPAY");
		return mv;
	}	
	
	@RequestMapping("/purge")
	public @ResponseBody List<WriteResult> purge() throws IOException {
		List<WriteResult> list = new ArrayList<WriteResult>();
		WriteResult transactionResult = mongoConfig.mongoTemplate().remove(new Query(), Transaction.class);
		WriteResult tokenResult = mongoConfig.mongoTemplate().remove(new Query(), Token.class);
		list.add(transactionResult);
		list.add(tokenResult);
		return list;
	}	
	
	protected Token registerToken(String registrationId) throws IOException {
		Query query = new Query(Criteria.where("registrationId").is(registrationId));
		Token token = mongoConfig.mongoTemplate().findOne(query, Token.class);
		if (token == null)
		{
			Token newToken = new Token();
			String identifier = Base64Utils.encodeToString(registrationId.getBytes());
			newToken.setIdentifier(identifier);
			newToken.setRegistrationId(registrationId);
			mongoConfig.mongoTemplate().save(newToken);
		}
		return token;
	}

	@ExceptionHandler(IOException.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.setViewName("oops");
		return mav;
	}
}
