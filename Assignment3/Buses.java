/*
 *
 * Write the Buses program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac Buses.java
 * To execute:
 *        java Buses 7302
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */

public class Buses {
    public static void main(String[] args) {

        // WRITE YOUR CODE HERE
        int busnum = Integer.parseInt(args[0]);
        if(busnum > 999 && busnum < 10000 ) {
        int num1 = busnum % 10;
        busnum /= 10;
        int num2 = busnum % 10;
        busnum /= 10;
        int num3 = busnum % 10;
        busnum /= 10;
        int num4 = busnum % 10;
        int sum = num1 + num2 + num3 + num4;
        if (sum % 2 == 0){
            System.out.println("LX");
        }
        else {
            System.out.println("H");
        }
        }
        else {
            System.out.println("ERROR");
        }

    }
}
