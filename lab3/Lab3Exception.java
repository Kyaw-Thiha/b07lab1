package lab3;

public class Lab3Exception extends Exception {
  private String message;

  public Lab3Exception(String message) {
    super(message);
    this.message = message;
  }
}
