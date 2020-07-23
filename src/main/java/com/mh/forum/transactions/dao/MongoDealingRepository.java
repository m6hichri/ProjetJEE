package com.mh.forum.transactions.dao;

import com.mh.forum.transactions.model.Creditor;
import com.mh.forum.transactions.model.Dealing;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDealingRepository extends MongoRepository<Dealing,String> {
}
