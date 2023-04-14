import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Select_Course extends JFrame implements ActionListener
{
    private TextField tx1;
    private JButton jb1, jb2;

    public Select_Course()
    {
        super("选课");
        this.setBounds(560,460,420,110);
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(2,2));
        this.setVisible(true);

        //第一行
        JLabel jl1 = new JLabel("课程号",JLabel.CENTER);
        jl1.setFont(new Font(null, Font.PLAIN, 15));
        this.add(jl1);
        this.tx1=new TextField(15);
        tx1.setFont(new Font(null, Font.BOLD, 20));
        this.add(this.tx1);
        //第二行
        this.jb1 =new JButton("选课");
        this.jb2 =new JButton("退课");
        jb1.setFont(new Font(null, Font.PLAIN, 15));
        jb2.setFont(new Font(null, Font.PLAIN, 15));
        this.add(this.jb2);
        this.add(this.jb1);
        this.jb1.addActionListener(this);
        this.jb2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = tx1.getText();
        if(s.equals(""))
        {
            JOptionPane.showMessageDialog(this,"输入不能为空，请检查输入！");
        }
        else
        {
            try
            {
                Statement st = Connect.doConnect();
                Connect.con.setAutoCommit(false);
                ResultSet r;

                if(e.getSource()== jb1)   //选课
                {
                    String sql="Select * from Course where Cno=\""+s+"\"";
                    //从Course表中查询是否有与输入中所对应的课序号
                    r=st.executeQuery(sql);
                    if(r.next())
                    {
                        String sql2="Select * from SC where Sno=\""+Login.s1+"\" and Cno=\""+s+"\"";
                        //检查该学生是否已选过该课程
                        r=st.executeQuery(sql2);
                        if(r.next())
                        {
                            JOptionPane.showMessageDialog(this,"你已选过该课程，请勿重复选择！");
                        }
                        else
                        {
                            String s1="insert into SC values(\'"+Login.s1+"\',\'"+s+"\',\'0\') ";     //在SC表中插入选课信息
                            int a = st.executeUpdate(s1);
                            String s2="Update Course set Cnum = Cnum-1  where Cno = \""+s+"\"";   //在Course表中将对应课序号的课程的课余量减一
                            int b = st.executeUpdate(s2);
                            //executeUpdate的返回值是一个整数，指示受影响的行数（即更新计数）。
                            if(a != 0 && b != 0)
                            {
                                JOptionPane.showMessageDialog(this,"选课成功！");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(this,"选课失败，请检查输入！");
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "该课程不存在，请重新选择！");
                    }
                }
                else if(e.getSource() == jb2)
                {
                    String sql="Select * from Course where Cno=\""+s+"\"";
                    //从Course表中查询是否有与输入中所对应的课序号
                    r=st.executeQuery(sql);
                    if(r.next())
                    {
                        String sql2="Select * from SC where Sno=\""+Login.s1+"\" and Cno=\""+s+"\"";
                        //检查该学生是否已选过该课程
                        r=st.executeQuery(sql2);
                        if(r.next())
                        {
                            String s1="DELETE FROM SC WHERE Sno =\""+Login.s1+"\"" + "AND Cno = " + "\"" + s + "\"";     //在SC表中插入选课信息
                            int a = st.executeUpdate(s1);
                            String s2="Update Course set Cnum = Cnum + 1  where Cno = \""+s+"\"";   //在Course表中将对应课序号的课程的课余量减一
                            int b = st.executeUpdate(s2);
                            //executeUpdate的返回值是一个整数，指示受影响的行数（即更新计数）。
                            if(a != 0 && b != 0)
                            {
                                JOptionPane.showMessageDialog(this,"退课成功！");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(this,"退课失败，请检查输入！");
                            }

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this,"你还没选过该课程，请重新选择！");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "该课程不存在，请重新选择！");
                    }
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

    //public static void main(String[] args) { new Select_Course();	}
}

