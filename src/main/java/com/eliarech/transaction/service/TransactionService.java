package com.eliarech.transaction.service;

import com.eliarech.transaction.exception.DuplicateTransactionException;
import com.eliarech.transaction.exception.TransactionNotFoundException;
import com.eliarech.transaction.model.Transaction;
import com.eliarech.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        // Check for existing transaction with same reference
        Optional<Transaction> existingTransaction = transactionRepository.findByReference(transaction.getReference());
        if (existingTransaction.isPresent()) {
            throw new DuplicateTransactionException("Transaction with reference " + transaction.getReference() + " already exists");
        }

        // Set timestamps and default status if not provided
        LocalDateTime now = LocalDateTime.now();
        transaction.setCreatedAt(now);
        transaction.setLastUpdatedAt(now);

        if (transaction.getStatus() == null) {
            transaction.setStatus(Transaction.Status.PENDING);
        }

        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
    }

    public Transaction getTransactionByReference(String reference) {
        return transactionRepository.findByReference(reference)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with reference: " + reference));
    }

    public List<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public List<Transaction> getTransactionsByAccountIdAndDateRange(String accountId, LocalDateTime start, LocalDateTime end) {
        return transactionRepository.findByAccountIdAndCreatedAtBetween(accountId, start, end);
    }

    public Transaction updateTransactionStatus(String id, Transaction.Status status) {
        Transaction transaction = getTransactionById(id);
        transaction.setStatus(status);
        transaction.setLastUpdatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByType(String type) {
        return transactionRepository.findByType(type);
    }

    public List<Transaction> getPendingTransactionsOlderThan(LocalDateTime date) {
        return transactionRepository.findByStatusAndCreatedAtBefore(Transaction.Status.PENDING, date);
    }

    public void deleteTransaction(String id) {
        Transaction transaction = getTransactionById(id);
        transactionRepository.delete(transaction);
    }
}