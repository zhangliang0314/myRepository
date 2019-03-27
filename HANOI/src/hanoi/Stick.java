package hanoi;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


//stick
public class Stick extends Canvas{
	Dish[] dishs;
	int dishNum;
	Color color;
	Stick(int n,Color c){
		dishNum = 0;
		color = c;
		dishs = new Dish[n];
	}
	public void addDish(Dish dish) {
		dishs[dishNum] = dish;
		dishNum++;
	}
	public Dish moveDish() {
		dishNum--;
		Dish dish = dishs[dishNum];
		dishs[dishNum] = null;
		return dish;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect((getSize().width)/2-5, (int)(getSize().height*0.1), 10, (int)(getSize().height*0.8));
		g.fillRect((getSize().width)/10, (int)(getSize().height*0.8+10), (getSize().width)/5*4, (int)(getSize().height*0.09));
		for(int i=0;i<dishNum;i++) {
			g.setColor(dishs[i].color);
			dishs[i].setLocation(new Point((getSize().width)/2,(int)(getSize().height*0.8-i*10)));
			dishs[i].Draw(g);
		}
	}
}