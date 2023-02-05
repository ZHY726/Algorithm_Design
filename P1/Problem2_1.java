public class Problem2_1 {
    public static void main(String[] args) {
        System.out.println("Test case1:");
        System.out.println(ACM(7000, 7294));

        System.out.println("Test case2:");
        System.out.println(ACM(25, 5038385));

        System.out.println("Test case3:");
        System.out.println(ACM(-59724, 783));

        System.out.println("Test case4:");
        //System.out.println(ACM(8516, -82147953548159344));
        System.out.println("The input is out of range of the integer");

        System.out.println("Test case5:");
        //System.out.println(ACM(45952456856498465985, 98654651986546519856));
        System.out.println("The input is out of range of the integer");

        System.out.println("Test case6:");
        //System.out.println(ACM(-45952456856498465985, -98654651986546519856));
        System.out.println("The input is out of range of the integer");

    }
    public static int ACM(int num1, int num2){
        int ans = 0;
        int st1 = num1, st2 = num2;
        if(num1 < 0){
            num1 *= -1;
        }
        if(num2 < 0){
            num2 *= -1;
        }
        while(num1 != 0){
            if(num1 % 2 == 1){
                ans += num2;
            }
            num1 /= 2;
            num2 *= 2;
        }
        if(st1 < 0 || st2 < 0){
            ans *= -1;
        }
        if(st1 < 0 && st2 < 0){
            ans *= -1;
        }
        return ans;
    }
}
