package lab3;

import java.util.List;

/**
 * Abstract base class for special number types.
 */
public abstract class SpecialNumber {
  /**
   * Adds another SpecialNumber to this one.
   *
   * @param number the number to add
   * @return the resulting SpecialNumber
   * @throws Lab3Exception if the type is incompatible
   */
  public abstract SpecialNumber add(SpecialNumber number) throws Lab3Exception;

  /**
   * Divides this SpecialNumber by an integer.
   *
   * @param number the integer divisor
   * @return the resulting SpecialNumber
   * @throws Lab3Exception if divisor is zero
   */
  public abstract SpecialNumber divideByInt(int number) throws Lab3Exception;

  /**
   * Computes the average of a list of SpecialNumbers.
   *
   * @param numbers the list of SpecialNumbers
   * @return the average SpecialNumber
   * @throws Lab3Exception if list is null or empty
   */
  public static SpecialNumber computeAverage(List<SpecialNumber> numbers) throws Lab3Exception {
    if (numbers == null || numbers.size() == 0) {
      throw new Lab3Exception("List cannot be empty");
    }

    SpecialNumber total = numbers.get(0);
    for (int i = 1; i < numbers.size(); i++) {
      total.add(numbers.get(i));
    }
    return total.divideByInt(numbers.size());
  }
}
