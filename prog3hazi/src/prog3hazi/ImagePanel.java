package prog3hazi;

import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

import java.awt.Dimension;

public class ImagePanel extends JPanel implements Serializable{
	
	ImageLabel l;
	public ImagePanel() {
		super();
		this.setPreferredSize(new Dimension(1280,720));
		this.setLayout(null);
	}
	public ImagePanel(ImageLabel l) {
		super();
		this.l=l;
		this.setPreferredSize(new Dimension(2000,1000));
		this.setLayout(null);
		this.add(l);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}

