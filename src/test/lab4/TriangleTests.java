package lab4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TriangleTests {
  @Test
  void testConstructor() {
    Point pointA = new Point(1.0, 2.0);
    Point pointB = new Point(2.0, 3.0);
    Point pointC = new Point(3.0, 4.0);
    Triangle triangle = new Triangle(pointA, pointB, pointC);

    assertEquals(triangle.A.x, 1.0);
    assertEquals(triangle.B.x, 2.0);
    assertEquals(triangle.C.x, 3.0);
  }

  @Test
  void testPerimeter() {
    Point pointA = new Point(0, 0);
    Point pointB = new Point(3.0, 0);
    Point pointC = new Point(0, 4.0);
    Triangle triangle = new Triangle(pointA, pointB, pointC);
    double perimeter = triangle.perimeter();
    assertEquals(perimeter, 3 + 4 + 5);
  }

  @Test
  void testEquilateral() {
    Point pointA = new Point(0, 0);
    Point pointB = new Point(Math.sqrt(5), 0);
    Point pointC = new Point(Math.sqrt(5) / 2, Math.sqrt(15) / 2);
    Triangle triangle = new Triangle(pointA, pointB, pointC);
    boolean isEquilateral = triangle.isEquilateral();
    assertTrue(isEquilateral);
  }

  @Test
  void testNotEquilateral() {
    Point pointA = new Point(0, 0);
    Point pointB = new Point(3.0, 0);
    Point pointC = new Point(1, Math.sqrt(3));
    Triangle triangle = new Triangle(pointA, pointB, pointC);
    boolean isEquilateral = triangle.isEquilateral();
    assertFalse(isEquilateral);
  }
}
