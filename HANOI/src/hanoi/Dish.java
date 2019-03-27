package hanoi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

//dish
public class Dish{
	Point location;

	int level;
	Color color;
	Dish(int l){
		level = l;
		Random rand = new Random();
		color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)); //�������������ɫ
	}
	public void setLocation(Point p) {
		location = p;
	}
	public void Draw(Graphics g) {
		g.fillRect(location.x-(40+level*20)/2, location.y, 40+level*20, 10);
	}
}