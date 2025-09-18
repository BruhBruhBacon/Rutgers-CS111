/*
 *  
 * Write the DogWalker program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac DogWalker.java
 * To execute:
 *        java DogWalker 5
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 *
 */
public class DogWalker {

    public static void main(String[] args) {

       // WRITE YOUR CODE HERE
       /*int steps = Integer.parseInt(args[0]);
       int x = 0;
       int y = 0;
       int count = 0;
       System.out.println("(" + x + "," + y + ")");
       while (count < steps) {
       int random = (int)(4 * Math.random());
       if (random == 0) {
        x -= 1;
       }
       else if (random == 1) {
        x += 1;
       }
       else if (random == 2) {
        y -= 1;
       }
       else if (random == 3) {
        y += 1;
       }
       System.out.println("(" + x + "," + y + ")");
       count += 1;
       }
       double sq = (x * x) + (y * y);
       System.out.println("Squared distance = " + sq); */

       int num = Integer.parseInt(args[0]);
        for (int x = 0;x <= num; x++) {
        for (int y = 0;y < x; y++) {
        System.out.print("*");
        }
        System.out.println(" ");
    }
        for (int i = num - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
