package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {
  private XboxController m_controller;
  private TankDrive m_drive;

  @Override
  public void robotInit() {
    m_controller = new XboxController(0);
    m_drive = new TankDrive(new TalonFX(99), new TalonFX(99));
  }

  @Override
  public void robotPeriodic() {
    m_drive.onUpdate();
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    if (m_controller.getXButtonPressed()) {
      m_drive.setState(TankDrive.TankState.kTankVoltage);
    }

    if (m_controller.getYButtonPressed()) {
      m_drive.setState(TankDrive.TankState.kTankSpeed);
    }

    if (m_controller.getBButtonPressed()) {
      m_drive.setState(TankDrive.TankState.kIdle);
    }

    switch (m_drive.getState()) {
      case kIdle:
        break;
      case kTankSpeed:
        Double rightSpeed =
            (Math.abs(m_controller.getRightY()) > 0.05) ? m_controller.getRightY() : 0;
        Double leftSpeed = (Math.abs(m_controller.getLeftY()) > 0.05) ? m_controller.getLeftY() : 0;
        m_drive.setSpeed(rightSpeed, leftSpeed);
      case kTankVoltage:
        Double rightVolts =
            ((Math.abs(m_controller.getRightY()) > 0.05) ? m_controller.getRightY() : 0)
                * RobotController.getBatteryVoltage();
        Double leftVolts =
            ((Math.abs(m_controller.getLeftY()) > 0.05) ? m_controller.getLeftY() : 0)
                * RobotController.getBatteryVoltage();
        m_drive.setVoltage(rightVolts, leftVolts);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
