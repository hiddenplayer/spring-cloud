package com.microservice.transaction.services;

import com.microservice.transaction.entities.Transaction;
import com.microservice.transaction.iservices.ITransactionService;
import com.microservice.transaction.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepo repository;


    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return repository.findAll();
    }

    @Override
    public Transaction getTransactionByID(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getByUserId(Integer id) {
        return repository.findByUserId(id);
    }

    @Override
    public List<Transaction> getByOverdue() {
        return repository.findByOverdue();
    }

    @Override
    public List<Transaction> getTransactionByTime(Date start, Date end) {
        return repository.findByTime(start, end);
    }

    @Override
    public Transaction updateTransaction(Integer id, Transaction dto) {
        Transaction existing = repository.findById(id).orElse(null);
        if(existing!=null){
            existing.setIssuedDate(dto.getIssuedDate());
            existing.setDueDate(dto.getDueDate());
            existing.setFinished(dto.isFinished());
            existing.setUserId(dto.getUserId());
            return repository.save(existing);
        }
        return null;
    }

}
