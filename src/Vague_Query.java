import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Vague_Query extends JFrame implements ActionListener
{
    private TextField tx1;
    private JButton jb1;
    private JLabel jl1;
    static String s;
    public Vague_Query(int type)
    {
        super("信息显示");
        this.setBounds(560,460,420,110);
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(2,1));
        this.setVisible(true);

        //第一行
        JPanel jp1 = new JPanel(new GridLayout(1,2));

        jl1 = new JLabel("课程类别",JLabel.CENTER);
        if(type == 1) jl1 = new JLabel("入学年份",JLabel.CENTER);
        if(type == 2) jl1 = new JLabel("年级",JLabel.CENTER);
        jl1.setFont(new Font(null, Font.PLAIN, 15));
        jp1.add(jl1);
        this.tx1=new TextField(15);
        tx1.setFont(new Font(null, Font.BOLD, 20));
        jp1.add(this.tx1);
        this.add(jp1);
        //第二行
        this.jb1 =new JButton("查询");
        jb1.setFont(new Font(null, Font.PLAIN, 15));
        this.add(this.jb1);
        this.jb1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        this.s = tx1.getText();
        String t = jl1.getText();
        if(this.s.equals("")) JOptionPane.showMessageDialog(this, "输入不能为空！");
        else
        {
            if(t.equals("课程类别")) {
                try {
                    Teacher_Query.TeacherQuery(3);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if(t.equals("入学年份")) {
                try {
                    Teacher_Query.TeacherQuery(4);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if(t.equals("年级")) {
                try {
                    Teacher_Query.TeacherQuery(5);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            dispose();
        }

    }

    //public static void main(String[] args) { new Vague_Query(1);	}
}

