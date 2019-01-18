package org.nico.design.mode.test;

import java.util.Scanner;

import org.junit.Test;
import org.nico.design.mode.adapter.RobotAlpha;
import org.nico.design.mode.adapter.RobotAlphaAdapter;
import org.nico.design.mode.adapter.RobotBetaProduct;

public class AdapterTest {
    
    @Test
	public void test() {
		
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
