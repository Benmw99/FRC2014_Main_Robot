package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.DrivetrainWithJoystick;
import org.team2168.utils.FalconGyro;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class Drivetrain extends Subsystem {
	private static Drivetrain instance = null;
	private static final boolean INVERT_LEFT = true;
	private static final boolean INVERT_RIGHT = false;
	private Talon rightMotor, rightMotor2;
    private Talon leftMotor, leftMotor2;
    private FalconGyro gyro;
    private Encoder driveTrainEncoderLeft;
    private Encoder driveTrainEncoderRight;

    /**
     * A private constructor to prevent multiple instances from being created.
     */
    private Drivetrain()
    {
    	rightMotor = new Talon(RobotMap.rightDriveMotor.getInt());
    	leftMotor = new Talon(RobotMap.leftDriveMotor.getInt());
    	rightMotor2 = new Talon(RobotMap.rightDriveMotor2.getInt());
    	leftMotor2 = new Talon(RobotMap.leftDriveMotor2.getInt());
    	gyro = new FalconGyro(RobotMap.gyroPort.getInt());

    	//converts ticks to distance in inches
    	double ticksPerRev =
    			(RobotMap.wheelDiameterDrivetrain.getDouble() * Math.PI) * 
    			(RobotMap.drivetrainGearRatio.getDouble()) /
    			RobotMap.ticksPerRevolutionDrivetrain.getDouble();
    	
    	driveTrainEncoderRight = new Encoder(RobotMap.driveTrainEncoderRightA.getInt(),
    			RobotMap.driveTrainEncoderRightB.getInt());
    	driveTrainEncoderRight.setDistancePerPulse(ticksPerRev);
    	driveTrainEncoderLeft = new Encoder(RobotMap.driveTrainEncoderLeftA.getInt(),
    			RobotMap.driveTrainEncoderLeftB.getInt());
    	driveTrainEncoderLeft.setDistancePerPulse(ticksPerRev);
    }
    
	/**
	 * 
	 * @return the instance of this subsystem.
	 */
    public static Drivetrain getInstance() {
    	if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
    }
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DrivetrainWithJoystick());
    }
    
    public void driveLeft(double speed)
    {
    	if(INVERT_LEFT) speed = -speed;
    	
    	leftMotor.set(speed);
    	leftMotor2.set(speed);
    }
    
    public void driveRight(double speed)
    {
    	if(INVERT_RIGHT) speed = -speed;
    	
    	rightMotor.set(speed);
    	rightMotor2.set(speed);
    }
    
    /**
     * A method to drive the motors on the drivetrain with.
     * 
     * @param rightSpeed the speed to drive the right motor at 
     * @param leftSpeed the speed to drive the left motor at
     */
    public void drive(double rightSpeed, double leftSpeed)
    {
    	this.driveRight(rightSpeed);
    	this.driveLeft(leftSpeed);
    }
    
    /**
     * Gets the distance the right encoder has turned
     * 
     * @return distance in inches
     */
    public double getRightEncoderDistance()
    {
    	return driveTrainEncoderRight.getDistance();
    }
    
    /**
     * Gets the distance the left encoder has turned
     * 
     * @return distance in inches
     */
    public double getLeftEncoderDistance()
    {
    	return driveTrainEncoderLeft.getDistance();
    }
    
    /**
     * Averages the distance of the two encoders to get the distance the robot
     * has traveled
     * 
     * @return distance in inches
     */
    public double getAveragedEncoderDistance()
    {
    	return (getLeftEncoderDistance() + getRightEncoderDistance())/2;
    }
    
    /**
     * resets right encoder to 0
     * 
     */
    public void resetRightEncoder()
    {
    	driveTrainEncoderRight.reset();
    }
    
    /**
     * resets left encoder to 0
     * 
     */
    public void resetLeftEncoder()
    {
    	driveTrainEncoderLeft.reset();
    }
    
    /**
     * Get the current angle of the gyro.
     * 
     * @return the angle of the gyro, in degrees.
     */
    public double getGyroAngle()
    {
    	return gyro.getAngle();
    }

    /**
     * Re-initialize the gyro. This should not be called during a match.
     */
	public void reinitGyro()
	{
		gyro.initGyro();
	}

	/**
	 * Set the current robot heading to 0.0
	 */
	public void resetGyro()
	{
		gyro.reset();
	}
}
