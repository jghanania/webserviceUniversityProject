package edu.fra.uas.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import edu.fra.uas.model.Expense;

/**
 * ExpenseRepository is a mock repository for storing and managing Expense
 * objects.
 * It extends HashMap to store expenses in-memory, using the expense ID as the
 * key.
 * 
 * This repository is used by the ExpenseService to simulate data storage and
 * retrieval.
 * In a real-world scenario, this would be replaced by a proper database.
 */
@Repository
public class ExpenseRepository extends HashMap<Long, Expense> {

    // No need to define any methods here as HashMap provides built-in methods
    // for storing, retrieving, and removing Expense objects by their ID.
}