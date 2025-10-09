package lab4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Lab4Tests {
  @Test
  void testPointConstructor() {
    Point point = new Point(1.0, 2.0);
    assertEquals(point.x, 1.0);
    assertEquals(point.y, 2.0);
  }

  @Test
  void testDistance() {
    Point pointA = new Point(1.0, 2.0);
    Point pointB = new Point(4.0, 6.0);
    double distance = pointA.distance(pointB);
    assertEquals(distance, 5);
  }

  @Test
  void testSameDistance() {
    Point pointA = new Point(1.0, 2.0);
    Point pointB = new Point(1.0, 2.0);
    double distance = pointA.distance(pointB);
    assertEquals(distance, 0);
  }

  @Test
  void testEqualCorrect() {
    Point point = new Point(1.0, 1.0);
    boolean isEqual = point.equals(point);
    assertTrue(isEqual);
  }

  @Test
  void testEqualNull() {
    Point point = new Point(1.0, 1.0);
    boolean isEqual = point.equals(null);
    assertFalse(isEqual);
  }

  @Test
  void testEqualTrue2() {
    Point pointA = new Point(1.0, 1.0);
    Point pointB = new Point(1.0, 1.0);
    boolean isEqual = pointA.equals(pointB);
    assertTrue(isEqual);
  }

  @Test
  void testEqualFalse() {
    Point pointA = new Point(1.0, 1.0);
    Point pointB = new Point(2.0, 1.0);
    boolean isEqual = pointA.equals(pointB);
    assertFalse(isEqual);
  }

  @Test
  void testEqualFalse2() {
    Point pointA = new Point(1.0, 1.0);
    Point pointB = new Point(1.0, 2.0);
    boolean isEqual = pointA.equals(pointB);
    assertFalse(isEqual);
  }

  @Test
  void testEqualDiffClass() {
    Point pointA = new Point(1.0, 1.0);
    String string = new String("Hi");
    boolean isEqual = pointA.equals(string);
    assertFalse(isEqual);
  }

  @Test
  void testHashCode() {
    Point point = new Point(2.0, 3.0);
    int pointHashCode = point.hashCode();
    assertEquals(pointHashCode, 3 * 2 + 5 * 3);
  }

  @Test
  void testTriangleConstructor() {
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
