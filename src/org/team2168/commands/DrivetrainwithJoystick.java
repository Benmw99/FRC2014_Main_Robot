
package org.team2168.commands;

import org.team2168.OI;
import org.team2168.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *
 * @author bradmiller
 */
public class DrivetrainWithJoystick extends CommandBase {
	
	private double leftSpeed, rightSpeed;

	OI oi = new OI();
	
    public DrivetrainWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: one of these will need to be negated
    	drivetrain.drive(oi.getBaseDriverLeftStick(),
    			oi.getBaseDriverRightStick());
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
