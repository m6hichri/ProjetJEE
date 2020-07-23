package com.mh.forum.transactions.dao;

import com.mh.forum.transactions.model.Creditor;
import com.mh.forum.transactions.model.Debtor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCreditorRepository extends MongoRepository<Creditor,String> {
}
