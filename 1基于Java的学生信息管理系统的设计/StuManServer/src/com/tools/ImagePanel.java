//���Զ�̬����һ��ͼƬ��JPanel
package com.tools;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
public class ImagePanel extends JPanel 
{

	Image im;
	//ָ��Panel�Ĵ�С
	public ImagePanel(Image im)
	{
		this.im=im;
		//ϣ������panel������Ӧ
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w, h);
	}
	//��������,���������paintComponent���������������paint����
	public void paintComponent(Graphics g)
	{
		//����
		super.paintComponent(g);
		g.drawImage(im, 0, 0,this.getWidth(),this.getHeight(),this);
	}	
}
