package org.hatice.ikplus.repository.expensesrepository;

import org.hatice.ikplus.entity.expensemanagement.Expenses;
import org.hatice.ikplus.enums.ExpensesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
	List<Expenses> findByEmployeeId(Long employeeId);
	List<Expenses> findByStatus(ExpensesType status);
}