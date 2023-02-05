public class Quizzes2 {
    public static void main(String[] args) {
        int[][] case1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Test case1:");
        search(case1, 8);

        int[][] case2 = new int[][]{{2, 4, 9, 14, 14, 15, 18}, {21, 27, 31, 35, 42, 45, 50}, {54, 59, 64, 69, 82, 84, 84}};
        System.out.println("Test case2:");
        search(case2, 45);

        int[][] case3 = new int[][]{{3, 15, 21, 24, 83, 87, 88, 93},
                                    {178, 319, 541, 669, 770, 793, 848, 970},
                                    {1439, 1546, 1853, 2124, 2234, 2459, 2518, 2978},
                                    {3111, 3406, 3490, 3669, 3796, 3936, 4112, 4776},
                                    {5277, 5667, 6067, 6555, 7890, 8056, 8234, 9968}};
        System.out.println("Test case3:");
        search(case3, 2356);
    }

    public static void search(int[][] table, int target){
        int n = table.length;
        int i = n - 1;
        while(i >= 0){
            if(table[i][0] > target){
                i--;
            }else{
                break;
            }
        }
        int j = binary_search(table[i], target);
        if(j == -1){
            System.out.println("target value not exists");
        }else{
            System.out.println("target position:" + " [" + i + " " + j + "]");
        }
    }

    public static int binary_search(int[] arr, int target){
        int n = arr.length;
        int l = 0, r = n - 1;
        int m;
        while(l <= r){
            m = l + (r - l) / 2;
            if(arr[m] == target){
                return m;
            }else if(target < arr[m]){
                r = m - 1;
            }else{
                l = m + 1;
            }
        }
        return -1;
    }
}
