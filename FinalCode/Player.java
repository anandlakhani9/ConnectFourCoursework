
public class Player {

    private char colour;
    //private Board theBoard;
    private int boardLength = 6;
    private int board_i_Length = 7;

    private int count;
    private boolean Win;
    private boolean placed = false;

    public Player(char colour){
        this.colour = colour;

    }

    //place a counter
    //column is known by this function
    public void placeCounter(int position, Board theBoard){
        placed = false;
        //go through rows
        for(int i=boardLength; i>=0; i--){
            //place the counter in the lowest unfilled row of that column
            if(!placed){
                if(theBoard.boardPosition(position-1,i-1) != 'r' && theBoard.boardPosition(position-1,i-1) != 'y'){
                    theBoard.setBoard(position, i, this);
                    placed = true;
                }

            }
        }
    }

    //get the colour of the player's counter
    public char getPlayer(){
        return this.colour;
    }

    //check for a win horizontally
    public boolean checkHorizontal(Board theBoard){
        count = 0;
        //go through rows
        for(int i=1; i<=boardLength; i++){
            //go through columns
            for(int j=1; j<=board_i_Length; j++){
                //count the number of same counters next to each other in a row
                if(theBoard.boardPosition(j-1,i-1) == this.colour){
                    count = count + 1;
                    //if there is 4 or more that player wins
                    if(count >= 4){
                        Win = true;
                        break;
                    }
                }
                //if there is a different counter, reset to 0
                else{
                    count = 0;
                     Win = false;
                }
            }
            if(Win){
                break;
            }
        }
        //return whether or not they have won
        if (Win){
            return true;
        }
        else {
            return false;
        }
    }

    //check for a win vertically - same logic as horizontal so won't comment all this
    public boolean checkVertical(Board theBoard){
        count = 0;

        for(int i=1; i<=board_i_Length; i++){
            for(int j=1; j<=boardLength; j++){
                if(theBoard.boardPosition(i-1,j-1) == this.colour){
                    count = count + 1;
                    if(count >= 4){
                       Win = true;
                       break;
                    }
                }
                else{
                    count = 0;
                }
            }
            if (Win){
                break;
            }
        }
        if (Win){
            return true;
        }
        else {
            return false;
        }
    }

    //only need to check diags starting from the bottom 3 rows
    //only need to do this for position>=4
    public boolean checkDiagLeft(Board theBoard){
        count = 0;
        char colour = this.colour;
        //go through columns
        for(int j=3; j<board_i_Length; j++){
            //go through rows
            for(int i = boardLength-1 ; i>=4; i--){
                //check the 3 values to the top left of the current one - if they are the same then the player has won
                if (theBoard.boardPosition(j,i) == colour && theBoard.boardPosition(j-1,i-1) == colour && theBoard.boardPosition(j-2,i-2) == colour&& theBoard.boardPosition(j-3,i-3) == colour){
                    Win = true;
                    break;
                }
            }
            if(Win){
                break;
            }
        }
        //return whether or not they have won
        if (Win){
            return true;
        }
        else {
            return false;
        }
    }

    //only need to check diags starting from the bottom 3 rows
    //only need to do this for position<=4
    //same logic as checkDiagLeft so won't comment this further
    public boolean checkDiagRight(Board theBoard){
        count = 0;
        char colour = this.colour;
        for(int j=3; j>=0; j--){
            for(int i = boardLength-1 ; i>=4; i--){
                if (theBoard.boardPosition(j,i) == colour && theBoard.boardPosition(j+1,i-1) == colour && theBoard.boardPosition(j+2,i-2) == colour&& theBoard.boardPosition(j+3,i-3) == colour){
                    Win = true;
                    break;
                }
            }
            if(Win){
                break;
            }
        }
        if (Win){
            return true;
        }
        else {
            return false;
        }
    }
    
    //this function checks to see if the player has won, if yes, it will return true, otherwise false
    public boolean checkWin(Board theBoard){
        if (checkDiagLeft(theBoard)){
            return true;
        }
        else if (checkDiagRight(theBoard)){
            return true;
        }
        else if (checkHorizontal(theBoard)){
            return true;
        }
        else if (checkVertical(theBoard)){
            return true;
        }
        else {
            return false;
        }
    }
}
