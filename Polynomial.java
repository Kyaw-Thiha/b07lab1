
public class Polynomial {
  private double[] coefficients;

  public Polynomial() {
    this.coefficients = new double[] { 0.0 };
  }

  public Polynomial(double[] coefficients) {
    this.coefficients = coefficients;
  }

  public Polynomial add(Polynomial poly) {
    if (poly.coefficients.length > this.coefficients.length) {
      for (int i = 0; i < this.coefficients.length; i++) {
        poly.coefficients[i] = this.coefficients[i] + poly.coefficients[i];
      }
      return poly;
    } else {
      for (int i = 0; i < poly.coefficients.length; i++) {
        this.coefficients[i] = this.coefficients[i] + poly.coefficients[i];
      }
      return this;
    }

  }

  public double evaluate(double x) {
    double result = 0;
    for (int i = 0; i < this.coefficients.length; i++) {
      result += this.coefficients[i] * Math.pow(x, i);
    }
    return result;
  }

  public boolean hasRoot(double x) {
    return evaluate(x) == 0.0;
  }
}
