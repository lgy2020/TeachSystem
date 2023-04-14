import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Change_Code extends JFrame implements ActionListener {

    TextField tx1, tx2, tx3;
    JButton jb1, jb2;
    int type;
    public Change_Code(int ty)
    {
        super("密码修改");
        type = ty;
        this.setBounds(510,310,600,200);
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(4,2));
        this.setVisible(true);
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);//退出系统
//            }
//        });

        //第一行
        JLabel jl1 = new JLabel("原密码",JLabel.CENTER);
        jl1.setFont(new Font(null, Font.PLAIN, 15));
        this.add(jl1);
        this.tx1=new TextField(15);
        tx1.setFont(new Font(null, Font.BOLD, 20));
        this.add(this.tx1);


        //第二行
        JLabel jl2 = new JLabel("新密码",JLabel.CENTER);
        jl2.setFont(new Font(null, Font.PLAIN, 15));
        this.add(jl2);
        this.tx2=new TextField(15);
        tx2.setFont(new Font(null, Font.BOLD, 20));
        this.add(this.tx2);

        //第三行
        JLabel jl3 = new JLabel("再次输入新密码",JLabel.CENTER);
        jl3.setFont(new Font(null, Font.PLAIN, 15));
        this.add(jl3);
        this.tx3=new TextField(15);
        tx3.setFont(new Font(null, Font.BOLD, 20));
        this.add(this.tx3);

        //第四行
        this.jb1 =new JButton("重置密码");
        this.jb2 =new JButton("确定修改");
        jb1.setFont(new Font(null, Font.PLAIN, 15));
        jb2.setFont(new Font(null, Font.PLAIN, 15));
        this.add(this.jb1);
        this.add(this.jb2);
        this.jb1.addActionListener(this);
        this.jb2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String s1 = tx1.getText();
        String s2 = tx2.getText();
        String s3 = tx3.getText();

        if(e.getSource() == jb1)
        {
            System.out.println("重置密码");
            String sql = "UPDATE Student SET Scode = \"123\" WHERE Sno = \"" + Login.s1 +"\"";
            if(type == 1) sql = "UPDATE Teacher SET Tcode = \"123\" WHERE Tno = \"" + Login.s1 + "\"";
            Statement st = Connect.doConnect();
            int q = 0;
            try {
                Connect.con.setAutoCommit(false);
                q = st.executeUpdate(sql);
                Connect.con.commit();
                Connect.con.close();
            } catch (SQLException ex) {
                try {
                    Connect.con.rollback();
                } catch (SQLException exc) {
                    throw new RuntimeException(exc);
                }
                throw new RuntimeException(ex);
            }
            if(q!=0)
            {
                JOptionPane.showMessageDialog(this,"重置成功，请重新登录！");
                new Login();
                dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"重置失败，请重试！");
            }
        }
        else if(e.getSource() == jb2)
        {
            if(s1.length() <= 0 || s2.length() <= 0 || s3.length() <= 0)
            {
                JOptionPane.showMessageDialog(this,"输入不能为空，请检查输入！");
            }
            else
            {
                try
                {
                    Statement st = Connect.doConnect();
                    Connect.con.setAutoCommit(false);
                    if(s1.equals(Login.s2)) //检验原密码与登录密码是否相同
                    {

                        if(s1.equals(s2))  //检验新密码与原密码是否相同
                        {
                            JOptionPane.showMessageDialog(this,"原密码与新密码不能相同，请重新输入！");
                            tx2.setText(null);
                            tx3.setText(null);
                        }
                        else
                        {

                            if(!s2.equals(s3)) //检验两次输入的新密码是否相同
                            {
                                JOptionPane.showMessageDialog(this,"两次输入的密码不一致，请重新输入！");
                                tx3.setText(null);
                            }
                            else
                            {
                                    String sql  = "update Student  set Scode=\""+s2+"\"  where Sno=\""+Login.s1+"\"";
                                    System.out.println("修改密码");
                                    if(type == 1) //修改教师密码
                                    {
                                        sql = "update Teacher  set Tcode=\""+s2+"\"  where Tno=\""+Login.s1+"\"";
                                    }
                                    int q = st.executeUpdate(sql);
                                    if(q!=0)
                                    {
                                        JOptionPane.showMessageDialog(this,"更改成功，请重新登录！");
                                        new Login();
                                        dispose();
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(this,"更改失败，请重试！");
                                    }
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"原密码输入错误，请检查输入！");
                    }
                    Connect.con.commit();
                    Connect.con.close();
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
            }
        }


    }
    //public static void main(String[] args) { new Change_Code(0);	}
}
