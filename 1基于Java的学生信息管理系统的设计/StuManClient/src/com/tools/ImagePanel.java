//可以动态加载一个图片的JPanel
package com.tools;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
public class ImagePanel extends JPanel 
{

	Image im;
	//指定Panel的大小
	public ImagePanel(Image im)
	{
		this.im=im;
		//希望背景panel是自适应
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w, h);
	}
	//画出背景,画组件的用paintComponent，画整个界面的用paint（）
	public void paintComponent(Graphics g)
	{
		//清屏
		super.paintComponent(g);
		g.drawImage(im, 0, 0,this.getWidth(),this.getHeight(),this);
	}	
}
