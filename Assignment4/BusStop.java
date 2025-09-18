/*
 * Write your program inside the main method to find the order
 * which the bus the student needs to take will arrive
 * according to the assignemnt description. 
 *
 * To compile:
 *        javac BusStop.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class BusStop {

    public static void main(String[] args) {

        // WRITE YOUR CODE HERE
        int[] arr = new int[args.length - 1];
        int order = 1000;

        for (int i = 0; i < args.length - 1; i++) {
            arr[i] = Integer.parseInt(args[i]);
            if (arr[i] == Integer.parseInt(args[args.length - 1])) {
                order = i + 1;
            }
        }

        /* 
        for (int x: arr) {
            System.out.print(x + " ");
        }  */

        System.out.println(order);

    }
}
