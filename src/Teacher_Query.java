import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Teacher_Query extends JFrame {

    static JFrame jf;
    static JTable table;
    static void TeacherQuery(int type) throws SQLException {

        jf = new JFrame("信息显示");
        //jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(666, 500);
        jf.setLayout(null);
        jf.setLocation(560, 320);

        Statement st = Connect.doConnect();
        ResultSet r;
        // 个人信息
        if(type == 1)
        {
            String[] columnNames = {"学号", "姓名", "年龄", "性别", "专业"};
            String sql="select Tno,Tname,Tage,Tsex,Ttitle from Teacher where Tno=\""+Login.s1+"\"";
            try {
                Connect.con.setAutoCommit(false);
                r = st.executeQuery(sql);
                int count = 0;
                while(r.next()) count++;
                r = st.executeQuery(sql);
                Object[][] array=new Object[count][5];
                count = 0;
                while(r.next())
                {
                    array[count][0]=r.getString("Tno");
                    array[count][1]=r.getString("Tname");
                    array[count][2]=Integer.valueOf(r.getInt("Tage"));
                    array[count][3]=r.getString("Tsex");
                    array[count][4]=r.getString("Ttitle");
                    count++;
                }
                table = new JTable(array, columnNames);
                Connect.con.commit();
                Connect.con.close();
            } catch (SQLException e) {
                Connect.con.rollback();
                throw new RuntimeException(e);
            }
        }
        // 本学期课表
        else if(type == 2)
        {
            String[] columnNames = {"课程序号", "课程名称", "学分"};
            String sql="SELECT TC.Cno, Course.Cname, Course.Ccredit FROM TC, Course " +
                    "WHERE TC.Cno = Course.Cno AND TC.Tno =  \"" + Login.s1 + "\"";
            try {
                Connect.con.setAutoCommit(false);
                r = st.executeQuery(sql);
                int count = 0;
                while(r.next()) count++;
                r = st.executeQuery(sql);
                Object[][] array=new Object[count][3];
                count = 0;
                while(r.next())
                {
                    array[count][0]=r.getString("Cno");
                    array[count][1]=r.getString("Cname");
                    array[count][2]=Integer.valueOf(r.getInt("Ccredit"));
                    count++;
                }
                table = new JTable(array, columnNames);
                Connect.con.commit();
                Connect.con.close();
            } catch (SQLException e) {
                Connect.con.rollback();
                throw new RuntimeException(e);
            }
        }
        // 同类课程查询
        else if(type == 3)
        {
            String s = Vague_Query.s;
            String[] columnNames = {"课程序号", "课程名称", "先导课", "学分", "剩余数量"};
            String sql="SELECT * FROM Course WHERE  Cno LIKE \"" + s + "%\"";
            try {
                Connect.con.setAutoCommit(false);
                r = st.executeQuery(sql);
                int count = 0;
                while(r.next()) count++;
                r = st.executeQuery(sql);
                Object[][] array=new Object[count][5];
                count = 0;
                while(r.next())
                {
                    array[count][0]=r.getString("Cno");
                    array[count][1]=r.getString("Cname");
                    array[count][2]=r.getString("Cpno");
                    array[count][3]=Integer.valueOf(r.getInt("Ccredit"));
                    array[count][4]=Integer.valueOf(r.getInt("Cnum"));
                    count++;
                }
                table = new JTable(array, columnNames);
                Connect.con.commit();
                Connect.con.close();
            } catch (SQLException e) {
                Connect.con.rollback();
                throw new RuntimeException(e);
            }
        }
        // 同一年级学生查询
        else if(type == 4)
        {
            String s = Vague_Query.s;
            String[] columnNames = {"学号", "姓名", "年龄", "性别", "专业"};
            String sql="select Sno,Sname,Sage,Ssex,Sdept from Student where Sno LIKE\""+ s +"%\"";
            try {
                Connect.con.setAutoCommit(false);
                r = st.executeQuery(sql);
                int count = 0;
                while(r.next()) count++;
                r = st.executeQuery(sql);
                Object[][] array=new Object[count][5];
                count = 0;
                while(r.next())
                {
                    array[count][0]=r.getString("Sno");
                    array[count][1]=r.getString("Sname");
                    array[count][2]=Integer.valueOf(r.getInt("Sage"));
                    array[count][3]=r.getString("Ssex");
                    array[count][4]=r.getString("Sdept");
                    count++;
                }
                table = new JTable(array, columnNames);
                Connect.con.commit();
                Connect.con.close();
            } catch (SQLException e) {
                Connect.con.rollback();
                throw new RuntimeException(e);
            }
        }
        // 同一年级学生平均成绩查询
        else if(type == 5)
        {
            String s = Vague_Query.s;
            String[] columnNames = {"学号", "姓名", "平均分"};
            String sql="SELECT  SC.Sno,Student.Sname,AVG(SC.Grade)  AS A_Grade FROM SC, Student WHERE SC.Sno = Student.Sno AND SC.Sno LIKE \"" + s +"%\"GROUP BY SC.Sno\n";
            try {
                Connect.con.setAutoCommit(false);
                r = st.executeQuery(sql);
                int count = 0;
                while(r.next()) count++;
                r = st.executeQuery(sql);
                Object[][] array=new Object[count][3];
                count = 0;
                while(r.next())
                {
                    array[count][0]=r.getString("Sno");
                    array[count][1]=r.getString("Sname");
                    array[count][2]=Double.valueOf(r.getDouble("A_Grade"));
                    count++;
                }
                table = new JTable(array, columnNames);
                Connect.con.commit();
                Connect.con.close();
            } catch (SQLException e) {
                Connect.con.rollback();
                throw new RuntimeException(e);
            }
        }
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(27, 15, 600, 360);
        // 添加 滚动面板 到 内容面板
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        // 设置 内容面板 到 窗口
        jf.setContentPane(panel);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
//    public static void main(String[] args) {
//        StudentQuery(1);
//    }
}

