package live.sh0ck.mineeffect.utils;

public class MathUtils {
  
  /**
   * Clamp a value between two ints
   *
   * @param value The value to clamp
   * @param min The minimum possible value
   * @param max The maximum possible value
   * @return The clamped value
   */
  public static int clamp(int value, int min, int max) {
    return value > max ? max : Math.max(value, min);
  }
}
