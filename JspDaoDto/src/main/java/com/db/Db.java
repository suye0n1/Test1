package com.db;

public class Db {
	//db연결하기
	static public String DB_JDBC_DRIVER_PACKAGE_PATH = "com.mysql.cj.jdbc.Driver";	//mysql
//	static private String DB_JDBC_DRIVER_PACKAGE_PATH = "oracle.jdbc.OracleDriver";	//오라클
	
	static private String DB_NAME = "board";
	static private String DB_URL_MYSQL = "jdbc:mysql://localhost:3306/"+DB_NAME;	//mysql
//	static private String DB_URL_ORACLE = "jdbc:oracle:thin:@127.0.0.1:1521:"+DB_NAME;	//오라클
	static public String DB_URL = DB_URL_MYSQL;	//디비 바뀌면 여기 바꾸시오.
	static public String DB_ID = "root";
	static public String DB_PW = "root";
	
	/* table들 */
	////	게시판
	public static final String JSP_BOARD = "jsp_board";	//자유게시판
	
	
}
