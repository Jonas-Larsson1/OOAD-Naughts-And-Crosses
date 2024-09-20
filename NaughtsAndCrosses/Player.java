package NaughtsAndCrosses;

import java.util.Random;

public class Player {
  private Marker marker;
  private boolean computerControlled = false;

  public Player(char markerChar) {
    this.marker = new Marker(markerChar);
  }

  public Player(char markerChar, boolean computerControlled) {
    this.marker = new Marker(markerChar);
    this.computerControlled = computerControlled;
  }

  public Marker getMarker() {
    return marker;
  }

  public boolean isComputerControlled() {
    return computerControlled;
  }

  public int getComputerChoice(Board board) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    int position;
    Random random = new Random();
    do {
      position = random.nextInt(board.getBoardSize() * board.getBoardSize());
    } while (!board.isPositionEmpty(position));
    return position;
  }
}
