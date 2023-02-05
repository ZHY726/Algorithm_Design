public class Quizzes3 {
    public static void main(String[] args) {
        //case1 : k = 15, n from 15 to 35;
        System.out.println("case1:");
        for(int n = 15; n <= 35; n++) {
            long start1 = System.currentTimeMillis();
            System.out.println(DAC(n, 15));
            long end1 = System.currentTimeMillis();
            System.out.println(end1 - start1 + "ms");
        }
        //case2 : n = 30, n from 1 to 20;
        System.out.println("case2:");
        for(int k = 1; k <= 20; k++) {
            long start2 = System.currentTimeMillis();
            System.out.println(DAC(30, k));
            long end2 = System.currentTimeMillis();
            System.out.println(end2 - start2 + "ms");
        }
        //case3 : k = 1000, n from 0 to 200000;
        System.out.println("case3:");
        for(int n = 10000; n <= 200000; n += 10000) {
            long start3 = System.currentTimeMillis();
            System.out.println(DP(n, 1000));
            long end3 = System.currentTimeMillis();
            System.out.println(end3 - start3 + "ms");
        }
        //case4 : n = 20000, k from 0 to 20000;
        System.out.println("case4:");
        for(int k = 0; k <= 20000; k += 1000) {
            long start2 = System.currentTimeMillis();
            System.out.println(DP(20000, k));
            long end2 = System.currentTimeMillis();
            System.out.println(end2 - start2 + "ms");
        }
    }
    public static int DAC(int n, int k){
        if(k == 0 || n == k){
            return 1;
        }else{
            return DAC(n - 1, k - 1) + DAC(n - 1, k);
        }
    }

    public static int DP(int n, int k){
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= Math.min(i, k); j++){
                if(j == 0 || j == i){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[n][k];
    }
}
