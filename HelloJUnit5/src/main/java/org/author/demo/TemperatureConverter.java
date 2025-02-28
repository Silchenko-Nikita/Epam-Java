package org.author.demo;

public class TemperatureConverter {
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) * 5.0 / 9.0;
    }

    public static void main(String[] args) {
        System.out.println("From Fahrenheit...");
        double[] fahrenheitArray = { 32.0, 0.0, -459.67, 50.0, 212.0, 14.0, -148.0 };
        for (double fahrenheit : fahrenheitArray) {
            System.out.println(fahrenheit + " to Celsius: " + fahrenheitToCelsius(fahrenheit));
            System.out.println(fahrenheit + " to Kelvin: " + fahrenheitToKelvin(fahrenheit));
        }
    }
}
