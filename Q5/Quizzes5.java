import java.util.Arrays;

public class Quizzes5 {
    public static void main(String[] args) {
        //case 1:
        int[] case1 = new int[]{};
        System.out.println("Test case1:");
        maxsum(case1);
        //case 2:
        int[] case2 = new int[]{1};
        System.out.println("Test case2:");
        maxsum(case2);
        //case 3:
        int[] case3 = new int[]{1, 2, 3, 4};
        System.out.println("Test case3:");
        maxsum(case3);
        //case 4:
        int[] case4 = new int[]{-7, -4, -2, -8};
        System.out.println("Test case4:");
        maxsum(case4);
        //case 5:
        int[] case5 = new int[]{-2, 3, 5, -7};
        System.out.println("Test case5:");
        maxsum(case5);
        //case 6:
        int[] case6 = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Test case6:");
        maxsum(case6);
        //case 7:
        int[] case7 = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test case7:");
        maxsum(case7);
    }
    public static void maxsum(int[] nums){
        if(nums.length == 0){
            System.out.println("sublist: []");
            System.out.println("max sum: 0");
            return;
        }
        int max = nums[0], sum = nums[0];
        int ml = 0, mr = 0;
        int l = 0, r = 0;
        int n = nums.length;
        for(int i = 1; i < n; i++){
            if(sum + nums[i] > nums[i]){
                r = i;
                sum = sum + nums[i];
            }else{
                l = i;
                r = i;
                sum = nums[i];
            }
            if(sum > max){
                ml = l;
                mr = r;
                max = sum;
            }
        }
// This code is for the condition that the sublist can be empty. I have already analyzed the output in my report.
//        if(max < 0){
//            System.out.println("sublist: []");
//            System.out.println("max sum: 0");
//            return;
//        }
        System.out.println("sublist: " + Arrays.toString(Arrays.copyOfRange(nums, ml, mr + 1)));
        System.out.println("max sum: " + max);
    }
}
