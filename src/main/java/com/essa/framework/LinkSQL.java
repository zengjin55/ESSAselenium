package com.essa.framework;
import java.sql.*;

public class LinkSQL {

    public static void main(String[] args) {
        LinkSQL dao = new LinkSQL();
        dao.SQLQuery();

    }

    public  static  String SQLQuery() {

        String     Code = null;
        Connection  conn = null;
        Statement   stmt = null;
        ResultSet   rs   = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
//            连接SIT环境的数据库
            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.247:3307/bpms","root","redhat");

            stmt = conn.createStatement();

// 查询FO单在制单中的PO单号
            rs =stmt.executeQuery("SELECT p.code FROM fact_fo as f   INNER JOIN sale_po as p  ON f.from_id=p.id  WHERE send_order_status=2 AND from_type=1 ORDER BY f.id DESC LIMIT 1");

            while (rs.next()) {
                Code = rs.getString("code");
            }
            return Code;

        } catch (ClassNotFoundException e) {
            System.out.println("驱动类无法找到！");
            throw  new  RuntimeException(e);

        } catch (SQLException e) {
            System.out.println("数据库访问异常！");
            throw  new RuntimeException(e);
        }finally {
            //关闭链接资源
            try {
                if (rs !=null) {
                    rs.close();
                }
                if (stmt !=null) {
                    stmt.cancel();
                }
                if (conn !=null) {
                    conn.close();
                }

            } catch (SQLException e) {
                System.out.println("关闭连接时发生异常");
                e.printStackTrace();
            }
        }
    }
}
