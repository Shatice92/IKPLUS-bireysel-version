package org.hatice.ikplus.constant;

public class Endpoints {

	
	public static final String VERSION="/v1";
	
	public static final String API="/api";
	public static final String DEVELOPER="/dev";
	public static final String TEST="/test";
	public static final String PROD="/prod";
	
	public static final String ROOT=VERSION+DEVELOPER;
	
	public static final String BREAK= ROOT+"/break";
	public static final String SHIFT=ROOT+"/shift";
	public static final String COMMENT= ROOT+"/comment";
	public static final String NOTIFICATION=ROOT+"/notification";
	
    public static final String USER=ROOT+"/user";
    public static final String ROLE=ROOT+"/role";

    
    
    public static final String EMPLOYEE=ROOT+"/employee";
    public static final String EMPLOYEEDOCUMENT=ROOT+"/employee-document";
    
    
    
    public static final String SAVE="/save";
    public static final String UPDATE = "/update/{id}";
    public static final String DELETE =  "/delete/{id}";
    public static final String LIST = "/list";
    public static final String ACTIVATESTATUS = "/activate-status/{id}";
    public static final String DEACTIVATESTATUS = "/deactivate-status/{id}";
    public static final String GETBYID = "/get-by-id/{id}";
    public static final String GETBYCOMPANYID = "/get-by-companyid/{companyId}";
    public static final String GETBYUSERNAME = "/get-by-username";
    public static final String GETBYEMAIL =  "/get-by-email";
    public static final String GETUSERBYNAME = "/get-user-by-username";
    public static final String GETPROFILEBYTOKEN = "/get-profile-by-token";
    
    
    public static final String GETBYSTATUS = "/get-by-status";
    

  public static final String USERROLE=ROOT+"/userRole";
	public static final String SAVEUSER="/save-user";
	public static final String GETALLUSERS="/get-all-users";
	public static final String FINDBYID = "/findById/{id}";
	public static final String REGISTER="/register";
	public static final String LOGIN = "/login";
	
	
	

}