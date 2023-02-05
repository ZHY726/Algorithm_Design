public class Problem2_2 {
    public static void main(String[] args) {
        System.out.println("Test case1:");
        System.out.println(RM(7000, 7294));

        System.out.println("Test case2:");
        System.out.println(RM(25, 5038385));

        System.out.println("Test case3:");
        System.out.println(RM(-59724, 783));

        System.out.println("Test case4:");
        //System.out.println(RM(8516, -82147953548159344));
        System.out.println("The input is out of range of the integer");

        System.out.println("Test case5:");
        //System.out.println(RM(45952456856498465985, 98654651986546519856));
        System.out.println("The input is out of range of the integer");

        System.out.println("Test case6:");
        //System.out.println(RM(-45952456856498465985, -98654651986546519856));
        System.out.println("The input is out of range of the integer");
    }
    public static int[] toIntArr(int num){
        int temp = num;
        int n = 0;
        while(temp != 0){
            temp /= 10;
            n++;
        }
        int[] ans = new int[n];
        for(int i = n - 1; i >= 0; i--){
            ans[i] = num % 10;
            num /= 10;
        }
        return ans;
    }
    public static int toInt(int[] arr){
        int n = arr.length;
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans *= 10;
            ans += arr[i];
        }
        return ans;
    }
    public static int RM(int num1, int num2){
        int[] arr1 = toIntArr(num1);
        int[] arr2 = toIntArr(num2);
        int n1 = arr1.length, n2 = arr2.length;
        int n = n1 + n2;
        int[] arr = new int[n];
        for(int i = n1 - 1; i >= 0; i--){
            for(int j = n2 - 1; j >= 0; j--){
                arr[i + j + 1] += arr1[i] * arr2[j];
            }
        }
        for(int i = n - 1; i > 0; i--){
            arr[i - 1] += arr[i] / 10;
            arr[i] %= 10;
        }
        int ans = toInt(arr);
        return ans;
    }
}
