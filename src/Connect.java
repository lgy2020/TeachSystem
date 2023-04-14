import java.sql.*;

public class Connect
{
    static Connection con;
    public static Statement doConnect()
    {
        String JDriver = "com.mysql.jdbc.Driver";// SQL数据库引擎
        String connectDB = "jdbc:mysql://127.0.0.1:3306/db_classwork";// 数据源
        try
        {
            Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("加载数据库引擎失败");
            System.exit(0);
        }
        try
        {
            String user = "root";
            String password = "1234";
            con = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
            //System.out.println("连接数据库成功");
            Statement stmt = con.createStatement();
            return stmt;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //System.out.println("数据库连接错误");
            System.exit(0);
        }
        return null;
    }
}