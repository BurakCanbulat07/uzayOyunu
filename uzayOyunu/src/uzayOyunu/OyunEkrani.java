package uzayOyunu;

import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class OyunEkrani extends JFrame{
	ImageIcon logoIcon = new ImageIcon("C:\\\\\\\\Users\\\\\\\\Lenovo\\\\\\\\eclipse-workspace\\\\\\\\uzayOyunu\\\\\\\\src\\\\\\\\uzayOyunu\\\\\\\\uzay_gemisi.png");

	private static final long serialVersionUID = 1L;

	public OyunEkrani(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		setIconImage(logoIcon.getImage());
		
	}

	public static void main(String[] args) {
		OyunEkrani ekran=new OyunEkrani("Uzay Oyunu"); 
		
		ekran.setResizable(false);
		ekran.setFocusable(false);
		
		ekran.setSize(800, 600);
		
		ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Oyun oyun=new Oyun();
		
		oyun.requestFocus();
		
		oyun.addKeyListener(oyun);
		oyun.setFocusable(true);
		oyun.setFocusTraversalKeysEnabled(false);
		
		ekran.add(oyun);
		ekran.setVisible(true);
		
		
	}

	
	


}
