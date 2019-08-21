package cn.util;

import java.sql.*;

public class BaseDao {
    protected static Connection conn;
    protected static PreparedStatement ps;
    protected static ResultSet rs;


    public static void getConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_person","root","769576");
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void closeConn(){
        try {
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int executeUpdate(String sql,Object[] params){
        int flag = 0;
        getConn();
        try {
            ps = conn.prepareStatement(sql);
            if(params!=null){
                for(int i=0;i<params.length;i++){
                    ps.setObject(i+1,params[i]);
                }
            }
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return flag;
    }


    public ResultSet executeQuery(String sql,Object[] params){
        getConn();
        try {
            ps = conn.prepareStatement(sql);
            if(params!=null){
                for(int i=0;i<params.length;i++){
                    ps.setObject(i+1,params[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static void main(String[] args) {
        BaseDao.getConn();
    }

}
