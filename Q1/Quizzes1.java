public class Quizzes1 {
    public static void main(String[] args) {
        int[] test1 = new int[]{1, 2, 3, 4};
        int[] test2 = new int[]{7, 3};
        int[] test3 = new int[]{4, 1, 7, 4, 3, 9, 1, 5};
        System.out.println("Test case1:");
        Quizzes1.subset(test1);
        System.out.println("Test case2:");
        Quizzes1.subset(test2);
        System.out.println("Test case3:");
        Quizzes1.subset(test3);
    }
    public static void subset(int[] arr){
        int n = arr.length;
        if(n < 3){
            System.out.println("no subset");
            return;
        };
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                for(int k = j + 1; k < n; k++){
                    System.out.println(arr[i] + " " + arr[j] + " " + arr[k]);
                }
            }
        }
    }
}
