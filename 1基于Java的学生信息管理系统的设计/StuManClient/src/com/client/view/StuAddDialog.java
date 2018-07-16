package com.client.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class StuAddDialog extends JDialog implements ActionListener
{
	//��������Ҫ��swing���
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3;
	
	//���幹�췽��
	//owner���ĸ����ڣ�title���ڱ��⣬modalģ̬���ڻ��Ƿ�ģ̬����
	public StuAddDialog(Frame owner,String title,boolean modal)
	{
		//���ø��๹�췽�����ﵽģ̬�Ի����Ч��
		super(owner,title,modal);
		jl1=new JLabel("ѧ��");
		jl2=new JLabel("����");
		jl3=new JLabel("�Ա�");
		jl4=new JLabel("����");
		jl5=new JLabel("����");
		jl6=new JLabel("ϵ��");
		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf5=new JTextField();
		jtf6=new JTextField();
		
		jb1=new JButton("���");
		jb1.addActionListener(this);
		jb1.setActionCommand("add");
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		jb2.setActionCommand("cancel");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//���ò���Ϊ6��1��
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		//������
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
			
		//չ��
		this.setSize(300,200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO �Զ����ɵķ������
		if(e.getActionCommand().equals("add"))
		{
			//�Ե����Ӱ�ť����Ӧ�Ķ���
			//�������ݿ�
			Connection ct=null;
			ResultSet rs=null;
			PreparedStatement ps=null;
			//�������ݿ�
			try {
				//1����������
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//2���õ�����
				ct=DriverManager.getConnection
				("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Students2","jummyht","1115101321");
				String strsql="insert into stu values(?,?,?,?,?,?)";
				ps=ct.prepareStatement(strsql);
				ps.setString(1,jtf1.getText());
				ps.setString(2,jtf2.getText());
				ps.setString(3,jtf3.getText());
				ps.setInt(4,Integer.parseInt(jtf4.getText()));
				ps.setString(5,jtf5.getText());
				ps.setString(6,jtf6.getText());
				ps.executeUpdate();
				//�رնԻ���
				this.dispose();
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
			finally
			{
				try 
				{
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(ct!=null) ct.close();
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
		//��������ȡ����ť��ֱ�ӹرնԻ���
		else if(e.getActionCommand().equals("cancel"))
		{
			this.dispose();
		}
	}

}
