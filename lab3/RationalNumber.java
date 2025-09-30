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
    this.numerator = this.numerator * rationalNumber.denominator;
    rationalNumber.numerator = rationalNumber.numerator * this.denominator;

    // Carrying out the addition
    this.numerator = rationalNumber.numerator + this.numerator;
    this.denominator = rationalNumber.denominator * this.numerator;

    return this;
  }

  @Override
  public SpecialNumber divideByInt(int number) {
    return this;
  }

  @Override
  public int compareTo(RationalNumber number) {
    return 0;
  }

  @Override
  public boolean equals(Object number) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
