package com.eliarech.transaction.repository;

import com.eliarech.transaction.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    Optional<Transaction> findByReference(String reference);
    
    List<Transaction> findByAccountId(String accountId);
    
    List<Transaction> findByAccountIdAndCreatedAtBetween(
            String accountId, LocalDateTime start, LocalDateTime end);
    
    List<Transaction> findByStatusAndCreatedAtBefore(
            Transaction.Status status, LocalDateTime date);
    
    List<Transaction> findByType(String type);
}
