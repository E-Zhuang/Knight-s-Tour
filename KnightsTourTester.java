
/**
 * Lab 18.2 Knight's Tour
 * 
 * Emily Zhuang
 * Mr. Lantsberger
 * APCS:Period 4
 * 31 December 2016
 */

public class KnightsTourTester
{
    /**
     * Here is the main() method. This program will simulate a knight's chess 
     * piece as it moves across an 8 x 8 chessboard, marking each move on the 
     * board as it goes. The positions the knight goes is randomly selected 
     * until the knight no longer has any valid moves left. That means the knight
     * has either traveled to all 64 spots on the board or all the valid positions 
     * around the knight have already been traveled to (so the knight can only go 
     * to each spot on the board once). 
     */
    public static void main(String[] args)
    {
        KnightsTour knight = new KnightsTour();
        
        knight.tour();      //runs and prints the simulation 
    }
}
