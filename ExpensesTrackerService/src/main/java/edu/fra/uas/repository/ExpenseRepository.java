package edu.fra.uas.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import edu.fra.uas.model.Expense;

@Repository
public class ExpenseRepository extends HashMap<Long, Expense> {

}