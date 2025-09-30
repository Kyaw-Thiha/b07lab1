package lab3;

/**
 * Represents a complex number with real and imaginary parts.
 */
class ComplexNumber extends SpecialNumber implements Comparable<ComplexNumber> {
  private double real;
  private double imaginary;

  /**
   * Constructs a ComplexNumber with given real and imaginary parts.
   *
   * @param real      the real part
   * @param imaginary the imaginary part
   * @throws Lab3Exception not actually thrown here, but kept for consistency
   */
  public ComplexNumber(double real, double imaginary) throws Lab3Exception {
    this.real = real;
    this.imaginary = imaginary;
  }

  /**
   * Adds another ComplexNumber to this one.
   *
   * @param number the number to add
   * @return the resulting ComplexNumber
   * @throws Lab3Exception if the type is incompatible
   */
  @Override
  public SpecialNumber add(SpecialNumber number) throws Lab3Exception {
    if (!(number instanceof ComplexNumber)) {
      throw new Lab3Exception("Cannot add an incompatible type");
    }
    ComplexNumber complexNumber = (ComplexNumber) number;
    this.real += complexNumber.real;
    this.imaginary += complexNumber.imaginary;

    return this;
  }

  /**
   * Divides this ComplexNumber by an integer.
   *
   * @param number the integer divisor
   * @return the resulting ComplexNumber
   * @throws Lab3Exception if divisor is zero
   */
  @Override
  public ComplexNumber divideByInt(int number) throws Lab3Exception {
    if (number == 0) {
      throw new Lab3Exception("Cannot divide by zero");
    }

    this.real = this.real / number;
    this.imaginary = this.imaginary / number;

    return this;
  }

  /**
   * Compares this ComplexNumber with another using magnitude.
   *
   * @param number the number to compare to
   * @return 1 if greater, -1 if smaller, 0 if equal
   */
  @Override
  public int compareTo(ComplexNumber number) {
    double currentResult = Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
    double comparedResult = Math.sqrt(Math.pow(number.real, 2) + Math.pow(number.imaginary, 2));

    if (currentResult > comparedResult) {
      return 1;
    } else if (currentResult < comparedResult) {
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
    ComplexNumber complexNumber = (ComplexNumber) number;
    return (this.real == complexNumber.real) && (this.imaginary == complexNumber.imaginary);
  }

  /**
   * Computes hash code for this ComplexNumber.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    double result = 71 * 7 + this.real;
    return (int) (result + 17 * result + this.imaginary);
  }
}
