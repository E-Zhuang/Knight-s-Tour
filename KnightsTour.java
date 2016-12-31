
/**
 * Lab 18.2 Knight's Tour
 * 
 * Emily Zhuang
 * Mr. Lantsberger
 * APCS:Period 4
 * 31 December 2016
 */

import java.util.Random;        //for random number generator
import apcslib.*;              //for format method
    
public class KnightsTour
{
    private int[][] board;                           //the chessboard
    private int[] xPos = {1,2,2,1,-1,-2,-2,-1};      //all possible moves in x direction
    private int[] yPos = {-2,-1,1,2,2,1,-1,-2};      //all possible moves in y direction
    private int[] validMoves;                        //stores the valid moves of the knight from any position
    private int myX, myY;                            //the current x and y position of the knight
    
    /**
     * Constructor for objects of class KnightsTour. This constructor creates an 8 x 8 chessboard
     * with a gutter of width 1 around the top and left side. It also instantiates an array of 
     * length 8 that stores the number of the valid positions for the current position of the knight.
     * It also begins the knight's position at the upper left corner of the chessboard. 
     */
    public KnightsTour()
    {
        board = new int[9][9];               
        validMoves = new int[8];   
        myX = 1;                   
        myY = 1;                  
    }
    
    /**
     * This method will randomly move the knight to a valid position on the chessboard until
     * it either has no where else to go (meaning all the possible moves for the knight have
     * already been stepped on by the knight) or it has been to all 64 spaces on the board.
     * As the piece moves, the spaces it has stepped on will be marked with the move number 
     * on which the piece has been to the spot. At the end, the finished board will be printed
     * and the total number of spaces that have been stepped on by the knight will also be printed. 
     */
    public void tour()
    {
        int move = 1;                   //current move number
        mark(move);                     //marks the beginning position of the knight
            
        while(allValid() != 0)          //as long as there are more moves for the knight
        {
            nextSpace();                //moves the knight to the next position
            move++;                     //increments the number of moves
            mark(move);                 //marks the current move at the current position
//            printBoard();
//            System.out.println();
        }
        
        printBoard();                   //prints the finished board
        printTotal(move);               //prints the total moves
    }
    
    /**
     * This method will mark the board with the number at which the knight reaches
     * that position on the board. 
     */
    private void mark(int k)
    {
        board[myX][myY] = k;
    }
          
    /**
     * This method uses a random number generator to generate a random index given by the 
     * method allValid() which returns how many valid positions there are for the spot 
     * where the knight is currently at. By retrieving the move number from the validMoves[]
     * int position will store the move number that was randomly generated and that we know 
     * is valid. The values of myX and myY are then modified based on what is stored in xPos
     * and yPos at the position place. 
     */
    private void nextSpace()
    {
        Random die = new Random();          //random number generator
        int valids;                         //number of valid moves
        int position;                       //the chosen move
        
        valids = allValid();                //fills validMoves[] with all valid moves and returns      
                                            //total number of valid moves so a random number can be generated
        position = validMoves[die.nextInt(valids)];     //generates a random number and selects the 
                                                        //corrosponding move that is at that number
        
        myX += xPos[position];              //moves the knight by modifying myX and myY
        myY += yPos[position];
    }
    
    /**
     * This method will compile an array with the valid positions the knight can move 
     * based on where the chess piece is currently. It uses all possible move combinations
     * found in the arrays xPos and yPos that gives changes in the x position and the y 
     * position to tempoaraily move the chess piece and then it checks the temporary position 
     * to see if is within bounds. 
     */
    private int allValid()
    {
        int index = 0;                  //the number of valid moves
        int tempX, tempY;               //temporary x and y position 
        
        //resets the array of valid moves
        for (int k = 0; k < validMoves.length; k++)
        {
            validMoves[k] = 0;
        }
        
        tempX = myX;                    //first sets x and y position to where the chess piece
        tempY = myY;                    //is actually at
        
        //goes through all the possible moves
        for (int pos = 0; pos < xPos.length; pos++)
        {
            tempX += xPos[pos];         //temporarily changes the position of the knight
            tempY += yPos[pos];
            //checks if the position is valid, if it is then it adds it to the validMoves array
            if (checkValid(tempX, tempY))
            {
                validMoves[index] = pos;
                index++;                    //increments the number of valid moves
            }
            tempX = myX;                //resets the temporary valid position
            tempY = myY;
        }
        
        return index;                   //returns the total valid moves
    }
    
    /**
     * This method checks to see if the potential position of (tempX, tempY) is valid.
     * Being valid means that the piece is on the board and the spot that the knight 
     * will move to has not been stepped on yet by the knight. 
     */
    private boolean checkValid(int tempX, int tempY)
    {
        boolean valid = true;   //if the spot is valid or not
        
        if (tempX > 8 || tempY > 8)     //if the spot goes out of bound on the right or bottom
        {
            valid = false;
        }
        else
        {
            if (tempX < 1 || tempY < 1)     //if the spot goes out of bounds on the top or left
            {
                valid = false;
            }
            else
            {
                if (board[tempX][tempY] != 0)   //if the spot has already been traveled on 
                {
                    valid = false;
                }
            }   
        }
        
        return valid;           //returns if the position is valid or not
    }
    
    /**
     * This method prints the current state of the chess board by first numbering the 
     * columns on the top and numbering the rows as each row is printed. The values 
     * that are printed are the steps of the knight so it shows on what move the 
     * knight was at which position on the board. If the knight never stepped on the 
     * spot, the space is marked with the number 0.
     */
    private void printBoard()
    {
        System.out.println(" \t 1\t 2\t 3\t 4\t 5\t 6\t 7\t 8");        //columns
        for (int r = 1; r < board.length; r++)                          //row for loop
        {
            System.out.print(r);                                        //prints the number of the row
            for (int c = 1; c < board[0].length; c++)                   //column for loop
            {
                System.out.print("\t" + Format.right(board[r][c],2));   //prints the value at each position
            }
            System.out.println();       
        }
    }
    
    /**
     * This method will print the total moves that the knight made on the chess 
     * board until it either reached all 64 moves or it no longer has any valid 
     * moves to go from where the knight is. The total number of moves is passed
     * and that value is printed as the total. 
     */
    private void printTotal(int moves)
    {
        System.out.println();
        System.out.println(moves + " squares were visited");
    }
}
