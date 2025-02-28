package org.author.demo;
 
public class ColorQuiz {
 
	public static enum Color {
		RED, GREEN, BLUE;
	}
 
	private int guessCounter;
 
	private Color getRandomColor() {
		return Color.values()[(int) (Math.random() * Color.values().length)];
	}
 
	public int getGuessCounter() {
		return guessCounter;
	}
 
	public int reset() {
		final int current = guessCounter;
		guessCounter = 0;
		return current;
	}
 
	public boolean guessTheColor(Color expectedColor) {
		final Color actualColor = getRandomColor();
		final boolean result = expectedColor == actualColor;
		if (result) {
			++guessCounter;
		}
		return true;
	}
}