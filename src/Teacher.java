import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;


public class Teacher  extends JFrame implements ActionListener
{
    private JButton jb1,jb2,jb3,jb4,jb5,jb6, jb7,jb8,jb9,jb10;

    public Teacher()
    {
        super("教师登录界面");
        this.setBounds(500,300,500,334);
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(1,3));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Calendar calendar = Calendar.getInstance(); // get current instance of the calendar
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String time = formatter.format(calendar.getTime());

        //第一列
        this.jb1 =new JButton("个人信息") ;
        this.jb1.addActionListener(this );

        this.jb2 =new JButton("本学期课表") ;
        this.jb2.addActionListener(this );

        this.jb3 =new JButton("同类课程查询") ;
        this.jb3.addActionListener(this );

        this.jb4 =new JButton("同年级学生信息") ;
        this.jb4.addActionListener(this );


        this.jb5 =new JButton("同年级学生平均成绩") ;
        this.jb5.addActionListener(this );

        this.jb6 =new JButton("增加学生成绩") ;
        this.jb6.addActionListener(this );

        this.jb7 =new JButton("修改学生成绩") ;
        this.jb7.addActionListener(this );

        this.jb8 =new JButton("删除学生成绩") ;
        this.jb8.addActionListener(this );

        this.jb9 =new JButton("修改密码") ;
        this.jb9.addActionListener(this );


        this.jb10 =new JButton("退出") ;
        this.jb10.addActionListener(this );


        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(10,1));
        jp1.add(jb1);
        jp1.add(jb2);
        jp1.add(jb3);
        jp1.add(jb4);
        jp1.add(jb5);
        jp1.add(jb6);
        jp1.add(jb7);
        jp1.add(jb8);
        jp1.add(jb9);
        jp1.add(jb10);

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
                Teacher_Query.TeacherQuery(1);
                //dispose();
                //System.out.println("jb1");
            }
            else if(e.getSource() == jb2)
            {
                Teacher_Query.TeacherQuery(2);
                //dispose();
                //System.out.println("jb2");
            }
            else if(e.getSource() == jb3)
            {
                new Vague_Query(0);
                //dispose();
                //System.out.println("jb3");
            }
            else if(e.getSource() == jb4)
            {
                new Vague_Query(1);
                //dispose();
                //System.out.println("jb4");
            }
            else if(e.getSource() == jb5)
            {
                new Vague_Query(2);
                //dispose();
                //System.out.println("jb5");
            }
            else if(e.getSource() == jb6)
            {
                new Insert_Student();
                //dispose();
                //System.out.println("jb6");
            }
            else if(e.getSource() == jb7)
            {
                new Update_Student();
                //dispose();
                //System.out.println("jb7");
            }
            else if(e.getSource() == jb8)
            {
                new Delete_Student();
                //dispose();
                //System.out.println("jb8");
            }
            else if(e.getSource() == jb9)
            {
                Change_Code t = new Change_Code(1);
                dispose();
                //System.out.println("jb11");
            }
            else if(e.getSource() == jb10)
            {
                new Login();
                dispose();
                //System.out.println("jb12");
            }
        }
        catch(Exception w)
        {
            w.printStackTrace();
        }
    }

    //public static void main(String[] args) {  new Teacher();}
}

