public class App {
    public static void main(String[] args) throws Exception {
        int[][] arr = {{0,1,0,1},{1,1,0,0,0},{1,0,0,0}};
        int one_count = 0;
        int zero_count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int k = 0; k < arr[0].length; k++) {
                if (arr[i][k] == 1) {
                    one_count++;
                }
                else if (arr[i][k] == 0) {
                    zero_count++;
                }
            }
        }
        
        System.out.print("1 count: " + one_count + "0 count: " + zero_count);
    }
}
