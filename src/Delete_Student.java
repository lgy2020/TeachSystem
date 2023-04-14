import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Delete_Student extends JFrame implements ActionListener
{
    private TextField tx1, tx2;
    private JButton jb1;

    public Delete_Student()
    {
        super("删除学生成绩");
        this.setBounds(560,460,420,135);
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(3,1));
        this.setVisible(true);


        // 第一行
        JPanel jp1 = new JPanel(new GridLayout(1,2));
        JLabel jl1 = new JLabel("学号",JLabel.CENTER);
        jl1.setFont(new Font(null, Font.PLAIN, 15));
        jp1.add(jl1);
        this.tx1=new TextField(15);
        tx1.setFont(new Font(null, Font.BOLD, 20));
        jp1.add(this.tx1);
        this.add(jp1);
        // 第二行
        JPanel jp2 = new JPanel(new GridLayout(1,2));
        JLabel jl2 = new JLabel("课程号",JLabel.CENTER);
        jl2.setFont(new Font(null, Font.PLAIN, 15));
        jp2.add(jl2);
        this.tx2=new TextField(15);
        tx2.setFont(new Font(null, Font.BOLD, 20));
        jp2.add(this.tx2);
        this.add(jp2);

        // 第三行
        this.jb1 =new JButton("确定删除");
        jb1.setFont(new Font(null, Font.PLAIN, 15));
        this.add(this.jb1);
        this.jb1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        String s1 = tx1.getText();
        String s2 = tx2.getText();
        if(s1.equals("") || s2.equals(""))
        {
            JOptionPane.showMessageDialog(this,"输入不能有空，请检查输入！");
        }
        else
        {
            try
            {
                Statement st = Connect.doConnect();
                Connect.con.setAutoCommit(false);
                ResultSet r2;
                String sql2="SELECT Sno, Cno, Grade FROM SC WHERE Sno = \"" + s1 +"\" AND Cno = \"" + s2 + "\"";
                r2=st.executeQuery(sql2);

                if(!r2.next())
                {
                    JOptionPane.showMessageDialog(this, "记录不存在，无法删除，请重新输入！");
                }
                else
                {
                    String sql = "DELETE FROM SC WHERE Sno = \"" + s1 + "\" AND Cno = \"" + s2 + "\"";
                    int q = st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this, "删除成功！");
                }
                Connect.con.commit();
                Connect.con.close();

            }
            catch (SQLException e1)
            {
                try {
                    Connect.con.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                e1.printStackTrace();
            }
        }
    }

    //public static void main(String[] args) { new Delete_Student(); }
}

