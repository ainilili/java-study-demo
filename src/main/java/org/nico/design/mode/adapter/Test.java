package org.nico.design.mode.adapter;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		RobotAlpha alpha = new RobotAlphaAdapter(new RobotBetaProduct());
		Scanner sc = new Scanner(System.in);
		String input;
		
		while(sc.hasNextLine()) {
			input = sc.nextLine();
			String answer = alpha.dialogue(input);
			System.out.println(answer);
		}
		
		sc.close();
	}
}
