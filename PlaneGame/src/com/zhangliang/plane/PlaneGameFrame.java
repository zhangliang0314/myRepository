package com.zhangliang.plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.ArrayList;

import com.zhangliang.util.GameUtil;
import com.zhangliang.util.MyFrame;

public class PlaneGameFrame extends MyFrame{
	Image bg = GameUtil.getImage("images/bg.jpg");
	Plane p = new Plane("images/plane.png",50,50);
	ArrayList bulletList=new ArrayList();
	
	Explode bao;
	Date startTime;
	Date endTime;
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		p.draw(g);
		for(int i=0;i<bulletList.size();i++) {
			Bullet b=(Bullet)bulletList.get(i);
			b.draw(g);
			
			//检查碰撞
			boolean peng=b.getRect().intersects(p.getRect());
			if(peng) {
				p.setLive(false);
				
				if(bao==null) {
					endTime=new Date();
				 bao = new Explode(p.x, p.y);
				 }
				bao.draw(g);
				break;
			}
		}
		if (!p.isLive()) {
			
			final int period=(int)(endTime.getTime()-startTime.getTime())/1000;
			printInfo(g, "生存时间:"+period+"秒", 20, 120, 260, Color.white);
			switch (period/10) {
			case 0:
			case 1:
				printInfo(g, "菜鸟", 50, 100, 200, Color.white);
				break;
			case 2:
				printInfo(g, "小鸟", 50, 100, 200, Color.white);
				break;
			case 3:
				printInfo(g, "大鸟", 50, 100, 200, Color.white);
				break;
			case 4:
				printInfo(g, "鸟王子", 50, 100, 200, Color.white);
				break;
			case 5:
				printInfo(g, "鸟人", 50, 100, 200, Color.white);
				break;

			default:
				break;
			}
		}
		
	}
	
	public void printInfo(Graphics g,String str,int size,int x,int y,Color color){
		Color c=g.getColor();
		g.setColor(color);
		Font f=new Font("宋体", Font.BOLD, size);
		g.setFont(f);
		g.drawString(str,x,y);
		g.setColor(c);
	}
	public static void main(String[] args) {
		new PlaneGameFrame().launchFrame();
	}
	
	public void launchFrame() {
		super.launchFrame();
		//添加键盘的监听
		addKeyListener(new KeyMonitor());
		//生成一堆子弹
		for(int i=0;i<30;i++) {
			Bullet b=new Bullet();
			bulletList.add(b);
			startTime=new Date();
		}
	}
	//定义为内部类，可以方便的使用外部类的普通属性 
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			p.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
		    p.subDirection(e);
		}
		
	}
	
	
}
