package prog3hazi;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class HostileCharacterTest {

	@Test
	public void testDead() {
		try {
			Level level = new Level(new ScaledGifImage("mainmenu.gif",200,200),new GameWindow());
			HostileCharacter character = new HostileCharacter(new ScaledGifImage("hostile.gif",200,200),level);
			for(int i=0;i<4;i++) {
				character.gotHit();
			}
			assertEquals("meghalt",false,character.alive);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
