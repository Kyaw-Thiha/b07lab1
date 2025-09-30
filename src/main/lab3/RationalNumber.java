package lab3;

/**
 * Represents a rational number (fraction) with numerator and denominator.
 */
class RationalNumber extends SpecialNumber implements Comparable<RationalNumber> {
  private int numerator;
  private int denominator;

  /**
   * Constructs a RationalNumber with given numerator and denominator.
   *
   * @param numerator   the numerator
   * @param denominator the denominator
   * @throws Lab3Exception if denominator is zero
   */
  public RationalNumber(int numerator, int denominator) throws Lab3Exception {
    if (denominator == 0) {
      throw new Lab3Exception("Denominator cannot be zero");
    }
    this.numerator = numerator;
    this.denominator = denominator;
  }

  /**
   * Adds another RationalNumber to this one.
   *
   * @param number the number to add
   * @return the resulting RationalNumber
   * @throws Lab3Exception if the type is incompatible
   */
  @Override
  public SpecialNumber add(SpecialNumber number) throws Lab3Exception {
    if (!(number instanceof RationalNumber)) {
      throw new Lab3Exception("Cannot add an incompatible type");
    }
    RationalNumber rationalNumber = (RationalNumber) number;

    int originalNumerator = this.numerator;
    int originalDenominator = this.denominator;
    int argumentNumerator = rationalNumber.numerator;
    int argumentDenominator = rationalNumber.denominator;

    originalNumerator = originalNumerator * argumentDenominator;
    argumentNumerator = argumentNumerator * originalDenominator;

    originalNumerator = argumentNumerator + originalNumerator;
    originalDenominator = argumentDenominator * originalDenominator;

    this.numerator = originalNumerator;
    this.denominator = originalDenominator;

    int HCF = getHCF(this.numerator, this.denominator);
    this.numerator = this.numerator / HCF;
    this.denominator = this.denominator / HCF;

    return this;
  }

  /**
   * Divides this RationalNumber by an integer.
   *
   * @param number the integer divisor
   * @return the resulting RationalNumber
   * @throws Lab3Exception if divisor is zero
   */
  @Override
  public SpecialNumber divideByInt(int number) throws Lab3Exception {
    if (number == 0) {
      throw new Lab3Exception("Cannot divide by zero");
    }

    int HCF = getHCF(this.numerator, number);
    this.numerator = this.numerator / HCF;
    number = number / HCF;
    this.denominator = this.denominator * number;

    return this;
  }

  /**
   * Compares this RationalNumber with another.
   *
   * @param number the number to compare to
   * @return 1 if greater, -1 if smaller, 0 if equal
   */
  @Override
  public int compareTo(RationalNumber number) {
    int currentNumerator = this.numerator;
    int currentDenominator = this.denominator;

    int comparedNumerator = number.numerator;
    int comparedDenominator = number.denominator;

    currentNumerator = currentNumerator * comparedDenominator;
    comparedNumerator = comparedNumerator * currentDenominator;

    if (currentNumerator > comparedNumerator) {
      return 1;
    } else if (currentNumerator < comparedNumerator) {
      return -1;
    } else {
      return 0;
    }
  }

  /**
   * Checks equality with another object.
   *
   * @param number the object to compare with
   * @return true if equal, false otherwise
   */
  @Override
  public boolean equals(Object number) {
    RationalNumber rationalNumber = (RationalNumber) number;

    ensureSimplified(this);
    rationalNumber = ensureSimplified(rationalNumber);

    return (this.numerator == rationalNumber.numerator) && (this.denominator == rationalNumber.denominator);
  }

  /**
   * Computes hash code for this RationalNumber.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    ensureSimplified(this);
    int result = 71 * 7 + this.numerator;
    return result + 17 * result + this.denominator;
  }

  /**
   * Finds the highest common factor (HCF).
   *
   * @param a first integer
   * @param b second integer
   * @return the HCF
   */
  private static int getHCF(int a, int b) {
    a = Math.abs(a);
    b = Math.abs(b);
    if (a == 0)
      return b;
    if (b == 0)
      return a;
    while (b != 0) {
      int t = a % b;
      a = b;
      b = t;
    }
    return a;
  }

  /**
   * Simplifies a RationalNumber.
   *
   * @param number the RationalNumber to simplify
   * @return the simplified RationalNumber
   */
  private static RationalNumber ensureSimplified(RationalNumber number) {
    int HCF = getHCF(number.numerator, number.denominator);
    if (HCF != -1) {
      number.numerator /= HCF;
      number.denominator /= HCF;
    }
    return number;
  }
}
