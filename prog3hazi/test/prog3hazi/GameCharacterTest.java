package prog3hazi;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class GameCharacterTest {
	
	@Test
	public void testSetLocation() {
		GameCharacter character;
		try {
			character = new GameCharacter(new ScaledGifImage("character_idle.gif",120,120));
			character.setLocation(120, 150);
			assertEquals("x-nek a megadott erteknek kell lennie", 120, character.getX());
			assertEquals("y-nak a megadott erteknek kell lennie",150, character.getY());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testJumped() {
		GameCharacter character;
		try {
			character = new GameCharacter(new ScaledGifImage("character_idle.gif",120,120));
			character.jumped();
			assertEquals("a karakter epp ugrik", true, character.jumping());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testHitGround() {
		GameCharacter character;
		try {
			character = new GameCharacter(new ScaledGifImage("character_idle.gif",120,120));
			character.hitground();
			assertEquals("a karakter epp ugrik", false, character.jumping());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testImage() {
		GameCharacter character;
		try {
			ScaledGifImage image = new ScaledGifImage("character_idle.gif",120,120);
			character = new GameCharacter(image);
			assertSame("a kepek megegyeznek", character.getImage(), image.getImage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
