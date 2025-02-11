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
    public static final String USERROLE=ROOT+"/userRole";
}
