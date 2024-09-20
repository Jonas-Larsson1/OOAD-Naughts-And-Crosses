package NaughtsAndCrosses;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

  private Scanner scanner = new Scanner(System.in);
  private ArrayList<Player> players = new ArrayList<>();
  private Board board;

  private void gameSetup() {
    System.out.print("Please choose game board size:");
    int boardSize = scanner.nextInt();

    System.out.print("Please choose your marker");

    board = new Board(boardSize);
  }

  private int getPlayerChoice(Player player) {
    if (player.isComputerControlled()) {
      System.out.printf("Computer controlled player '%s' is making a move... \n", player.getMarker());
      return player.getComputerChoice(board);
    }
    System.out.printf("\nPlayer '%s', please choose column and row to place your marker in (example : A1) \n", player.getMarker());

    String userInput = scanner.nextLine();
    char columnChar = userInput.charAt(0);
    int rowNumber = Integer.parseInt(userInput.substring(1));

    int column = columnChar - 'A';
    int row = rowNumber - 1;

    int position = row * board.getBoardSize() + column;

    return position;
  }

  private void processPlayerChoice(int playerChoice, Player player) {
    boolean validChoiceMade = false;
    int currentChoice = playerChoice;
    while (!validChoiceMade) {
      if (!board.placeMarker(currentChoice, player.getMarker())) {
        System.out.println("Invalid position.");
        currentChoice = getPlayerChoice(player);
      } else {
        validChoiceMade = true;
      }
    }
  }

  public void start() {

//    gameSetup();
    board = new Board(5);

    boolean running = true;

    Player player1 = new Player('X');
    Player player2 = new Player('O', true);

    players.add(player1);
    players.add(player2);

    int currentPlayerIndex = 0;

    int currentRound = 0;

//    board.printBoard();

    while (running) {
      Player currentPlayer = players.get(currentPlayerIndex);
      if (!currentPlayer.isComputerControlled()) {
        board.printBoard();
      }
      int playerChoice = getPlayerChoice(currentPlayer);
      processPlayerChoice(playerChoice, currentPlayer);
      if (board.checkWinner(currentPlayer)) {
        System.out.printf("Player '%s' wins!", currentPlayer.getMarker());
        break;
      }
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
      currentRound += 1;
      if (currentRound == board.getBoardSize() * board.getBoardSize()) {
        System.out.println("It's a draw!");
        break;
      }
    }
  }
}
