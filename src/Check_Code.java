import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Check_Code {
    public static boolean CheckCode(String user, String code, int type) throws SQLException {

        String sql = "select * from Student where Sno=\'" + user + "\' and Scode=\'" + code + "\'";
        if(type == 1) sql = "select * from Teacher where Tno=\'" + user + "\' and Tcode=\'" + code + "\'";
        Statement st = Connect.doConnect();
        ResultSet rs = null;
        try
        {
            Connect.con.setAutoCommit(false);
            rs = st.executeQuery(sql);
            Connect.con.commit();
        }
        catch (SQLException e2)
        {
            try {
                Connect.con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e2.printStackTrace();
        }
        if(rs == null) return false;
        if(rs.next()) return true;
        return false;
    }
}
