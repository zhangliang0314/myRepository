package hanoi;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Scrollbar;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HanoiFrame {
	public static void main(String[] args) {
		new MyFrame();
	}

}

class MyFrame extends Frame implements ActionListener{	
	GamePanel panel;	
	Button pre, next, init, start, pause;	
	TextField input;	
	Label label;
        Label label2;
	Panel menu,menu2,menu3;	
        Scrollbar speed;
        JSlider slider,num;
        boolean jud;
        int s = 500;
	int a[][], n = 5, i = 0;	
	public MyFrame()	
	{		
		super("汉诺塔");		
		a = new int[(int)Math.pow(2, n)][2];	
                speed = new Scrollbar(Scrollbar.HORIZONTAL,500,1000,100,1000);
                speed.setUnitIncrement(5);
                speed.setBlockIncrement(10);
                slider = new JSlider(100,900,500);
                num = new JSlider(1,9,5);
                num.setPreferredSize(new Dimension(100, 50));
           //     num.setOrientation(SwingConstants.VERTICAL);
                num.setMajorTickSpacing(4);
                num.setMinorTickSpacing(1);
                num.setPaintTicks(true);
                num.setPaintLabels(true);
                slider.setMajorTickSpacing(100);
                slider.setPreferredSize(new Dimension(150, 50));
             //   slider.setOrientation(SwingConstants.VERTICAL);
                slider.setMajorTickSpacing(200);
                slider.setMinorTickSpacing(20);
                slider.setPaintTicks(true);
           //     slider.setPaintLabels(true);
		panel = new GamePanel(n);
                start = new Button("开始");
		pre = new Button("上一步");		
		next = new Button("下一步");
                pause = new Button("暂停");
		init = new Button("重置");		
		label = new Label("盘子个数");	
                
                label2 = new Label("播放速度");
		input = new TextField("5", 8);		
		menu = new Panel();
                menu2 = new Panel();
                menu3 = new Panel();
                slider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                    s = slider.getValue();
                     }
                });   
                start.addActionListener(this);
                pause.addActionListener(this);
		pre.addActionListener(this);		
		next.addActionListener(this);		
		init.addActionListener(this);
                menu.add(label2);
                menu.add(slider);
		menu.add(label);
                
		menu.add(num);
                menu.add(start);
		menu.add(pre);		
		menu.add(next);	
                menu.add(pause);
		menu.add(init);  
                
            
                
		add(panel, BorderLayout.CENTER);		
		add(menu, BorderLayout.SOUTH);
                add(menu2, BorderLayout.WEST);
                add(menu3, BorderLayout.EAST);
                num.addChangeListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                            n = num.getValue();
                            remove(panel);			
			panel = new GamePanel(n);			
			add(panel, BorderLayout.CENTER);			
			validate();			
			a = new int[(int)Math.pow(2, n)][2];			
			i = 0;			
			hanoi(n, 0, 1, 2);			
			i = 0;	
                            }
                        });	
		addWindowListener(new WindowAdapter()		
		{			
			public void windowClosing(WindowEvent e)		
			{			
				System.exit(0);		
				}		
			});  		
		setBounds(300, 100, 800, 300);		
		setVisible(true);		
		validate();  		
		hanoi(n, 0, 1, 2);		
		i = 0;  	
		}	
	public void actionPerformed(ActionEvent e)	
	{		
		if (e.getSource() == init)		
		{			
			jud =false;
                     
			remove(panel);			
			panel = new GamePanel(n);			
			add(panel, BorderLayout.CENTER);			
			validate();			
			a = new int[(int)Math.pow(2, n)][2];			
			i = 0;			
			hanoi(n, 0, 1, 2);			
			i = 0;		
			
                }

		else if (e.getSource() ==start)
                {   
                    jud =true;
                    new Thread(new Runnable() {			
                        public void run() {                       
                            while (jud==true)				
                            {
                                repaint();
                                try {
                                        Thread.sleep(s);
                                    } 
                                catch (Exception e2) {    
                                    }
                                if(jud == true){                           
                                    panel.moveDish(a[i][0], a[i][1]);
                                    i++;                         
                                    }                                                               
                            }	
		        }
		    }
		).start();
                 
                }   

                else if(e.getSource()==pause)
                    jud =false;
                
                else    if(e.getSource() == next)		
		{			
			if (i >= (int)Math.pow(2, n) - 1)				
				return;			
			
			panel.moveDish(a[i][0], a[i][1]);			
			i++;		
			}		
		else		
		{	
                    if(e.getSource() == pre)
                    {
			if (i <= 0)				
				return;			
			i--;		
			panel.moveDish(a[i][1], a[i][0]);
                    }
			}	
		}
        
	private void hanoi(int n, int x, int y, int z)	
	{		
		if (n == 1)		
		{			
			a[i][0] = x;			
			a[i][1] = z;			
			i++;		
			}		
		else		
		{			
			hanoi(n - 1, x, z, y);			
			a[i][0] = x; 
			a[i][1] = z; 
			i++;			
			hanoi(n - 1, y, x, z);		
			}	
		}
	}

                                





