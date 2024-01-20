package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class TankDrive {
  public static enum TankState {
    kIdle,
    kTankSpeed,
    kTankVoltage,
  }

  private MotorController m_rightMotor;
  private MotorController m_leftMotor;
  private TankState m_state;
  private Double m_leftSpeed;
  private Double m_rightSpeed;
  private Double m_leftVoltage;
  private Double m_rightVoltage;

  TankDrive(MotorController rightMotor, MotorController leftMotor) {
    m_rightMotor = rightMotor;
    m_leftMotor = leftMotor;
    m_state = TankState.kIdle;
  }

  TankDrive(
      MotorController rightMotor,
      MotorController leftMotor,
      Boolean rightInverted,
      Boolean leftInverted) {
    m_rightMotor = rightMotor;
    m_rightMotor.setInverted(rightInverted);
    m_leftMotor = leftMotor;
    m_leftMotor.setInverted(leftInverted);
    m_state = TankState.kIdle;
  }

  void onUpdate() {
    switch (m_state) {
      case kIdle:
        m_leftSpeed = 0.0;
        m_leftVoltage = 0.0;
        m_rightSpeed = 0.0;
        m_rightVoltage = 0.0;
        break;
      case kTankSpeed:
        m_rightMotor.set(m_rightSpeed);
        m_leftMotor.set(m_leftSpeed);
      case kTankVoltage:
        m_rightMotor.setVoltage(m_rightVoltage);
        m_leftMotor.setVoltage(m_leftVoltage);
      default:
        System.err.println("Case not handled!");
        break;
    }
  }

  void setState(TankState state) {
    m_state = state;
  }

  TankState getState() {
    return m_state;
  }

  void setSpeed(Double rightSpeed, Double leftSpeed) {
    m_rightSpeed = rightSpeed;
    m_leftSpeed = leftSpeed;
  }

  void setVoltage(Double rightVolts, Double leftVolts) {
    m_rightVoltage = rightVolts;
    m_leftVoltage = leftVolts;
  }
}
