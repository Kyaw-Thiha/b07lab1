package lab3;

class RationalNumber extends SpecialNumber implements Comparable<RationalNumber> {
  private int numerator;
  private int denominator;

  public RationalNumber(int numerator, int denominator) throws Lab3Exception {
    if (denominator == 0) {
      throw new Lab3Exception("Denominator cannot be zero");
    }
    this.numerator = numerator;
    this.denominator = denominator;
  }

  @Override
  public SpecialNumber add(SpecialNumber number) throws Lab3Exception {
    // Type casting to RationalNumber
    if (!(number instanceof RationalNumber)) {
      throw new Lab3Exception("Cannot add an incompatible type");
    }
    RationalNumber rationalNumber = (RationalNumber) number;

    // Implementing the addition
    // Scaling both numerators
    int originalNumerator = this.numerator;
    int originalDenominator = this.denominator;
    int argumentNumerator = rationalNumber.numerator;
    int argumentDenominator = rationalNumber.denominator;

    originalNumerator = originalNumerator * argumentDenominator;
    argumentNumerator = argumentNumerator * originalDenominator;

    // Carrying out the addition
    originalNumerator = argumentNumerator + originalNumerator;
    originalDenominator = argumentDenominator * originalDenominator;

    this.numerator = originalNumerator;
    this.denominator = originalDenominator;

    // Simplify the fraction
    int HCF = getHCF(this.numerator, this.denominator);
    this.numerator = this.numerator / HCF;
    this.denominator = this.denominator / HCF;

    return this;
  }

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

  @Override
  public int compareTo(RationalNumber number) {
    // Scaling both numerators
    int currentNumerator = this.numerator;
    int currentDenominator = this.denominator;

    int comparedNumerator = number.numerator;
    int comparedDenominator = number.denominator;

    // Scaling both numerators
    currentNumerator = currentNumerator * comparedDenominator;
    comparedNumerator = comparedNumerator * currentDenominator;

    // Comparing the numbers
    if (currentNumerator > comparedNumerator) {
      return 1;
    } else if (currentNumerator < comparedNumerator) {
      return -1;
    } else {
      return 0;
    }
  }

  @Override
  public boolean equals(Object number) {
    // Type casting to RationalNumber
    RationalNumber rationalNumber = (RationalNumber) number;

    // Simplify current number
    ensureSimplified(this);

    // Simplify argument number
    rationalNumber = ensureSimplified(rationalNumber);

    return (this.numerator == rationalNumber.numerator) && (this.denominator == rationalNumber.denominator);
  }

  @Override
  public int hashCode() {
    ensureSimplified(this);
    int result = 71 * 7 + this.numerator;
    return result + 17 * result + this.denominator;
  }

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

  private static RationalNumber ensureSimplified(RationalNumber number) {
    int HCF = getHCF(number.numerator, number.denominator);
    if (HCF != -1) {
      number.numerator /= HCF;
      number.denominator /= HCF;
    }
    return number;
  }
}
