package lab3;

import java.util.List;

public abstract class SpecialNumber {
  public abstract SpecialNumber add(SpecialNumber number) throws Lab3Exception;

  public abstract SpecialNumber divideByInt(int number) throws Lab3Exception;

  public static SpecialNumber computeAverage(List<SpecialNumber> numbers) throws Lab3Exception {
    if (numbers == null || numbers.size() == 0) {
      throw new Lab3Exception("List cannot be empty");
    }

    SpecialNumber total = numbers.get(0);
    for (int i = 1; i < numbers.size(); i++) {
      total.add(numbers.get(i));
    }
    return total.divideByInt(numbers.size());
  };
}
