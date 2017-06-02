package com.codenpay;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String>{

	List<Transaction> findByStatusResultNotNull(Sort sort);
}
