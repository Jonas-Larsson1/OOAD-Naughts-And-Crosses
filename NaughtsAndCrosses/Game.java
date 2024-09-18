package NaughtsAndCrosses;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

  private Scanner scanner = new Scanner(System.in);
  private ArrayList<Player> players = new ArrayList<>();
  private Board board;

  public  void displayCurrentState(Board board) {
    board.printBoard();
  }

  private int getUserChoice() {
    return scanner.nextInt();
  }

  private void processUserChoice(int userChoice, Player player) {
    board.placeMarker(userChoice - 1, player.getMarker());
  }

  public void start() {
    board = new Board(3);
    boolean running = true;

    Player player1 = new Player('X');
    Player player2 = new Player('O');

    players.add(player1);
    players.add(player2);

    int currentPlayerIndex = 0;

    while (running) {
      Player currentPlayer = players.get(currentPlayerIndex);
      displayCurrentState(board);
      int userChoice = getUserChoice();
      processUserChoice(userChoice, currentPlayer);
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
  }
}
