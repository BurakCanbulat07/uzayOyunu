package uzayOyunu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates{
	private int x;
	private int y;
	public Ates(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}

public class Oyun extends JPanel implements KeyListener,ActionListener{
	private static final long serialVersionUID = 1L;
	Timer timer = new Timer(5,this);
	private int gecenSure=0;
	private int harcananAtes=0;
	
	private BufferedImage image;

	private ArrayList<Ates> ates = new ArrayList<Ates>();
	
	private int atesdirY=1;
	private int topX=0;
	private int topdirX=2;
	private int UzayGemisiX=400;
	private int dirUzayX=20;
	
	public boolean kontrolEt() {
		for (Ates fire : ates) {
			if (new Rectangle(fire.getX()+14,fire.getY(),10,20).intersects(new Rectangle(topX,0,20,20))) {
				return true;
			}
		}
		return false;
	}
	
	public Oyun() {
		try {
			image = ImageIO.read(new FileInputStream(new File("C:\\\\Users\\\\Lenovo\\\\eclipse-workspace\\\\uzayOyunu\\\\src\\\\uzayOyunu\\\\uzay_gemisi.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBackground(Color.BLACK);
			
		timer.start();
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		gecenSure+=5;
		
		g.setColor(Color.red);
		
		g.fillOval(topX, 0, 20, 20);
		
		g.drawImage(image,UzayGemisiX,490,image.getWidth() / 4,image.getHeight() / 4, this);
		
		
		for(Ates fire : ates) {
			if (fire.getY()<0) {
				ates.remove(fire);
			}
		}
		g.setColor(Color.blue);
			
		for	(Ates fire : ates) {
			g.fillRect(fire.getX()+14,fire.getY(), 10, 20);
		}
		
		if (kontrolEt()) {
			timer.stop();
			String message = "Kazandiniz!!!\n"+
							 "Harcanan Ates : "+harcananAtes+"\n"+
							 "Gecen Sure : "+gecenSure / 1000.0;
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
		}
	}
	
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for(Ates fire : ates) {
			fire.setY(fire.getY()-atesdirY);
		}
		
		
		topX +=topdirX;
		
		if (topX>=750) {
			topdirX=-topdirX;
		}
		if (topX<=0) {
			topdirX=-topdirX;
		}
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
int c = e.getKeyCode();
		
		if (c==KeyEvent.VK_A||c==KeyEvent.VK_LEFT) {
			if (UzayGemisiX<=0) {
				UzayGemisiX=0;
			}else {
				UzayGemisiX-=dirUzayX;
			}
		}else if (c==KeyEvent.VK_D||c==KeyEvent.VK_RIGHT) {
			if (UzayGemisiX>=720) {
				UzayGemisiX=720;
			}else {
				UzayGemisiX+=dirUzayX;
			}
		}else if (c==KeyEvent.VK_SPACE) {
			ates.add(new Ates(UzayGemisiX+15,490));
			
			harcananAtes++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
