package lab3;

/**
 * Custom exception class for Lab3.
 */
public class Lab3Exception extends Exception {
  public String message;

  /**
   * Constructs a Lab3Exception with a message.
   *
   * @param message the exception message
   */
  public Lab3Exception(String message) {
    super(message);
    this.message = message;
  }
}
