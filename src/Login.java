import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.*;


public class Login  extends JFrame implements ActionListener
{
    private TextField tx1, tx2;
    private JPasswordField jp;   //密码框

    //static int type;
    // 真实验证码
    private String code_get;
    private JButton jb1,jb2;

    private ComponentEvent event;

    private BackgroundPanel bg1, bg2, bg3;
    static String s1,s2,s3;

    public Login()
    {

        super("登录界面");

        this.setBounds(500,250,580,400);
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(2,1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 三个背景图片
        bg1 = new BackgroundPanel((new ImageIcon("images/b1.jpg")).getImage());
        bg2 = new BackgroundPanel((new ImageIcon("images/b2.jpg")).getImage());
        bg3 = new BackgroundPanel((new ImageIcon("images/back.jpg")).getImage());

        JPanel pl_table = new JPanel();
        pl_table.setSize(300, 280);
        pl_table.setLayout(new GridLayout(5,1));

        //第一行
        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(1,2));
        JLabel jl1 = new JLabel("账号",JLabel.CENTER);
        jl1.setFont(new Font(null, Font.PLAIN, 15));
        jp1.add(jl1);
        this.tx1=new TextField(15);
        this.tx1.setBackground(new Color(232,240,254));
        this.tx1.setFont(new Font(null, Font.BOLD, 28));
        jp1.add(this.tx1);
        pl_table.add(jp1);
        //第二行
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1,2));
        JLabel jl2 = new JLabel("密码",JLabel.CENTER);
        jl2.setFont(new Font(null, Font.PLAIN, 15));
        jp2.add(jl2);
        this.jp=new JPasswordField(15);
        this.jp.setBackground(new Color(232,240,254));
        this.jp.setFont(new Font(null, Font.BOLD, 20));
        jp2.add(this.jp);
        pl_table.add(jp2);
        //第三行
        JPanel jp3 = new JPanel();
        jp3.setLayout(new GridLayout(1,2));
        JLabel jl3 = new JLabel("验证码",JLabel.CENTER);
        jl3.setFont(new Font(null, Font.PLAIN, 15));
        jp3.add(jl3);
        this.tx2=new TextField(15);

        this.tx2.setBackground(new Color(232,240,254));
        this.tx2.setFont(new Font(null, Font.BOLD, 28));
        jp3.add(this.tx2);
        pl_table.add(jp3);
        //第四行
        Object[] objs = Code_Picture.createImage();
        code_get = objs[0].toString();
        JLabel label_cp = new JLabel();
        ImageIcon img = new ImageIcon((BufferedImage)objs[1]);
        label_cp.setIcon((Icon)img);
        JPanel jp44 = new JPanel();
        //jp4.add(new JLabel("点击验证码可刷新",JLabel.CENTER));
        jp44.add(label_cp);
        JPanel jp4 = new JPanel();
        jp4.setLayout(new GridLayout(1,2));
        JLabel jl4 = new JLabel("单击验证码可刷新",JLabel.CENTER);
        jl4.setFont(new Font(null, Font.PLAIN, 15));
        jp4.add(jl4);
        jp4.add(jp44);
        pl_table.add(jp4);
        System.out.println(code_get);

        //验证码图片单击事件
        jp44.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 1){
                    //重新获取验证码
                    getPicture(label_cp,jp44);
                    System.out.println(code_get);
                }
            }
        });

        // 第五行
        JPanel jp5 = new JPanel();
        jp5.setLayout(new GridLayout(1,2));
        this.jb1 =new JButton("学生登陆") ;
        jb1.setFont(new Font(null, Font.PLAIN, 15));
        jp5.add(this.jb1);
        this.jb1.addActionListener(this );
        this.jb2 =new JButton("教师登陆") ;
        jb2.setFont(new Font(null, Font.PLAIN, 15));
        jp5.add(this.jb2);
        this.jb2.addActionListener(this );
        pl_table.add(jp5);

        //this.add(bg1);
        //this.add(bg2);
        this.add(bg3);
        this.add(pl_table);

        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//退出系统
            }
        });
    }


    public void componentMoved(ComponentEvent e) {	}

    public void componentShown(ComponentEvent e) {	}

    public void componentHidden(ComponentEvent e) {  }

    public void actionPerformed(ActionEvent e)
    {
        s1 = tx1.getText();
        s2 = new String(jp.getPassword());
        s3 = tx2.getText();
        if(!s1.equals("") && !s2.equals("") && !s3.equals(""))
        {
            System.out.println(s3);
            System.out.println(code_get);
            //System.out.println(s3.equals(code_get));
            if(!s3.equals(code_get))
            {
                JOptionPane.showMessageDialog(this,"验证码输入有误，请检查输入！");
            }
            else
            {
                try
                {
                    Statement st = Connect.doConnect();
                    if(e.getSource() == jb1)    //学生登录
                    {
                        // this.type = 0;
                        if(Check_Code.CheckCode(s1,s2,0))
                        {
                            JOptionPane.showMessageDialog(this,"亲爱的同学，欢迎使用教务系统！");
                            //System.out.println("学生登陆成功！");
                            new Student();
                            dispose();
                        }
                        else JOptionPane.showMessageDialog(this,"用户名或密码输入有误，请检查输入！");
                    }
                    else if(e.getSource()==jb2)    //教师登录
                    {
                        //this.type = 1;
                        if(Check_Code.CheckCode(s1,s2,1))
                        {
                            JOptionPane.showMessageDialog(this,"亲爱的老师，欢迎使用教务系统！");
                            //System.out.println("老师登陆成功！");
                            new Teacher();
                            dispose();
                        }
                        else JOptionPane.showMessageDialog(this,"用户名或密码输入有误，请检查输入！");
                    }
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,"用户名、密码、验证码不能为空，请检查输入！");
        }
    }
    //添加图片，获取验证码
    public void getPicture(JLabel label,JPanel panel)
    {
        Object[] obj = Code_Picture.createImage();
        code_get = obj[0].toString();
        ImageIcon img = new ImageIcon((BufferedImage)obj[1]);//创建图片对象
        label.setIcon((Icon)img);
        panel.add(label);
    }
    public static void main(String[] args)
    {
        //showMessageDialog(null,"登录名为自己的学号或职工号，初始密码为123，请登陆后及时修改密码！");
        new Login();
    }
}
