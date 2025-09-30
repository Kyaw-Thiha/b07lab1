package lab3;

class ComplexNumber extends SpecialNumber implements Comparable<ComplexNumber> {
  private double real;
  private double imaginary;

  public ComplexNumber(double real, double imaginary) throws Lab3Exception {
    this.real = real;
    this.imaginary = imaginary;
  }

  @Override
  public SpecialNumber add(SpecialNumber number) throws Lab3Exception {
    // Type casting to RationalNumber
    if (!(number instanceof ComplexNumber)) {
      throw new Lab3Exception("Cannot add an incompatible type");
    }
    ComplexNumber complexNumber = (ComplexNumber) number;
    this.real += complexNumber.real;
    this.imaginary += complexNumber.imaginary;

    return this;
  }

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
   * Compare the current number to the other number in argument
   * using their magnitudes
   * 
   * @param number another complex number to compare against the current
   * @return 1 if current is greater, -1 if current is smaller, and 0 if equal
   */
  @Override
  public int compareTo(ComplexNumber number) {
    // Comparism through magnitude
    double currentResult = Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
    double comparedResult = Math.sqrt(Math.pow(number.real, 2) + Math.pow(number.imaginary, 2));

    // Comparism
    if (currentResult > comparedResult) {
      return 1;
    } else if (currentResult < comparedResult) {
      return -1;
    } else {
      return 0;
    }
  }

  @Override
  public boolean equals(Object number) {
    // Type casting to ComplexNumber
    ComplexNumber complexNumber = (ComplexNumber) number;
    return (this.real == complexNumber.real) && (this.imaginary == complexNumber.imaginary);
  }

  @Override
  public int hashCode() {
    double result = 71 * 7 + this.real;
    return (int) (result + 17 * result + this.imaginary);
  }
}
