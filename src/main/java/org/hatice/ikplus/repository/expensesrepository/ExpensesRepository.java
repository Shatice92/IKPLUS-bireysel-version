package org.hatice.ikplus.repository.expensesrepository;

import org.hatice.ikplus.entity.expensemanagement.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
}