package NaughtsAndCrosses;

public class Player {
  private Marker marker;

  public Player(char markerChar) {
    this.marker = new Marker(markerChar);
  }

  public Marker getMarker() {
    return marker;
  }
}
