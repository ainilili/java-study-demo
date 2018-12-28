package org.nico.design.mode.adapter;

public class RobotBetaProduct implements RobotBeta{

	@Override
	public String dialogue(String input) {
		return input.replace("吗", "").replaceAll("[?|？]", "!");
	}

}
