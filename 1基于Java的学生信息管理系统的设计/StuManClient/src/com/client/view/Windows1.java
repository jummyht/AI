/*
 * ����ϵͳ����Ա���������ܽ���Ľ���
 * ��ɽ����˳�򣬴��ϵ��£�������
 * 1��
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

	//������Ҫ�����
	//JFrame����,����ͼƬ
	Image titleIcon=null,timeGg;
	//�˵���
	JMenuBar jmb;
	//һ���˵�����6��
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;
	//���ڵ�һ�е�5�������˵�
	JMenuItem jmm1,jmm2,jmm3,jmm4,jmm5;
	//���ڵ�һ�е�5�������˵���ͼ��
	ImageIcon jmm1_icon,jmm2_icon,jmm3_icon,jmm4_icon,jmm5_icon;
	//������
	JToolBar jtb;
	//������һ����ʮ����ť
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//��Ҫ��������
	JPanel p1,p2,p3,p4,p5;
	//���ʱ��ı�ǩ
	JLabel timeNow;
	
	//����м�ͼƬp1�Ķ����8����ǩ
	JLabel p1_lab1,p1_lab2,p1_lab3,p1_lab4,p1_lab5,p1_lab6,p1_lab7,p1_lab8;
	//��p2��嶨����Ҫ��JLable��������ͷ����
	JLabel p2_lab1, p2_lab2;
	//javax.swing���е�Timer���Զ�ʱ����Action�¼�
	javax.swing.Timer t;
	//����м�ͼƬpanel�Ķ��󣬷���p1����
	ImagePanel p1_imgPanel;
	JSplitPane jsp1;
	//p2��p3�ǿ�Ƭ����
	CardLayout cardP3=new CardLayout();
	CardLayout cardp2=new CardLayout();
	StuInfo si=null;
	public static void main(String[] args) 
	{
		// TODO �Զ����ɵķ������
		//Windows1 w1=new Windows1();
	}
//	public void getsi(String StuId)
//	{
//		si=new StuInfo(StuId);
//	}

	//��ʼ���м��ĸ�panel
	public void initAllPanels()
	{
		//����p1��壬���ð�����
		p1=new JPanel(new BorderLayout());
		//�½�����ͼƬͼƬ
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
		//�����꣬Ϊ���͹��
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		//ImagePanel����Ҳ��panel��������8*1���񲼾�
		this.p1_imgPanel=new ImagePanel(p1_bg);
		this.p1_imgPanel.setLayout(new GridLayout(8,1));
		//���е�label��Ҫ����ImagePanel����
		//��һ��
		p1_lab1=new JLabel(new ImageIcon("image\\label_1.PNG"));
		p1_lab1.setFont(MyTools.f3);
		p1_imgPanel.add(p1_lab1);
		//����ڶ���JLable
		p1_lab2=new JLabel("ѧ �� �� Ϣ",new ImageIcon("image\\label_2.jpg"),0);
		p1_lab2.setFont(MyTools.f3);
		//Ϊ�������ͼ��
		p1_lab2.setCursor(myCursor);
		//�ø�label������
		p1_lab2.setEnabled(false);
		//����
		p1_lab2.addMouseListener(this);
		p1_imgPanel.add(p1_lab2);
		//������
		p1_lab3=new JLabel("�� �� �� ��",new ImageIcon("image\\label_3.jpg"),0);
		p1_lab3.setFont(MyTools.f3);
		//Ϊ�������ͼ��
		p1_lab3.setCursor(myCursor);
		//�ø�label������
		p1_lab3.setEnabled(false);
		p1_lab3.addMouseListener(this);
		p1_imgPanel.add(p1_lab3);
		//���ĸ�
		p1_lab4=new JLabel("�� ѧ �� ��",new ImageIcon("image\\label_4.jpg"),0);
		p1_lab4.setFont(MyTools.f3);
		//Ϊ�������ͼ��
		p1_lab4.setCursor(myCursor);
		//�ø�label������
		p1_lab4.setEnabled(false);
		p1_lab4.addMouseListener(this);
		p1_imgPanel.add(p1_lab4);
		//�����
		p1_lab5=new JLabel("ѧ �� ѡ ��",new ImageIcon("image\\label_5.jpg"),0);
		p1_lab5.setFont(MyTools.f3);
		//Ϊ�������ͼ��
		p1_lab5.setCursor(myCursor);
		//�ø�label������
		p1_lab5.setEnabled(false);
		p1_lab5.addMouseListener(this);
		p1_imgPanel.add(p1_lab5);
		//������
		p1_lab6=new JLabel("�� ѧ �� ��",new ImageIcon("image\\label_6.jpg"),0);
		p1_lab6.setFont(MyTools.f3);
		//Ϊ�������ͼ��
		p1_lab6.setCursor(myCursor);
		//�ø�label������
		p1_lab6.setEnabled(false);
		p1_lab6.addMouseListener(this);
		p1_imgPanel.add(p1_lab6);
		////���߸�
		p1_lab7=new JLabel("�� �� �� ѯ",new ImageIcon("image\\label_7.jpg"),0);
		p1_lab7.setFont(MyTools.f3);
		//Ϊ�������ͼ��
		p1_lab7.setCursor(myCursor);
		//�ø�label������
		p1_lab7.setEnabled(false);
		p1_lab7.addMouseListener(this);
		p1_imgPanel.add(p1_lab7);
		//�ڰ˸�
		p1_lab8=new JLabel("�� �� �� ��",new ImageIcon("image\\label_8.jpg"),0);
		p1_lab8.setFont(MyTools.f3);
		//Ϊ�������ͼ��
		p1_lab8.setCursor(myCursor);
		//�ø�label������
		p1_lab8.setEnabled(false);
		p1_lab8.addMouseListener(this);
		p1_imgPanel.add(p1_lab8);
		//��ͼƬ������p1��
		p1.add(p1_imgPanel);
				
		//����p2/p3/p4���
		
		cardp2=new CardLayout();
		//p2��Ƭ����
		p2=new JPanel(this.cardp2);
		p2_lab1=new JLabel(new ImageIcon("image\\jp2_left.jpg"));
		p2_lab1.addMouseListener(this);
		
		p2_lab2=new JLabel(new ImageIcon("image\\jp2_right.jpg"));
		p2_lab2.addMouseListener(this);
		//��p2_lab1��p2_lab2���뵽p2��
		//������˳������Ϊ0������Ϊ1
		//˳��С�Ŀ�Ƭ������ʾ
		p2.add(p2_lab1,"0");
		p2.add(p2_lab2,"1");
		
		this.cardP3=new CardLayout();
		//p3�ǿ�Ƭ����
		p3=new JPanel(this.cardP3);
		//�ȸ�p3����һ�������濨Ƭ
		//p3���������
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
		//ͼƬ���
		ImagePanel ip=new  ImagePanel(zhu_image);
		//��ͼƬ�����뵽p3��
		p3.add(ip, "0");
		//��p3����Ӽ���JLable
		//JLabel jl3_1=new JLabel (new ImageIcon("image\\jp3_1.jpg"));
		//����Empinfo����(JPanel)
		//���¹������
		//EmpInfo eInfo=new EmpInfo();
		//p3.add(eInfo, "1");
		
		p3.add(si,"1");
		JLabel jl3_2=new JLabel (new ImageIcon("image\\pyjh.PNG"));
		p3.add(jl3_2,"2");
		JLabel jl3_3=new JLabel (new ImageIcon("image\\jcdg.PNG"));
		p3.add(jl3_3,"3");
		//������ʾ�����JPanel
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
		//p4������
		p4=new JPanel(new BorderLayout());
		//��p2,p3, ���뵽p4
		p4.add(p2,"West");
		p4.add(p3,"Center");
				
		//��һ����ִ��ڷֱ���P1����p4
		//p4���p2��p3
		//�����
		jsp1=new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, true, p1, p4);
		//ָ����ߵ����ռ��������
		jsp1.setDividerLocation(150);
		//�ѱ߽�������Ϊ10
		jsp1.setDividerSize(10);
	}
	//��ʼ��������
	public void initToolBar()
	{
		//�������������
		jtb=new JToolBar();
		//���ù��������ܸ���
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
		//�Ѱ�ť����jtb
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
	//��ʼ���˵�
	public void initMenu()
	{
		//����ͼ��
		jmm1_icon=new ImageIcon("image/jm1_icon1.jpg");
		jmm2_icon=new ImageIcon("image/jm1_icon2.jpg");
		jmm3_icon=new ImageIcon("image/jm1_icon3.jpg");
		jmm4_icon=new ImageIcon("image/jm1_icon4.jpg");
		jmm5_icon=new ImageIcon("image/jm1_icon5.jpg");
		//����һ���˵�
		jm1=new JMenu("ѧ����Ϣ");
		jm1.setFont(MyTools.f1);
		//���������˵�
		jmm1=new JMenuItem("��Ϣά��",jmm1_icon);
		jmm1.setFont(MyTools.f2);
		jmm2=new JMenuItem("�޸�����",jmm2_icon);
		jmm2.setFont(MyTools.f2);
		jmm3=new JMenuItem("ѡ��ʦ",jmm3_icon);
		jmm3.setFont(MyTools.f2);
		jmm4=new JMenuItem("���˿α�",jmm4_icon);
		jmm4.setFont(MyTools.f2);
		jmm5=new JMenuItem("��  ��",jmm5_icon);
		jmm5.setFont(MyTools.f2);
		jmm5.addActionListener(this);
		jmm5.setActionCommand("exit");
		//����Ѷ����˵�����һ���˵�
		jm1.add(jmm1);
		jm1.add(jmm2);
		jm1.add(jmm3);
		jm1.add(jmm4);
		jm1.add(jmm5);
				
		jm2=new JMenu("�����ƻ�");
		jm2.setFont(MyTools.f1);
		jm3=new JMenu("��ѧ���");
		jm3.setFont(MyTools.f1);
		jm4=new JMenu("ѧ��ѡ��");
		jm4.setFont(MyTools.f1);
		jm5=new JMenu("��ѧ����");
		jm5.setFont(MyTools.f1);
		jm6=new JMenu("����");
		jm6.setFont(MyTools.f1);
				
		//��һ���˵����뵽JMenuBar
		jmb=new JMenuBar();
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jmb.add(jm5);
		jmb.add(jm6);
		//��JMenuBar�ӵ�JFrame
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//���ó�ʼ���˵�����
		this.initMenu();
		//���ó�ʼ������������
		this.initToolBar();
		si=new StuInfo(ms);
		//���ó�ʼ���м����ĺ���
		this.initAllPanels();
		
		//����p5���,���ñ߽粼��
		p5=new JPanel(new BorderLayout());
		//����Timer
		t=new Timer(1000,this);//ÿ��1000ms����ActionEvent�¼�
		//������ʱ��
		t.start();
		timeNow=new JLabel(Calendar.getInstance().getTime().toLocaleString()+"          ");
		timeNow.setFont(MyTools.f1);
		try 
		{
			timeGg=ImageIO.read(new File("image/time_bg.jpg"));
		} 
		catch (IOException e) 
		{
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		ImagePanel ip1=new ImagePanel(timeGg);
		ip1.setLayout(new BorderLayout());
		ip1.add(timeNow,"East");
		p5.add(ip1);
		
		//�ѹ���������JFrame
		//��JFrame��ȡ��Container
		//�����������Ǵ���ԭ����JFrame��ԭ����this.add();
		//��������������.add();
		Container ct=this.getContentPane();
		ct.add(jtb,"North");
		ct.add(jsp1,"Center");
		ct.add(p5,"South");
		//���ô�С
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(titleIcon);
		this.setTitle("ѧ����Ϣ����ϵͳ");
		this.setSize(w, h-45);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO �Զ����ɵķ������
		//��ȡ��ǰʱ��
		this.timeNow.setText("��ǰʱ�䣺"+Calendar.getInstance().getTime().toLocaleString()+"          ");
		if(e.getActionCommand().equals("exit"))
		{
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		//�ж��û�����Ǹ�lable
		//��������һ����Ƭp1�ϵ�lab2������ʾp3��Ƭ�ϵĵ�һ������
		if(e.getSource()==this.p1_lab2)
		{
			this.cardP3.show(p3, "1");
		}
		//��������һ����Ƭp1�ϵ�lab3������ʾp3��Ƭ�ϵĵ�2������
		else if(e.getSource()==this.p1_lab3)
		{
			this.cardP3.show(p3, "2");
		}
		//��������һ����Ƭp1�ϵ�lab4������ʾp3��Ƭ�ϵĵ�3������
		else if(e.getSource()==this.p1_lab4)
		{
			this.cardP3.show(p3, "3");
		}
		//��������һ����Ƭp1�ϵ�lab5������ʾp3��Ƭ�ϵĵ�4������
		else if(e.getSource()==this.p1_lab5)
		{
			this.cardP3.show(p3, "4");
		}
		//��������һ����Ƭp1�ϵ�lab6������ʾp3��Ƭ�ϵĵ�5������
		else if(e.getSource()==this.p1_lab6)
		{
			this.cardP3.show(p3, "5");
		}
		//��������һ����Ƭp1�ϵ�lab7������ʾp3��Ƭ�ϵĵ�6������
		else if(e.getSource()==this.p1_lab7)
		{
			this.cardP3.show(p3, "6");
		}
		//����������ļ�ͷ������ʾ���ҵļ�ͷ��ͬʱ����p4��ߴ�С�����أ�Ϊ0
		//����������������һ��
		else if(e.getSource()==this.p2_lab1)
		{
			//����ʾ���ֲ����Ľ���p1��������
			//��ʾ���ҵļ�ͷ
			this.cardp2.show(p2, "1");
			this.jsp1.setDividerLocation(0);
		}
		//���������ҵļ�ͷ������ʾ����ļ�ͷ��ͬʱ����p4��ߴ�С��150����
		else if(e.getSource()==this.p2_lab2)
		{
			//����ʾ���ֲ����Ľ���p1չ������
			//��ʾ����ļ�ͷ
			this.cardp2.show(p2, "0");
			this.jsp1.setDividerLocation(150);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		//����û�ѡ����ĳ��JLabel�������
		if(e.getSource()==this.p1_lab2)
		{
			//��굽��label������ʾ����
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
		// TODO �Զ����ɵķ������
		//����û�ѡ����ĳ������jlabel�����
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
