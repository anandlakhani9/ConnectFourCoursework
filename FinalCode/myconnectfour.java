
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class myconnectfour {
    private BufferedReader input;
    private Board board;
    private Player playerR;
    private Player playerY;
    private Random r;

    public static void main(String[] args){
        new myconnectfour();
    }

    public myconnectfour(){
        input = new BufferedReader(new InputStreamReader(System.in));
        playGame();
    }

    //this contains the game logic
    private void playGame(){
        System.out.println("Welcome to Connect 4");
        System.out.println("There are 2 players red and yellow");
        System.out.println("Player 1 is Red, Player 2 is Yellow");
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
        System.out.println("");
        board = new Board();
        playerR = new Player('r');
        playerY = new Player('y');
        boolean win = false;
        int turn = 1;
        int move;
        int moveCounter = 0;
        while(!win){

            //the total number of moves made so far
            moveCounter++;

            //player 1
            if (turn==1){
                System.out.println("Make your move:");
                //take a user input
                String userInput = getUserInput();
                //try make it an integer
                try{
                    move = Integer.parseInt(userInput);
                    //try to make the move
                    try {
                        playerR.placeCounter(move, board);
                    }
                    //if it fails, the integer was not between 1-7
                    //or the row was full
                    //the user must try again
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Row full or row doesn't exist - choose between rows 1 to 7:");
                        turn = 1;
                        moveCounter--;
                        continue;
                    }
                }
                //if the user did not enter an integer they must try again
                catch (NumberFormatException e){
                    System.out.println("Please enter a number between 1 and 7");
                    turn = 1;
                    moveCounter--;
                    continue;

                }
                //check for win in all directions
               if(playerR.checkWin(board)){
                   win = true;
                   board.printBoard();
                   System.out.println("r is the winner");
                   break;
               }

                else{
                    turn = 2;
                }
            }

            //player 2
            else if (turn == 2){
                System.out.println("The computer will now move:");
                //attempts to make the move in a random position
                try {
                    playerY.placeCounter(getRandom(), board);
                }
                //if it the column is full, the current player should try again
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Row full - the computer needs to try again:");
                    turn = 2;
                    moveCounter--;
                    continue;
                }
                //check in all directions to see if y wins
                if(playerY.checkWin(board)){
                    win = true;
                    board.printBoard();
                    System.out.println("y is the winner");
                    break;
                }

                //otherwise
                else{
                    turn = 1;
                }
            }
            board.printBoard();

            //if 42 valid moves have been made, and the game has not been won, it is a stalemate
            if(staleMate(moveCounter)){
                System.out.println("Draw!");
                break;
            }
        }
    }

    //get the user input
    private String getUserInput(){
        String toReturn = null;
        try{
            toReturn = input.readLine();
        }
        catch(Exception e){

        }
        return toReturn;
    }

    //generate a random integer for the bot to use
    private int getRandom(){
        Random r = new Random();
        int move= r.nextInt(6);
        move = move + 1;
        return move;
    }

    //check for a stalemate
    private boolean staleMate(int moveCounter){
        if (moveCounter >= 42){
            return true;
        }
        else {
            return false;
        }
    }



}

