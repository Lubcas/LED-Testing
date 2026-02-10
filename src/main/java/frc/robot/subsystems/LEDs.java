package frc.robot.subsystems;

import java.awt.Color;

import com.ctre.phoenix6.controls.SolidColor;
import com.ctre.phoenix6.controls.EmptyAnimation;
import com.ctre.phoenix6.controls.FireAnimation;
import com.ctre.phoenix6.controls.RainbowAnimation;
import com.ctre.phoenix6.controls.StrobeAnimation;
import com.ctre.phoenix6.hardware.CANdle;
import com.ctre.phoenix6.signals.RGBWColor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase {
  private final CANdle candle;

  public LEDs() {
    // CHANGE CAN ID LATER
    candle = new CANdle(31, "rio");
  }
// WS2812B are RGB
  public void setState(HubState state) {
    candle.setControl(new EmptyAnimation(0));
    candle.setControl(new SolidColor(0, 7).withColor(state.color));
    RGBWColor color = new RGBWColor(state.color.Red, state.color.Green, state.color.Blue);
    candle.setControl(new SolidColor(8, 399).withColor(color));
  }

  public enum AnimationTypes {
    RainbowAnimation,
    FireAnimation,
    StrobeAnimation
  }

  public enum HubState {
    HUB_ACTIVE(0, 255, 0), // Green
    OPPONENT_HUB(255, 0, 0), // Red
    HUB_STARTING_SOON(255, 255, 0), // Yellow
    HUB_ENDING_SOON(190, 0, 255), // Purple
    TRANSITION(0, 0, 255), // Blue
    DISABLED(40, 40, 40); // Dim white


    private final RGBWColor color;

    private HubState(int r, int g, int b) {
      color = new RGBWColor(r, g, b);
    }
  }
  
  public void setRainbow(){
    candle.setControl(
      new RainbowAnimation(0, 100)
      .withBrightness(0.5)
      .withFrameRate(60));
  }

  public void setFire(){
    candle.setControl(new FireAnimation(0, 100)
    .withBrightness(0.5)
    .withFrameRate(60));
  } 

}
