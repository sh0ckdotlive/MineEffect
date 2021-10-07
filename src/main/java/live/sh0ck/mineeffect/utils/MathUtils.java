package live.sh0ck.mineeffect.utils;

/**
 * Utility class for {@link Integer} math.
 *
 * @author sh0ckR6
 * @since 2021.1007.1
 */
public class MathUtils {
  
  /**
   * Clamp a value between two ints
   *
   * @param value The value to clamp
   * @param min The minimum possible value
   * @param max The maximum possible value
   * @return The clamped value
   *
   * @author sh0ckR6
   * @since 2021.1007.1
   */
  public static int clamp(int value, int min, int max) {
    return value > max ? max : Math.max(value, min);
  }
}
