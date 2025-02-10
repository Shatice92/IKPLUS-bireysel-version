package org.hatice.ikplus.Repository.expensesmanagement;

import org.hatice.ikplus.entity.companymanagement.Company;
import org.hatice.ikplus.entity.expensemanagement.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
}