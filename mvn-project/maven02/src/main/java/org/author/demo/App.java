package org.author.demo;
 
public class App {
 
	public static void main(String[] args) {
		final ColorQuiz quiz = new ColorQuiz();
 
		final int repNumber = 10;
		int numberOfGuesses = 0;
 
		System.out.println("The guess counter should match the number of guesses.");
		for (ColorQuiz.Color color : ColorQuiz.Color.values()) {
 
			System.out.println("Check " + color + " " + repNumber + " times...");
			for (int i = 0; i < repNumber; ++i) {
				if (quiz.guessTheColor(color)) {
					++numberOfGuesses;
				}
			}
 
			final int expected = numberOfGuesses;
			final int actual = quiz.getGuessCounter();
 
			System.out.println("The guess counter: " + expected);
			System.out.println("The number of guesses: " + actual);
 
			if (expected != actual) {
				System.out.println("Error!");
			} else {
				System.out.println("Ok.");
			}
 
			assert expected == actual : "Failed! " + expected + " is expected but " + actual + " is actual";
 
			numberOfGuesses = 0;
			quiz.reset();
		}
	}
}