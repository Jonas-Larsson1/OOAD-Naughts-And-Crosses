package NaughtsAndCrosses;

public class Board {

  private Marker[][] currentState;
  private int boardSize;

  public Board(int boardSize) {
    this.boardSize = boardSize;
    this.currentState = new Marker[boardSize][boardSize];

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        currentState[i][j] = Marker.EMPTY;
      }
    }
  }

  public boolean placeMarker(int position, Marker marker) {
    if (position < 0 || position >= boardSize * boardSize) {
      return false;
    }

    int row = position / boardSize;
    int col = position % boardSize;

    if (currentState[row][col] != Marker.EMPTY) {
      return false;
    }

    currentState[row][col] = marker;
    return true;

  }

  public void printBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        System.out.print(" " + currentState[row][col] + " ");
        if (col < boardSize - 1) {
          System.out.print("|");
        }
      }
      if (row < boardSize - 1) {
        System.out.println("\n---+---+---");
      }
    }
    System.out.println();
  }
}
