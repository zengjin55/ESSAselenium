package com.essa.framework;

import com.mysql.cj.api.jdbc.Statement;
import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButil {
	/*// 数据库驱动名字
	private static String jdbcName = null;
	// 数据库协议地址
	private static String dbUrl = null;
	// 数据库用户名
	private static String dbUser = null;
	// 数据库密码
	private static String dbPassword = null;
*/
	public static Connection getCon() {

		Connection conn = null;
		try {
			//通过环境找寻相应的配置，然后初始化数据库连接
			EnvEnum envEnum = EnvEnum.fromCode(Model.getEnv());
			ConfigProperties dbConfig = ConfigProperties.getConfig(envEnum);
			Class.forName(dbConfig.getJdbcName());
			conn = DriverManager.getConnection(dbConfig.getDbUrl(),dbConfig.getDbUser(), dbConfig.getDbPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		/*if ("SIT".equals(Model.getEnv())) {
			try {
				jdbcName = DBInfo.getSitJdbcName();
				dbUrl = DBInfo.getSitSqlUrl();
				dbUser = DBInfo.getSitUser();
				dbPassword = DBInfo.getSitPassword();
				Class.forName(jdbcName);
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				return conn;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("HOTFIX".equals(Model.getEnv())) {
			try {
				jdbcName = DBInfo.getHotfixJdbcName();
				dbUrl = DBInfo.getHotfixSqlUrl();
				dbUser = DBInfo.getHotfixUser();
				dbPassword = DBInfo.getHotfixPassword();
				Class.forName(jdbcName);
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				return conn;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("DIT".equals(Model.getEnv())) {
			try {
				jdbcName = DBInfo.getDitJdbcName();
				dbUrl = DBInfo.getDitSqlUrl();
				dbUser = DBInfo.getDitUser();
				dbPassword = DBInfo.getDitPassword();
				Class.forName(jdbcName);
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				return conn;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("UAT".equals(Model.getEnv())) {
			try {
				jdbcName = DBInfo.getUatJdbcName();
				dbUrl = DBInfo.getUatSqlUrl();
				dbUser = DBInfo.getUatUser();
				dbPassword = DBInfo.getUatPassword();
				Class.forName(jdbcName);
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				return conn;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;*/
	}

	/**
	 * 关闭连接
	 * 
	 * @param stmt
	 * @param conn
	 * @throws Exception
	 */
	public static void close(Statement stmt, Connection conn) throws Exception {
		if (stmt != null) {
			stmt.close();
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param cstmt
	 * @param conn
	 * @throws Exception
	 */
	public static void close(CallableStatement cstmt, Connection conn) throws Exception {
		if (cstmt != null) {
			cstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param pstmt
	 * @param conn
	 * @throws SQLException
	 */
	public static void close(PreparedStatement pstmt, Connection conn) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 重载关闭方法
	 * 
	 * @param rs
	 * @param pstmt
	 * @param conn
	 * @throws Exception
	 */
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) throws Exception {
		if (rs != null) {
			rs.close();
			if (pstmt != null) {
				pstmt.close();
				if (conn != null) {
					conn.close();
				}
			}
		}
	}
}
