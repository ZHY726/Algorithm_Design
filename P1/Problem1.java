public class Problem1 {
    public static void main(String[] args) {
        int[] case1_1 = new int[]{};
        int[] case1_2 = new int[]{3, 7, 9};
        System.out.println("Test case1:");
        merge(case1_1, case1_2);

        int[] case2_1 = new int[]{2, 7, 9};
        int[] case2_2 = new int[]{1};
        System.out.println("Test case2:");
        merge(case2_1, case2_2);

        int[] case3_1 = new int[]{1, 7, 10, 15};
        int[] case3_2 = new int[]{3, 8, 12, 18};
        System.out.println("Test case3:");
        merge(case3_1, case3_2);

        int[] case4_1 = new int[]{1, 3, 5, 5, 15, 18, 21};
        int[] case4_2 = new int[]{5, 5, 6, 8, 10, 12, 16, 17, 17, 20, 25, 28};
        System.out.println("Test case4:");
        merge(case4_1, case4_2);
    }
    public static void merge(int[] arr1, int[] arr2){
        int n = arr1.length, m = arr2.length;
        int[] arr = new int[n + m];
        int i = 0, j = 0, k = 0;
        while(i < n && j < m){
            if(arr1[i] < arr2[j]){
                arr[k++] = arr1[i++];
            }else{
                arr[k++] = arr2[j++];
            }
        }
        while(i < n){
            arr[k++] = arr1[i++];
        }
        while(j < m){
            arr[k++] = arr2[j++];
        }
        System.out.print("A = [");
        for(int x = 0; x < n + m; x++) {
            System.out.print(arr[x]);
            if(x == n + m - 1){
                break;
            }
            System.out.print(" ");
        }
        System.out.println("]");
    }
}
