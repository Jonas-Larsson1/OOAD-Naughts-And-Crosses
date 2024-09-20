package NaughtsAndCrosses;

public class Board {

  private Marker[][] currentState;
  private int boardSize;
  private int lastMoveRow;
  private int lastMoveCol;

  public Board(int boardSize) {
    this.boardSize = boardSize;
    this.currentState = new Marker[boardSize][boardSize];

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        currentState[i][j] = Marker.EMPTY;
      }
    }
  }

  public int getBoardSize() {
    return boardSize;
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
    lastMoveRow = row;
    lastMoveCol = col;
    return true;
  }

  public boolean placeMarker(char column, int row, Marker marker) {
    int col = column - 'A' + 1;

    if (currentState[row][col] != Marker.EMPTY) {
      return false;
    }

    currentState[row][col] = marker;
    lastMoveRow = row;
    lastMoveCol = col;
    return true;
  }

  public boolean checkWinner(Player player) {
    Marker playerMarker = player.getMarker();
    return (checkDirection(lastMoveRow, lastMoveCol, 0, 1, playerMarker) ||
        checkDirection(lastMoveRow, lastMoveCol, 1, 0, playerMarker) ||
        checkDirection(lastMoveRow, lastMoveCol, 1, 1, playerMarker) ||
        checkDirection(lastMoveRow, lastMoveCol, 1, -1, playerMarker));
  }

  private boolean checkDirection(int row, int col, int rowIncrement, int colIncrement, Marker marker) {
    int consecutiveCount = 1;
    consecutiveCount += countMarkersInDirection(row, col, rowIncrement, colIncrement, marker);
    consecutiveCount += countMarkersInDirection(row, col, -rowIncrement, -colIncrement, marker);
    return consecutiveCount >= boardSize;
  }

  private int countMarkersInDirection(int row, int col, int rowIncrement, int colIncrement, Marker marker) {
    int count = 0;
    for (int i = 1; i < boardSize; i++) {
      int newRow = row + i * rowIncrement;
      int newCol = col + i * colIncrement;
      if (newRow < 0 || newRow >= boardSize || newCol < 0 || newCol >= boardSize) {
        break;
      }
      if (currentState[newRow][newCol] == marker) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

/*  public void printBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        System.out.print(" " + currentState[row][col] + " ");
        if (col < boardSize - 1) {
          System.out.print("|");
        }
      }
      if (row < boardSize - 1) {
        System.out.println();
        for (int col = 0; col < boardSize; col++) {
          System.out.print("---");
          if (col < boardSize - 1) {
            System.out.print("+");
          }
        }
        System.out.println();
//        System.out.println("\n---+---+---");
      }
    }
    System.out.println();
  }*/

  public void printBoard() {
    System.out.print("   ");
    for (int col = 0; col < boardSize; col++) {
      char columnLabel = (char) ('A' + col);
      System.out.print(" " + columnLabel + "  ");
    }
    System.out.println();

    for (int row = 0; row < boardSize; row++) {
      System.out.printf("%2d ", row + 1);

      for (int col = 0; col < boardSize; col++) {
        System.out.print(" " + currentState[row][col] + " ");
        if (col < boardSize - 1) {
          System.out.print("|");
        }
      }

      if (row < boardSize - 1) {
        System.out.println();
        System.out.print("   ");
        for (int col = 0; col < boardSize; col++) {
          System.out.print("---");
          if (col < boardSize - 1) {
            System.out.print("+");
          }
        }
        System.out.println();
      }
    }
    System.out.println();
  }

  public boolean isPositionEmpty(int position) {
    int row = position / boardSize;
    int col = position % boardSize;
    return currentState[row][col] == Marker.EMPTY;
  }
}
