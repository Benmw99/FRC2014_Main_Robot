package org.team2168.commands.flashlight;

import org.team2168.commands.CommandBase;


/**
 * Turn the flashlight on.
 * 
 *  This command doesn't complete. If it is going to be used in a sequence,
 *  specify a timeout!
 */
public class FlashlightOn extends CommandBase {

	long currenttime;
	
    public FlashlightOn() {
    	requires(flashlight);
    }

    /**
     * Called just before this Command runs the first time
     */
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	currenttime = System.currentTimeMillis();
    	flashlight.turnOn();
    }

    /**
     * This command will never complete (we're running it as a default command)
     * It will repeatedly set the state of the spike relay output.
     */
    protected boolean isFinished() {
    	if(System.currentTimeMillis() - currenttime >= 30000){
    		return true;
    	}
        return false;
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    	flashlight.turnOff();
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     */
    protected void interrupted() {
    }
}
