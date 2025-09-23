import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Polynomial {
  private List<Double> coefficients;
  private List<Integer> exponents;

  public Polynomial() {
    this.coefficients = new ArrayList<>();
    this.exponents = new ArrayList<>();
  }

  public Polynomial(List<Double> coefficients, List<Integer> exponents) {
    this.coefficients = coefficients;
    this.exponents = exponents;
  }

  public Polynomial(File file) {
    this();

    try {
      Scanner reader = new Scanner(file);
      int i = 0;
      boolean isCoefficient = true;
      boolean isCoefficientPositive = true;
      int floatingPointFactor = 0; // Should be 0 when int, and >=1 when in floating part

      while (reader.hasNextLine()) {
        String line = reader.nextLine();

        for (char data : line.toCharArray()) {
          if (data == '-' || data == '+') {
            // Handling the + and - signs
            isCoefficient = true;
            isCoefficientPositive = (data == '+');
            floatingPointFactor = 0;
            if (i != 0) {
              i++;
            }

          } else if (data == 'x') {
            // Handling the x
            isCoefficient = false;
            floatingPointFactor = 0;

          } else if (data == '.') {
            // Handling the floating points in coefficients
            floatingPointFactor = 1;

          } else {
            // Append the elements first if they do not exist
            while (this.coefficients.toArray().length <= i) {
              this.coefficients.add(0.0);
              this.exponents.add(0);
            }

            if (isCoefficient) {
              // Adding the coefficient
              double coefficient = Double.parseDouble(Character.toString(data));

              if (!isCoefficientPositive) {
                coefficient = -coefficient;
              }

              if (floatingPointFactor == 0) {
                this.coefficients.set(i, this.coefficients.get(i) * 10 + coefficient);
              } else {
                this.coefficients.set(i, this.coefficients.get(i) + (coefficient * Math.pow(0.1, floatingPointFactor)));
                floatingPointFactor++;
              }
            } else {
              // Adding the exponent
              int exponent = Integer.parseInt(Character.toString(data));
              this.exponents.set(i, this.exponents.get(i) * 10 + exponent);
            }
          }
        }
      }

      reader.close();
    } catch (FileNotFoundException e) {
      // Hey, file is not found!!!
    }
  }

  public Polynomial add(Polynomial poly) {
    for (int i = 0; i < poly.exponents.toArray().length; i++) {
      int index = this.exponents.indexOf(poly.exponents.get(i));

      if (index == -1) {
        // If not found
        this.exponents.add(poly.exponents.get(i));
        this.coefficients.add(poly.coefficients.get(i));
      } else {
        // If found
        this.coefficients.set(index, this.coefficients.get(index) + poly.coefficients.get(i));
      }
    }

    return this;
  }

  public Polynomial multiply(Polynomial poly) {
    // Initialize new exponents & coeffcients
    List<Integer> exponentResult = new ArrayList<>();
    List<Double> coefficientResult = new ArrayList<>();

    // Carrying out the multiplication
    for (int i = 0; i < poly.exponents.toArray().length; i++) {
      int exponent = poly.exponents.get(i);
      double coefficient = poly.coefficients.get(i);

      for (int j = 0; j < this.exponents.toArray().length; j++) {
        // Add the exponents and multiply the coefficients
        int newExponent = this.exponents.get(j) + exponent;
        double newCoefficient = this.coefficients.get(j) * coefficient;

        // Upate the exponent & coefficients
        if (exponentResult.contains(newExponent)) {
          int index = exponentResult.indexOf(newExponent);
          coefficientResult.set(index, coefficientResult.get(index) + newCoefficient);
        } else {
          exponentResult.add(newExponent);
          coefficientResult.add(newCoefficient);
        }
      }
    }

    // Updating the results
    this.exponents = exponentResult;
    this.coefficients = coefficientResult;

    return this;
  }

  public double evaluate(double x) {
    double result = 0;
    for (int i = 0; i < this.coefficients.toArray().length; i++) {
      int exponent = this.exponents.get(i);
      double coefficient = this.coefficients.get(i);

      result += coefficient * Math.pow(x, exponent);
    }

    return result;
  }

  public boolean saveToFile(String fileName) {
    try {
      PrintWriter writer = new PrintWriter(fileName);

      for (int i = 0; i < this.coefficients.size(); i++) {
        double coefficient = this.coefficients.get(i);
        int exponent = this.exponents.get(i);
        Boolean isCoefficientInteger = (coefficient % 1.0 == 0);

        if (coefficient > 0 && i != 0) {
          writer.append("+");
          // Negative sign is handled automatically
        }

        if (isCoefficientInteger) {
          writer.append(Integer.toString((int) Math.floor(coefficient)));
        } else {
          writer.append(Double.toString(coefficient));
        }

        if (exponent > 0) {
          writer.append("x");
          writer.append(Integer.toString(exponent));
        }
      }

      writer.flush();
      writer.close();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean hasRoot(double x) {
    return evaluate(x) == 0.0;
  }
}
