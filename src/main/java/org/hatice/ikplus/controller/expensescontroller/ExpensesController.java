package org.hatice.ikplus.controller.expensescontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.constant.Endpoints.*;
import org.hatice.ikplus.dto.request.expenserequest.AddExpensesRequestDto;
import org.hatice.ikplus.dto.request.expenserequest.UpdateExpensesRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.expenseresponse.ExpensesResponse;
import org.hatice.ikplus.service.expensesservice.ExpensesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hatice.ikplus.constant.Endpoints.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.EMPLOYEE_EXPENSES)
public class ExpensesController {
	private final ExpensesService expensesService;
	
	// Harcama ekleme (POST)
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Boolean>> addExpense(@RequestBody AddExpensesRequestDto request) {
		expensesService.addExpense(request);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Expenses saved successfully")
		                                     .code(200).success(true).build());
	}
	
	// Harcamayı onaylama (PUT)
	@PutMapping(APPROVE)
	public ResponseEntity<BaseResponse<Boolean>> approveExpense(@PathVariable Long id) {
		expensesService.approveExpense(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Expenses approved successfully")
		                                     .code(200).success(true).build());
	}
	
	// Harcamayı reddetme (PUT)
	@PutMapping(REJECT)
	public ResponseEntity<BaseResponse<Boolean>> rejectExpense(@PathVariable Long id) {
		expensesService.rejectExpense(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Expenses rejected successfully")
		                                     .code(200).success(true).build());
	}
	// Harcama güncelleme (PUT)
	@PutMapping(UPDATE)
	public ResponseEntity<BaseResponse<Boolean>> updateExpense(@PathVariable Long id,
	                                                           @RequestBody UpdateExpensesRequestDto request) {
		
		expensesService.updateExpense(id, request);
		
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Expenses updated successfully")
		                                     .code(200).success(true).build());
	}
	
	// Belirli bir çalışana ait harcamaları getirme (GET)
	@GetMapping(GETEXPENSESBYEMPLOYEEID)
	public ResponseEntity<BaseResponse<List<ExpensesResponse>>> getExpensesByEmployee(@PathVariable Long employeeId) {
		return ResponseEntity.ok(BaseResponse.<List<ExpensesResponse>>builder()
		                                     .data(expensesService.getExpensesByEmployee(employeeId)).message("Expenses retrieved successfully")
		                                     .code(200).success(true).build());
	}
	
	// Tüm harcamaları listeleme (GET)
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<ExpensesResponse>>> getAllExpenses() {
		return ResponseEntity.ok(BaseResponse.<List<ExpensesResponse>>builder()
		                                     .data(expensesService.getAllExpenses()).message("Expenses listed successfully")
		                                     .code(200).success(true).build());
	}
	
}