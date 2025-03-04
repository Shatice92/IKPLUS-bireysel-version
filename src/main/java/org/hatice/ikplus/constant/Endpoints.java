package org.hatice.ikplus.constant;

public class Endpoints {
	//Temel Endpointler
	public static final String VERSION = "/v1";
	public static final String API = "/api";
	public static final String DEVELOPER = "/dev";
	public static final String ROOT = VERSION + DEVELOPER;
	public static final String PASSWORD = ROOT + "/password";
	
	
	// Yeni Roller ile ilgili endpointler
	public static final String ADMIN = ROOT + "/admin"; // ADMIN rolü için
	public static final String COMPANY_MANAGER = ROOT + "/company-manager"; // Şirket Yöneticisi rolü için
	public static final String EMPLOYEE = ROOT + "/employee"; // Personel rolü için
	public static final String VISITOR = ROOT + "/visitor"; // Ziyaretçi rolü için
	public static final String WEBSITE_MEMBER = ROOT + "/website-member"; // Website Üyesi rolü için
	
	// Diğer endpointler
	public static final String USER = ROOT + "/user";
	public static final String ROLE = ROOT + "/role";
	public static final String COMMENT = ROOT + "/comment";
	public static final String NOTIFICATION = ROOT + "/notification";
	public static final String DASHBOARD = "/dashboard";

	public static final String BLOOD_TYPES = ROOT+"/blood-types";
	public static final String CONTACT = ROOT + "/contact";
	public static final String LEAVES = ROOT + "/leaves";

	
	// Admin rolü için
	public static final String ADMIN_COMPANIES = ADMIN + "/companies";
	public static final String ADMIN_USERS = ADMIN + "/users";
	public static final String ADMIN_SETTINGS = ADMIN + "/settings";
	public static final String ADMIN_FEATURES = ADMIN + "/features";
	public static final String ADMIN_SUBSCRIPTION = ADMIN + "/subscription";
	public static final String ADMIN_LEAVE_TYPES = ADMIN + "/leave-types";
	public static final String ADMIN_ROLE = ADMIN + "/role";
	public static final String SAVE_USER = ADMIN + "/save-user";
	public static final String GET_ALL_USERS = ADMIN + "/get-all-users";
	public static final String UPDATE_USER = ADMIN + "/update-user/{id}";
	public static final String FIND_BY_ID = ADMIN + "/find-by-id/{id}";
	
	
	// Şirket Yöneticisi rolü için
	public static final String COMPANY_MANAGER_EMPLOYEES = COMPANY_MANAGER + "/employees";
	public static final String COMPANY_MANAGER_SHIFTS = COMPANY_MANAGER + "/shifts";
	public static final String COMPANY_MANAGER_BREAK = COMPANY_MANAGER + "/break";
	public static final String COMPANY_MANAGER_COMMENTS = COMPANY_MANAGER + "/comments";
	public static final String COMPANY_MANAGER_LEAVES = COMPANY_MANAGER + "/leaves";
	public static final String COMPANY_MANAGER_EMPLOYEE_DOCUMENT = COMPANY_MANAGER + "/employee-document";
	public static final String COMPANY_MANAGER_SETTINGS = COMPANY_MANAGER + "/settings";
	public static final String COMPANY_MANAGER_NOTIFICATION = COMPANY_MANAGER + "/notification";
	public static final String COMPANY_MANAGER_ASSETS =COMPANY_MANAGER+"/assets" ;
	
	
	// Personel rolü için
	public static final String EMPLOYEE_PROFILE = EMPLOYEE + "/profile";
	public static final String EMPLOYEE_LEAVES = EMPLOYEE + "/leaves";
	public static final String EMPLOYEE_ASSETS = EMPLOYEE + "/assets";
	public static final String EMPLOYEE_EXPENSES = EMPLOYEE + "/expenses";
	public static final String EMPLOYEE_NOTIFICATION = EMPLOYEE + "/notification";
	
	
	// Ziyaretçi rolü için
	public static final String VISITOR_COMMENTS = VISITOR + "/comments";
	public static final String VISITOR_COMMENT_DETAILS = VISITOR + "/comment/{employeeId}";
	
	
	// Site Üyesi rolü için
	public static final String WEBSITE_MEMBER_PROFILE = WEBSITE_MEMBER + "/profile";
	public static final String WEBSITE_MEMBER_SETTINGS = WEBSITE_MEMBER + "/settings";
	public static final String WEBSITE_MEMBER_NOTIFICATION = WEBSITE_MEMBER + "/notification";
	
	
	// CRUD İşlemleri
	public static final String SAVE = "/save";
	public static final String UPDATE = "/update/{id}";
	public static final String DELETE = "/delete/{id}";
	public static final String LIST = "/list";
	public static final String GETBYID = "/get-by-id/{id}";
	public static final String GETBYUSERNAME = "/get-by-username";
	public static final String GETPROFILEBYTOKEN = "/get-profile-by-token";
	public static final String GETBYSTATUS = "/get-by-status";
	public static final String ASSIGN = "/assign";
	public static final String GETBYROLENAME = "/get-by-role-name/{roleName}";
	public static final String ACTIVATESTATUS = "/activate/{id}";
	public static final String DEACTIVATESTATUS = "/deactivate/{id}";
	public static final String GETBYCOMPANYID = "/get-by-company-id/{companyId}";
	public static final String GETBYEMPLOYEEID = "/get-by-employee-id/{employeeId}";
	public static final String FINDBYID = "/find-by-id/{id}";
	public static final String GET_PROFILE_BY_TOKEN = "/get-profile-by-token";
	public static final String UPDATE_STATUS = "/update-status";
	
	
	//kontrol et
	public static final String APPROVE = "/approve/{id}";
	public static final String REJECT = "/reject/{id}";
	public static final String GETEXPENSESBYEMPLOYEEID = "/get-expenses-by-employee/{id}";
	public static final String GETASSETBYEMPLOYEEID = "/get-asset-by-employeeId";
	
	
	public static final String REGISTER = "/register";
	public static final String LOGIN = "/login";
	public static final String ASSIGNROLE = "/assign-role";
	public static final String UPDATEUSER = "/update-user/{id}";

	public static final String VERIFY = "/verify/{authId}";
	public static final String PASSWORD_REQUEST =  "/request";
	public static final String PASSWORD_RESET =  "/reset";

	public static final String UPDATE_USER_PROFILE = "/update-user-profile";
	
	
	
}