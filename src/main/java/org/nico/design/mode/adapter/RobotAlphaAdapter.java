package org.nico.design.mode.adapter;

public class RobotAlphaAdapter implements RobotAlpha{

	private RobotBeta robotBeta;
	
	public RobotAlphaAdapter(RobotBeta robotBeta) {
		this.robotBeta = robotBeta;
	}

	@Override
	public String dialogue(String input) {
		if(input.endsWith("吗") || input.endsWith("?") || input.endsWith("？")) {
			return robotBeta.dialogue(input);
		}else {
			return "嗯嗯！";
		}
	}

}
