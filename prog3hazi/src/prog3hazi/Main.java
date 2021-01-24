package prog3hazi;

import java.io.File;
import java.io.IOException;

public class Main {
	public static void main (String[] args)
	{
		
		try {
			GameWindow window = new GameWindow();
			Menu menu = new Menu(new ScaledGifImage("mainmenu.gif",1280,720),window);
			window.add(menu);
			window.setVisible(true);
			
			window.playAudioOnLoop(new File("zene.wav"));
			
			
			
		} 
		
		catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
