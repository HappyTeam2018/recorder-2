package com.cooltrickshome;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class DesktopScreenshot implements MouseListener,
		MouseMotionListener {

	int drag_status = 0;
	int c1;
	int c2;
	int c3;
	int c4;
	JFrame frame=null;
	static int counter=0;
	JLabel background=null;
	static
	{
		System.loadLibrary("MyLogger");
	}
	
	public static native int GetKey();
	

	/**
	 * @param args
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String args[]) throws AWTException, IOException, InterruptedException
	{
		DesktopScreenshot ds=new DesktopScreenshot();
		System.out.println("Press F6 and then drag your mouse to take screenshot. For exiting press F8");
		while(true)
		{
		int d=GetKey();
		if(d==117)
		{
			ds.getImage();
		}
		else
			if(d==119)
			{
				System.exit(0);
			}
		}
	}
	
	public void getImage() throws AWTException, IOException, InterruptedException {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	    Robot robot = new Robot();
	    BufferedImage img = robot.createScreenCapture(new Rectangle(size));
	    ImagePanel panel = new ImagePanel(img);
	    frame=new JFrame();
	    frame.add(panel);
	    frame.setLocation(0, 0);
	    frame.setSize(size);
	    frame.setLayout(new FlowLayout());
	    frame.setUndecorated(true);
	    frame.setVisible(true);
	    frame.addMouseListener(this);
	    frame.addMouseMotionListener(this);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void draggedScreen() throws Exception {
		int w = this.c1 - this.c3;
		int h = this.c2 - this.c4;
		w *= -1;
		h *= -1;
		Robot robot = new Robot();
		BufferedImage img = robot.createScreenCapture(new Rectangle(this.c1,
				this.c2, w, h));
		counter++;
		File save_path = new File("screenshot"+counter+".jpg");
		ImageIO.write(img, "JPG", save_path);
		c1=0;
		c2=0;
		c3=0;
		c4=0;	
		frame.disable();
		frame.dispose();
		new File("desktop.jpg").delete();
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		paint();
		this.c1 = arg0.getX();
		this.c2 = arg0.getY();
	}

	public void mouseReleased(MouseEvent arg0) {
		paint();
		if (this.drag_status == 1) {
			this.c3 = arg0.getX();
			this.c4 = arg0.getY();
			try {
				draggedScreen();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		paint();
		this.drag_status = 1;
		this.c3 = arg0.getX();
		this.c4 = arg0.getY();
	}

	public void mouseMoved(MouseEvent arg0) {
	}

	public void paint() {
		Graphics g = frame.getGraphics();
		frame.repaint();
		int w = this.c1 - this.c3;
		int h = this.c2 - this.c4;
		w *= -1;
		h *= -1;
		if (w < 0) {
			w *= -1;
		}
		g.drawRect(this.c1, this.c2, w, h);
	}
}
