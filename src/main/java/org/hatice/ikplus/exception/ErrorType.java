package org.hatice.ikplus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	USER_NOT_FOUND(1001, "User not found", HttpStatus.NOT_FOUND),
	EMPLOYEE_NOT_FOUND(2001, "Employee not found", HttpStatus.NOT_FOUND),
	EMPLOYEEDOCUMENT_NOT_FOUND(2001, "Employee not found", HttpStatus.NOT_FOUND),
	USERLIST_EMPTY(1002, "UserList is Empty", HttpStatus.NOT_FOUND),
	EMPLOYEELIST_EMPTY(2002, "EmployeeList is Empty", HttpStatus.NOT_FOUND),
	VALIDATION_ERROR(400,"Validation Errors, Please check validation rules",HttpStatus.BAD_REQUEST),
	INTERNAL_SERVER_ERROR(500,"Server Error, Try Again Later",HttpStatus.INTERNAL_SERVER_ERROR),
	EMPLOYEE_ALREADY_ACTIVE(2002, "Employee already active", HttpStatus.BAD_REQUEST ),
	EMPLOYEE_ALREADY_PASSIVE(2003, "Employee already passive", HttpStatus.BAD_REQUEST ),
	PASSWORD_MISMATCH(1005,"Password dont match ",HttpStatus.BAD_REQUEST),
	INVALID_TOKEN(1004,"Invalid Token",HttpStatus.BAD_REQUEST ),
	INVALID_CREDENTIALS(1003, "Invalid email or password.", HttpStatus.UNAUTHORIZED),
	ROLE_NOT_FOUND(4001, "Role not found", HttpStatus.NOT_FOUND),
	EXPENSE_NOT_FOUND(3001, "Expense not found", HttpStatus.NOT_FOUND),
	ASSET_NOT_FOUND(5001, "Asset not found", HttpStatus.NOT_FOUND),
	LEAVE_NOT_FOUND(6001, "Leave not found", HttpStatus.NOT_FOUND),
	LEAVE_ALREADY_APPROVED(6002, "Leave already approved", HttpStatus.BAD_REQUEST),
	LEAVE_ALREADY_REJECTED(6003, "Leave already rejected", HttpStatus.BAD_REQUEST),
	LEAVELIST_EMPTY(6004, "Leavelist is Empty", HttpStatus.NOT_FOUND),
	AUTHORIZATION_NOT_FOUND(7001, "Authorization not found", HttpStatus.NOT_FOUND),
	EMAIL_ALREADY_EXISTS(1006, "Email already exists", HttpStatus.BAD_REQUEST);
	
	
	int code;
	String message;
	HttpStatus httpStatus;
}