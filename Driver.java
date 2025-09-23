import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Driver {
  private static Polynomial makePoly(int[] exps, double[] coeffs) {
    List<Integer> e = new ArrayList<>();
    List<Double> c = new ArrayList<>();
    for (int i = 0; i < exps.length; i++) {
      e.add(exps[i]);
      c.add(coeffs[i]);
    }
    return new Polynomial(c, e);
  }

  public static void main(String[] args) throws Exception {
    // Build two polynomials directly
    Polynomial P1 = makePoly(new int[] { 2, 1, 0 }, new double[] { 3, 2, 1 }); // 3x^2 + 2x + 1
    Polynomial P2 = makePoly(new int[] { 1, 0 }, new double[] { -4, 5 }); // -4x + 5

    // evaluate()
    System.out.println("P1(2) = " + P1.evaluate(2));

    // add()
    Polynomial sum = makePoly(new int[] { 2, 1, 0 }, new double[] { 3, 2, 1 }).add(P2);
    System.out.println("(P1+P2)(2) = " + sum.evaluate(2));

    // multiply()
    Polynomial prod = makePoly(new int[] { 2, 1, 0 }, new double[] { 3, 2, 1 }).multiply(P2);
    System.out.println("(P1*P2)(2) = " + prod.evaluate(2));

    // hasRoot()
    Polynomial R = makePoly(new int[] { 2, 1, 0 }, new double[] { 1, 1, -6 }); // (x-2)(x+3)
    System.out.println("R has root 2: " + R.hasRoot(2));
    System.out.println("R has root -3: " + R.hasRoot(-3));

    // saveToFile() + load back using File constructor (round-trip)
    String f1 = "poly_rt.txt";
    boolean saved = P1.saveToFile(f1);
    System.out.println("saveToFile -> " + saved);
    Polynomial P1r = new Polynomial(new File(f1));
    System.out.println("Reloaded P1(2) = " + P1r.evaluate(2));

    // Construct from a hand-crafted file with NO WHITESPACE
    // Format matches your parser: signs, digits, optional '.', 'x' then exponent
    // digits
    String f2 = "manual.txt";
    try (PrintWriter w = new PrintWriter(f2)) {
      w.print("+3x2-4x1+1.25");
    } // 3x^2 - 4x + 1.25
    Polynomial Pman = new Polynomial(new File(f2));
    System.out.println("Manual poly (3x^2-4x+1.25) at x=2: " + Pman.evaluate(2));
  }
}
