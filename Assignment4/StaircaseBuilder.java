/*
 * Write your program inside the main method to build
 * a staicase in a 2D array of characters according
 * to the assignment description
 *
 * To compile:
 *        javac StaircaseBuilder.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class StaircaseBuilder {
    
    public static void main(String[] args) {

        // WRITE YOUR CODE HERE
        
        int d = Integer.parseInt(args[0]);
        int bricks = Integer.parseInt(args[1]);
        char[][] arr = new char[d][d];

        int count = 0;
        int realCol = 1;
        int realRow = 1;
        int cBricks = bricks;

        for (int col = 0; col < d;col++) {
            for(int row = d-1;row >= 0; row--) {
                    if (cBricks == 0) {
                        arr[row][col] = ' ';   
                }
                    else if (realRow <= realCol) {
                        arr[row][col] = 'X';
                        count++;
                        cBricks--;
                        }
                    else {
                        arr[row][col] = ' ';
                        }

                    realRow++;
                }
                realCol++;
                realRow = 1;
            }

        for (int z = 0; z < arr.length; z++) {
          for (int y = 0; y < arr[0].length; y++) {
            System.out.print(arr[z][y]); 
          }
          System.out.println();
      }

        System.out.println("Bricks remaining: " + (bricks - count));


    }
}
