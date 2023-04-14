import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.swing.JOptionPane.showMessageDialog;

public class OutPut {
    public OutPut() throws SQLException {
        Statement st = Connect.doConnect();
        String sql="SELECT  SC.Cno, Cname, Ccredit,Grade FROM Course,SC WHERE SC.Cno = Course.Cno AND Grade > 0 AND SC.Sno = " +
                "\"" + Login.s1  + "\"";
        try {
            Connect.con.setAutoCommit(false);
            ResultSet r = st.executeQuery(sql);
            int count = 0;
            while(r.next()) count++;
            r = st.executeQuery(sql);
            Object[][] array=new Object[count + 1][4];
            array[0][0] = "课程序号";
            array[0][1] = "课程名称";
            array[0][2] = "学分";
            array[0][3] = "成绩";
            count = 1;
            while(r.next())
            {
                array[count][0]=r.getString("Cno");
                array[count][1]=r.getString("Cname");
                array[count][2]=Integer.valueOf(r.getInt("Ccredit"));
                array[count][3]=Integer.valueOf(r.getInt("Grade"));
                count++;
            }

            WriteArray.writeArrayToTxt(array,"Score.txt");
            WriteArray.writeArrayToTxt(array,"Score.xls");
            showMessageDialog(null,"数据导出成功");
            Connect.con.commit();
            Connect.con.close();
        } catch (SQLException e) {
            Connect.con.rollback();
            throw new RuntimeException(e);
        }
    }
}
