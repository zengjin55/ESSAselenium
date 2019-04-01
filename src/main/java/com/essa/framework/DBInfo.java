package com.essa.framework;

public class DBInfo {

	//sit
	public static String sitJdbcName = "com.mysql.cj.jdbc.Driver";
	public static String sitSqlUrl = "jdbc:mysql://192.168.1.247:3307/bpms";
	public static String sitUser = "root";
	public static String sitPassword = "redhat";
	//hotfix
	public static String hotfixJdbcName = "com.mysql.cj.jdbc.Driver";
	public static String hotfixSqlUrl = "jdbc:mysql://192.168.1.19:3321/bpms";
	public static String hotfixUser = "essa_hotfix";
	public static String hotfixPassword = "essa^_hotfix^D<K123";
	//dit
	public static String ditJdbcName;
	public static String ditSqlUrl;
	public static String ditUser;
	public static String ditPassword;
	//uat
	public static String uatJdbcName = "com.mysql.cj.jdbc.Driver";
	public static String uatSqlUrl = "jdbc:mysql://112.74.135.97:3325/bpms";
	public static String uatUser = "essa_uat";
	public static String uatPassword = "lXI!RqH6thvmMNNh";
	
	public static String getSitJdbcName() {
		return sitJdbcName;
	}
	public static String getSitSqlUrl() {
		return sitSqlUrl;
	}
	public static String getSitUser() {
		return sitUser;
	}
	public static String getSitPassword() {
		return sitPassword;
	}
	public static String getHotfixJdbcName() {
		return hotfixJdbcName;
	}
	public static String getHotfixSqlUrl() {
		return hotfixSqlUrl;
	}
	public static String getHotfixUser() {
		return hotfixUser;
	}
	public static String getHotfixPassword() {
		return hotfixPassword;
	}
	public static String getDitJdbcName() {
		return ditJdbcName;
	}
	public static String getDitSqlUrl() {
		return ditSqlUrl;
	}
	public static String getDitUser() {
		return ditUser;
	}
	public static String getDitPassword() {
		return ditPassword;
	}
	public static String getUatJdbcName() {
		return uatJdbcName;
	}
	public static String getUatSqlUrl() {
		return uatSqlUrl;
	}
	public static String getUatUser() {
		return uatUser;
	}
	public static String getUatPassword() {
		return uatPassword;
	}
}
