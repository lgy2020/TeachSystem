import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;


public class Student  extends JFrame implements ActionListener
{
    private JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7;

    public Student()
    {
        super("学生登录界面");
        this.setBounds(500,300,500,240);
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(1,3));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Calendar calendar = Calendar.getInstance(); // get current instance of the calendar
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String time = formatter.format(calendar.getTime());

        //第一列
        this.jb1 =new JButton("个人信息") ;
        this.jb1.addActionListener(this );

        this.jb2 =new JButton("成绩") ;
        this.jb2.addActionListener(this );

        this.jb3 =new JButton("导出成绩");
        this.jb3.addActionListener(this );

        this.jb4 =new JButton("本学期课表") ;
        this.jb4.addActionListener(this );

        this.jb5 =new JButton("选课") ;
        this.jb5.addActionListener(this );

        this.jb6 =new JButton("修改密码") ;
        this.jb6.addActionListener(this );

        this.jb7 =new JButton("退出") ;
        this.jb7.addActionListener(this );

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(7,1));
        jp1.add(jb1);
        jp1.add(jb2);
        jp1.add(jb3);
        jp1.add(jb4);
        jp1.add(jb5);
        jp1.add(jb6);
        jp1.add(jb7);
        // 第二列
        JPanel jp2 = new JPanel();
        JLabel jl1 = new JLabel("今天是：" + time,JLabel.CENTER);
        jl1.setFont(new Font(null, Font.BOLD, 15));
        jp2.add(jl1);


        // 第三列
        JPanel jp3 = new JPanel();
        JLabel jl2 = new JLabel("系统公告",JLabel.CENTER);
        jl2.setFont(new Font(null, Font.BOLD, 15));
        jp3.add(jl2);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if(e.getSource() == jb1)
            {
                Student_Query.StudentQuery(1);
                //dispose();
                //System.out.println("jb1");
            }
            else if(e.getSource() == jb2)
            {
                Student_Query.StudentQuery(2);
                //dispose();
                //System.out.println("jb2");
            }
            else if(e.getSource() == jb3)
            {
                new OutPut();
            }
            else if(e.getSource() == jb4)
            {
                Student_Query.StudentQuery(3);
                //dispose();
                //System.out.println("jb3");
            }
            else if(e.getSource() == jb5)
            {
                Student_Query.StudentQuery(4);
                //dispose();
                //System.out.println("jb4");
            }
            else if(e.getSource() == jb6)
            {
                Change_Code t = new Change_Code(0);
                //System.out.println("jb5");
            }
            else if(e.getSource() == jb7)
            {
                new Login();
                dispose();
                //System.out.println("jb6");
            }
        }
        catch(Exception w)
        {
            w.printStackTrace();
        }
    }

     //public static void main(String[] args) {  new Student();}
}

