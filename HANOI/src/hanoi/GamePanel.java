package hanoi;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class GamePanel extends Panel{
	Stick[] sticks = new Stick[3];
	Button bt;	
	GamePanel(int n)	
	{		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));		
		sticks[0] = new Stick(n, Color.black);		
		sticks[1] = new Stick(n, Color.red);		
		sticks[2] = new Stick(n, Color.green);		
		for (int i = n - 1; i >= 0; i--)		{			
			sticks[0].addDish(new Dish(i));		
			}		
		add(sticks[0]);		
		add(Box.createHorizontalStrut(10));		
		add(sticks[1]);		
		add(Box.createHorizontalStrut(10));		
		add(sticks[2]);		validate();	}	
	public void paint(Graphics g)	{		
		g.drawRect(10, 20, 100, 110);		
		sticks[0].repaint();		
		sticks[1].repaint();		
		sticks[2].repaint();	}	
	public void moveDish(int a, int b)	{		
		Dish dish = sticks[a].moveDish();		
		sticks[b].addDish(dish);		
		repaint();	}
	}