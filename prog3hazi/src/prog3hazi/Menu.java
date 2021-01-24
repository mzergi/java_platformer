package prog3hazi;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;


public class Menu extends ImagePanel implements Serializable{
	private ScaledGifImage background;
	private ScaledGifImage newgame;
	private ScaledGifImage newgame_selected;
	private ScaledGifImage quit;
	private ScaledGifImage quit_selected;
	private ScaledGifImage title;
	
	private JLabel title_label;
	private JLabel background_label;
	private JLabel newgame_label;
	private JLabel quit_label;
	
	private GameWindow window;
	
	
	
	public Menu (ScaledGifImage image, GameWindow window) throws IOException {
		super();
		this.setPreferredSize(new Dimension(1280,720));
		this.setLayout(null);
		background = image;
		this.window = window;
		title = new ScaledGifImage("title.png",434,85);
		newgame=new ScaledGifImage("new_game.png",288,40);
		newgame_selected = new ScaledGifImage("new_game_selected.png",288,40);
		quit = new ScaledGifImage("quit.png",220,40);
		quit_selected = new ScaledGifImage("quit_selected.png",220,40);
		
		title_label = new JLabel(title.getImageIcon());
		newgame_label = new JLabel(newgame.getImageIcon());
		quit_label = new JLabel(quit.getImageIcon());
		background_label = new ImageLabel(background);
		
		this.add(title_label);
		this.add(newgame_label);
		this.add(quit_label);
		this.add(background_label);
	
		title_label.setBounds(428,20,434,85);
		newgame_label.setBounds(506,210,288,40);
		newgame_label.setBorder(BorderFactory.createEmptyBorder());
		quit_label.setBounds(536,510,220,40);
		background_label.setBounds(background.getWidth(),background.getHeight(),background.getWidth(),background.getHeight());
		
	
		
		mouseDragInit();
		
	}
	public void mouseDragInit() {
		newgame_label.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent me) {
				newgame_label.setIcon(newgame_selected.getImageIcon());
			}
			@Override
			public void mouseExited(MouseEvent me) {
				newgame_label.setIcon(newgame.getImageIcon());
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				newgamepressed();
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		quit_label.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent me) {
				
				quit_label.setIcon(quit_selected.getImageIcon());
				
			}
			@Override
			public void mouseExited(MouseEvent me) {
				quit_label.setIcon(quit.getImageIcon());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	public void newgamepressed(
			) {
		try {
			
			window.startLevel(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(title.getImage(),0,0,this);
		g.drawImage(newgame.getImage(),0,0,this);
		g.drawImage(quit.getImage(),0,0,this);
		g.drawImage(background.getImage(),0,0,this);
	}
}
