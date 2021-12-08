
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Board {
    private BufferedReader input;
    private char[][] board;
    private boolean placed = false;
    private Player player;

    public Board(){
        board = new char[6][7];
        printBoard();
    }

    //output the board
    public void printBoard(){
        //go through the rows
        for(int i=0; i<6; i++){
            //go through the columns and print the board
            for(int j=0; j < 7; j++){
                if(board[i][j] == 'r'){
                    System.out.print("| r ");
                }
                else if(board[i][j] == 'y'){
                    System.out.print("| y ");
                }
                else{
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
        System.out.println("  1   2   3   4   5   6   7");
    }

    //get the entire board as an array
    public char[][] getBoard(){
        return board;
    }

    //set a value of the board array
    public void setBoard(int position, int row, Player player){
        board[row-1][position-1] = player.getPlayer();
    }

    //get the length of the board
    public int getLength() {
        return board.length;
    }

    //get the value from a single position on the board
    public char boardPosition(int position, int row){
        //System.out.println(board[position][row]);
        return board[row][position];
    }
}
