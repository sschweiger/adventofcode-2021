package org.sschweiger.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;

// https://adventofcode.com/2021/day/2
public class Day2 {

	public static void main(String[] args) {
		var app = new Day2();
		app.run();
		app.run2();
	}

	public void run() {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day2.data"))) {
			int horizontalPosition = 0;
			int verticalPosition = 0;

			String line;
			while ((line = reader.readLine()) != null) {
				var parts = line.split(" ");

				var command = parts[0];
				var value = Integer.parseInt(parts[1]);

				switch (command) {
					case "forward":
						horizontalPosition += value;
						break;
					case "down":
						verticalPosition += value;
						break;
					case "up":
						verticalPosition -= value;
						break;
				}
			}

			System.out.println("results part 1");
			System.out.println("horizontalPosition: " + horizontalPosition);
			System.out.println("verticalPosition: " + verticalPosition);
			System.out.println("result: " + horizontalPosition * verticalPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run2() {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day2.data"))) {
			int horizontalPosition = 0;
			int verticalPosition = 0;
			int aim = 0;

			String line;
			while ((line = reader.readLine()) != null) {
				var parts = line.split(" ");

				var command = parts[0];
				var value = Integer.parseInt(parts[1]);

				switch (command) {
					case "forward":
						horizontalPosition += value;
						verticalPosition += aim * value;
						break;
					case "down":
						aim += value;
						break;
					case "up":
						aim -= value;
						break;
				}
			}

			System.out.println("results part 2");
			System.out.println("horizontalPosition: " + horizontalPosition);
			System.out.println("verticalPosition: " + verticalPosition);
			System.out.println("result: " + horizontalPosition * verticalPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
