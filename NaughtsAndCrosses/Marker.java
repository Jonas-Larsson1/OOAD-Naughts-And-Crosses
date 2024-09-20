package NaughtsAndCrosses;

public class Marker {
  public static final Marker EMPTY = new Marker(' ');
  private char markerChar;

  public Marker(char markerChar) {
    this.markerChar = markerChar;
  }

  @Override
  public String toString() {
    return String.valueOf(markerChar);
  }

}











