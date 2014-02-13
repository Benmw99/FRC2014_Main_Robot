package org.team2168.commands;

public class WindWinch extends CommandBase{

	double speed;
	
	public WindWinch(double speed) {
		requires(catapultWinch);
		this.speed = speed;
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		catapultWinch.setCatapult(speed);
	}

	protected boolean isFinished() {
		return catapultWinch.isCatapultSet();
	}

	protected void end() {
		catapultWinch.setCatapult(0);
	}

	protected void interrupted() {
		catapultWinch.setCatapult(0);
	}

}
