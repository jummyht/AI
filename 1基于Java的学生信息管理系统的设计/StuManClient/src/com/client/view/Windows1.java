/*
 * 这是系统管理员、经理、主管进入的界面
 * 完成界面的顺序，从上到下，从左到右
 * 1、
 * */
package com.client.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.Timer;
import javax.imageio.*;
import java.io.*;

import com.common.Message;
import com.tools.*;
import java.util.*;
public class Windows1 extends JFrame implements ActionListener,MouseListener
{

	//定义需要的组键
	//JFrame标题,背景图片
	Image titleIcon=null,timeGg;
	//菜单条
	JMenuBar jmb;
	//一级菜单，共6列
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;
	//属于第一列的5个二级菜单
	JMenuItem jmm1,jmm2,jmm3,jmm4,jmm5;
	//属于第一列的5个二级菜单的图标
	ImageIcon jmm1_icon,jmm2_icon,jmm3_icon,jmm4_icon,jmm5_icon;
	//工具栏
	JToolBar jtb;
	//工具栏一共有十个按钮
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//需要的五个面板
	JPanel p1,p2,p3,p4,p5;
	//存放时间的标签
	JLabel timeNow;
	
	//存放中间图片p1的对象的8个标签
	JLabel p1_lab1,p1_lab2,p1_lab3,p1_lab4,p1_lab5,p1_lab6,p1_lab7,p1_lab8;
	//给p2面板定义需要的JLable，两个箭头左右
	JLabel p2_lab1, p2_lab2;
	//javax.swing包中的Timer可以定时触发Action事件
	javax.swing.Timer t;
	//存放中间图片panel的对象，放在p1上面
	ImagePanel p1_imgPanel;
	JSplitPane jsp1;
	//p2和p3是卡片布局
	CardLayout cardP3=new CardLayout();
	CardLayout cardp2=new CardLayout();
	StuInfo si=null;
	public static void main(String[] args) 
	{
		// TODO 自动生成的方法存根
		//Windows1 w1=new Windows1();
	}
//	public void getsi(String StuId)
//	{
//		si=new StuInfo(StuId);
//	}

	//初始化中间四个panel
	public void initAllPanels()
	{
		//处理p1面板，设置包布局
		p1=new JPanel(new BorderLayout());
		//新建背景图片图片
		Image p1_bg=null;
		try 
		{
			p1_bg=ImageIO.read(new File("image\\jp1_bg.jpg"));
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//定义光标，为手型光标
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		//ImagePanel本身也是panel可以设置8*1网格布局
		this.p1_imgPanel=new ImagePanel(p1_bg);
		this.p1_imgPanel.setLayout(new GridLayout(8,1));
		//所有的label都要放在ImagePanel上面
		//第一个
		p1_lab1=new JLabel(new ImageIcon("image\\label_1.PNG"));
		p1_lab1.setFont(MyTools.f3);
		p1_imgPanel.add(p1_lab1);
		//加入第二个JLable
		p1_lab2=new JLabel("学 籍 信 息",new ImageIcon("image\\label_2.jpg"),0);
		p1_lab2.setFont(MyTools.f3);
		//为光标设置图像
		p1_lab2.setCursor(myCursor);
		//让该label不可用
		p1_lab2.setEnabled(false);
		//监听
		p1_lab2.addMouseListener(this);
		p1_imgPanel.add(p1_lab2);
		//第三个
		p1_lab3=new JLabel("培 养 计 划",new ImageIcon("image\\label_3.jpg"),0);
		p1_lab3.setFont(MyTools.f3);
		//为光标设置图像
		p1_lab3.setCursor(myCursor);
		//让该label不可用
		p1_lab3.setEnabled(false);
		p1_lab3.addMouseListener(this);
		p1_imgPanel.add(p1_lab3);
		//第四个
		p1_lab4=new JLabel("教 学 大 纲",new ImageIcon("image\\label_4.jpg"),0);
		p1_lab4.setFont(MyTools.f3);
		//为光标设置图像
		p1_lab4.setCursor(myCursor);
		//让该label不可用
		p1_lab4.setEnabled(false);
		p1_lab4.addMouseListener(this);
		p1_imgPanel.add(p1_lab4);
		//第五个
		p1_lab5=new JLabel("学 期 选 课",new ImageIcon("image\\label_5.jpg"),0);
		p1_lab5.setFont(MyTools.f3);
		//为光标设置图像
		p1_lab5.setCursor(myCursor);
		//让该label不可用
		p1_lab5.setEnabled(false);
		p1_lab5.addMouseListener(this);
		p1_imgPanel.add(p1_lab5);
		//第六个
		p1_lab6=new JLabel("教 学 评 估",new ImageIcon("image\\label_6.jpg"),0);
		p1_lab6.setFont(MyTools.f3);
		//为光标设置图像
		p1_lab6.setCursor(myCursor);
		//让该label不可用
		p1_lab6.setEnabled(false);
		p1_lab6.addMouseListener(this);
		p1_imgPanel.add(p1_lab6);
		////第七个
		p1_lab7=new JLabel("成 绩 查 询",new ImageIcon("image\\label_7.jpg"),0);
		p1_lab7.setFont(MyTools.f3);
		//为光标设置图像
		p1_lab7.setCursor(myCursor);
		//让该label不可用
		p1_lab7.setEnabled(false);
		p1_lab7.addMouseListener(this);
		p1_imgPanel.add(p1_lab7);
		//第八个
		p1_lab8=new JLabel("考 试 管 理",new ImageIcon("image\\label_8.jpg"),0);
		p1_lab8.setFont(MyTools.f3);
		//为光标设置图像
		p1_lab8.setCursor(myCursor);
		//让该label不可用
		p1_lab8.setEnabled(false);
		p1_lab8.addMouseListener(this);
		p1_imgPanel.add(p1_lab8);
		//把图片面板放入p1中
		p1.add(p1_imgPanel);
				
		//处理p2/p3/p4面板
		
		cardp2=new CardLayout();
		//p2卡片布局
		p2=new JPanel(this.cardp2);
		p2_lab1=new JLabel(new ImageIcon("image\\jp2_left.jpg"));
		p2_lab1.addMouseListener(this);
		
		p2_lab2=new JLabel(new ImageIcon("image\\jp2_right.jpg"));
		p2_lab2.addMouseListener(this);
		//把p2_lab1和p2_lab2加入到p2中
		//并设置顺序，向左为0，向右为1
		//顺序小的卡片会先显示
		p2.add(p2_lab1,"0");
		p2.add(p2_lab2,"1");
		
		this.cardP3=new CardLayout();
		//p3是卡片布局
		p3=new JPanel(this.cardP3);
		//先给p3加入一个主界面卡片
		//p3主界面面板
		Image zhu_image=null;
		try 
		{
			zhu_image = ImageIO.read(new File("image\\bj.jpg"));
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//图片面板
		ImagePanel ip=new  ImagePanel(zhu_image);
		//把图片面板加入到p3中
		p3.add(ip, "0");
		//对p3做添加几个JLable
		//JLabel jl3_1=new JLabel (new ImageIcon("image\\jp3_1.jpg"));
		//创建Empinfo对象(JPanel)
		//人事管理界面
		//EmpInfo eInfo=new EmpInfo();
		//p3.add(eInfo, "1");
		
		p3.add(si,"1");
		JLabel jl3_2=new JLabel (new ImageIcon("image\\pyjh.PNG"));
		p3.add(jl3_2,"2");
		JLabel jl3_3=new JLabel (new ImageIcon("image\\jcdg.PNG"));
		p3.add(jl3_3,"3");
		//创建显示报表的JPanel
//		Chart mychart=new Chart();
//		p3.add(mychart,"4");
		JLabel jl3_4=new JLabel (new ImageIcon("image\\kb.PNG"));
		p3.add(jl3_4, "4");
		
//		JLabel jl3_5=new JLabel (new ImageIcon("image\\bj2.PNG"));
//		p3.add(jl3_5,"5");
//		JLabel jl3_6=new JLabel (new ImageIcon("image\\jp3_6.jpg"));
//		p3.add(jl3_6,"6");
//		JLabel jl3_7=new JLabel (new ImageIcon("image\\jp3_7.jpg"));
//		p3.add(jl3_7,"7");
		//p4包布局
		p4=new JPanel(new BorderLayout());
		//把p2,p3, 加入到p4
		p4.add(p2,"West");
		p4.add(p3,"Center");
				
		//做一个拆分窗口分别存放P1，和p4
		//p4会放p2和p3
		//横向分
		jsp1=new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, true, p1, p4);
		//指定左边的面板占多大的像素
		jsp1.setDividerLocation(150);
		//把边界线设置为10
		jsp1.setDividerSize(10);
	}
	//初始化工具栏
	public void initToolBar()
	{
		//处理工具栏的组键
		jtb=new JToolBar();
		//设置工具栏不能浮动
		jtb.setFloatable(false);
		jb1=new JButton(new ImageIcon("image/jb1.jpg"));
		jb2=new JButton(new ImageIcon("image/jb2.jpg"));
		jb3=new JButton(new ImageIcon("image/jb3.jpg"));
		jb4=new JButton(new ImageIcon("image/jb4.jpg"));
		jb5=new JButton(new ImageIcon("image/jb5.jpg"));
		jb6=new JButton(new ImageIcon("image/jb6.jpg"));
		jb7=new JButton(new ImageIcon("image/jb7.jpg"));
		jb8=new JButton(new ImageIcon("image/jb8.jpg"));
		jb9=new JButton(new ImageIcon("image/jb9.jpg"));
		jb10=new JButton(new ImageIcon("image/jb10.jpg"));
		//把按钮加入jtb
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
		jtb.add(jb7);
		jtb.add(jb8);
		jtb.add(jb9);
		jtb.add(jb10);
	}
	//初始化菜单
	public void initMenu()
	{
		//创建图标
		jmm1_icon=new ImageIcon("image/jm1_icon1.jpg");
		jmm2_icon=new ImageIcon("image/jm1_icon2.jpg");
		jmm3_icon=new ImageIcon("image/jm1_icon3.jpg");
		jmm4_icon=new ImageIcon("image/jm1_icon4.jpg");
		jmm5_icon=new ImageIcon("image/jm1_icon5.jpg");
		//创建一级菜单
		jm1=new JMenu("学籍信息");
		jm1.setFont(MyTools.f1);
		//创建二级菜单
		jmm1=new JMenuItem("信息维护",jmm1_icon);
		jmm1.setFont(MyTools.f2);
		jmm2=new JMenuItem("修改密码",jmm2_icon);
		jmm2.setFont(MyTools.f2);
		jmm3=new JMenuItem("选择导师",jmm3_icon);
		jmm3.setFont(MyTools.f2);
		jmm4=new JMenuItem("个人课表",jmm4_icon);
		jmm4.setFont(MyTools.f2);
		jmm5=new JMenuItem("退  出",jmm5_icon);
		jmm5.setFont(MyTools.f2);
		jmm5.addActionListener(this);
		jmm5.setActionCommand("exit");
		//加入把二级菜单加入一级菜单
		jm1.add(jmm1);
		jm1.add(jmm2);
		jm1.add(jmm3);
		jm1.add(jmm4);
		jm1.add(jmm5);
				
		jm2=new JMenu("培养计划");
		jm2.setFont(MyTools.f1);
		jm3=new JMenu("教学大纲");
		jm3.setFont(MyTools.f1);
		jm4=new JMenu("学期选课");
		jm4.setFont(MyTools.f1);
		jm5=new JMenu("教学评估");
		jm5.setFont(MyTools.f1);
		jm6=new JMenu("帮助");
		jm6.setFont(MyTools.f1);
				
		//把一级菜单加入到JMenuBar
		jmb=new JMenuBar();
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jmb.add(jm5);
		jmb.add(jm6);
		//把JMenuBar加到JFrame
		this.setJMenuBar(jmb);
	}
	public Windows1(Message ms) 
	{
		try 
		{
			titleIcon=ImageIO.read(new File("image\\jiubei.gif"));
		} 
		catch (IOException e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//调用初始化菜单函数
		this.initMenu();
		//调用初始化工具栏函数
		this.initToolBar();
		si=new StuInfo(ms);
		//调用初始化中间面板的函数
		this.initAllPanels();
		
		//处理p5面板,设置边界布局
		p5=new JPanel(new BorderLayout());
		//创建Timer
		t=new Timer(1000,this);//每隔1000ms触发ActionEvent事件
		//启动定时器
		t.start();
		timeNow=new JLabel(Calendar.getInstance().getTime().toLocaleString()+"          ");
		timeNow.setFont(MyTools.f1);
		try 
		{
			timeGg=ImageIO.read(new File("image/time_bg.jpg"));
		} 
		catch (IOException e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ImagePanel ip1=new ImagePanel(timeGg);
		ip1.setLayout(new BorderLayout());
		ip1.add(timeNow,"East");
		p5.add(ip1);
		
		//把工具栏加入JFrame
		//从JFrame中取得Container
		//容器的作用是代替原来的JFrame，原来是this.add();
		//现在是容器对象.add();
		Container ct=this.getContentPane();
		ct.add(jtb,"North");
		ct.add(jsp1,"Center");
		ct.add(p5,"South");
		//设置大小
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(titleIcon);
		this.setTitle("学生信息管理系统");
		this.setSize(w, h-45);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO 自动生成的方法存根
		//获取当前时间
		this.timeNow.setText("当前时间："+Calendar.getInstance().getTime().toLocaleString()+"          ");
		if(e.getActionCommand().equals("exit"))
		{
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		//判断用户点击那个lable
		//如果点击第一个卡片p1上的lab2，则显示p3卡片上的第一个界面
		if(e.getSource()==this.p1_lab2)
		{
			this.cardP3.show(p3, "1");
		}
		//如果点击第一个卡片p1上的lab3，则显示p3卡片上的第2个界面
		else if(e.getSource()==this.p1_lab3)
		{
			this.cardP3.show(p3, "2");
		}
		//如果点击第一个卡片p1上的lab4，则显示p3卡片上的第3个界面
		else if(e.getSource()==this.p1_lab4)
		{
			this.cardP3.show(p3, "3");
		}
		//如果点击第一个卡片p1上的lab5，则显示p3卡片上的第4个界面
		else if(e.getSource()==this.p1_lab5)
		{
			this.cardP3.show(p3, "4");
		}
		//如果点击第一个卡片p1上的lab6，则显示p3卡片上的第5个界面
		else if(e.getSource()==this.p1_lab6)
		{
			this.cardP3.show(p3, "5");
		}
		//如果点击第一个卡片p1上的lab7，则显示p3卡片上的第6个界面
		else if(e.getSource()==this.p1_lab7)
		{
			this.cardP3.show(p3, "6");
		}
		//如果点击向左的箭头，则显示向右的箭头，同时设置p4左边大小（像素）为0
		//看起来就像收起来一样
		else if(e.getSource()==this.p2_lab1)
		{
			//把显示各种操作的界面p1隐藏起来
			//显示向右的箭头
			this.cardp2.show(p2, "1");
			this.jsp1.setDividerLocation(0);
		}
		//如果点击向右的箭头，则显示向左的箭头，同时设置p4左边大小是150像素
		else if(e.getSource()==this.p2_lab2)
		{
			//把显示各种操作的界面p1展开起来
			//显示向左的箭头
			this.cardp2.show(p2, "0");
			this.jsp1.setDividerLocation(150);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		//如果用户选择了某个JLabel，则高亮
		if(e.getSource()==this.p1_lab2)
		{
			//鼠标到达label区域显示高亮
			this.p1_lab2.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab3)
		{
			this.p1_lab3.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab4)
		{
			this.p1_lab4.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab5)
		{
			this.p1_lab5.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab6)
		{
			this.p1_lab6.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab7)
		{
			this.p1_lab7.setEnabled(true);
		}
		else if(e.getSource()==this.p1_lab8)
		{
			this.p1_lab8.setEnabled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		//如果用户选中了某个操作jlabel则禁用
		if(arg0.getSource()==this.p1_lab2)
		{
			this.p1_lab2.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab3)
		{
			this.p1_lab3.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab4)
		{
			this.p1_lab4.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab5)
		{
			this.p1_lab5.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab6)
		{
			this.p1_lab6.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab7)
		{
			this.p1_lab7.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab8)
		{
			this.p1_lab8.setEnabled(false);
		}
	}	
}
