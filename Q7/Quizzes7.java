import java.io.*;
import java.util.*;

public class Quizzes7 {
    public static void main(String[] args) {
        //Becasue 5 graphs in one input file,
        //so I need to get the start and end line of each graph in order to seperate them.
        ArrayList<String> list = new ArrayList<>(readCSV("Quiz7_Input_File.csv"));
        ArrayList<Integer> spaceline = new ArrayList<>(findSpaceLine(list));
        //Test case1:
        int[][] matrix1 = to2DArray(list, 1, spaceline.get(0) - 1);
        int i1 = matrix1[matrix1.length - 1][0], j1 = matrix1[matrix1.length - 1][1];
        int[] arr1 = to1D(matrix1);
        System.out.println("Test case1:");
        use1D(arr1, i1, j1);
        //Test case2:
        int[][] matrix2 = to2DArray(list, spaceline.get(0) + 1, spaceline.get(1) - 1);
        int i2 = matrix2[matrix2.length - 1][0], j2 = matrix2[matrix2.length - 1][1];
        int[] arr2 = to1D(matrix2);
        System.out.println("Test case2:");
        use1D(arr2, i2, j2);
        //Test case3:
        int[][] matrix3 = to2DArray(list, spaceline.get(1) + 1, spaceline.get(2) - 1);
        int i3 = matrix3[matrix3.length - 1][0], j3 = matrix3[matrix3.length - 1][1];
        int[] arr3 = to1D(matrix3);
        System.out.println("Test case3:");
        use1D(arr3, i3, j3);
        //Test case4:
        int[][] matrix4 = to2DArray(list, spaceline.get(2) + 1, spaceline.get(3) - 1);
        int i4 = matrix4[matrix4.length - 1][0], j4 = matrix4[matrix4.length - 1][1];
        int[] arr4 = to1D(matrix4);
        System.out.println("Test case4:");
        use1D(arr4, i4, j4);
        //Test case5:
        int[][] matrix5 = to2DArray(list, spaceline.get(3) + 1, list.size());
        int i5 = matrix5[matrix5.length - 1][0], j5 = matrix5[matrix5.length - 1][1];
        int[] arr5 = to1D(matrix5);
        System.out.println("Test case5:");
        use1D(arr5, i5, j5);
    }
    public static void use1D(int[] arr, int i, int j){
        //A, B, C
        int elemnum = arr.length;
        int nodenum = (int)Math.sqrt(elemnum * 2) + 1;
        int linknum = 0;
        for(int x = 0; x < elemnum; x++){
            if(arr[x] > 0){
                linknum++;
            }
        }
        System.out.println("Total number of nodes: " + nodenum);
        System.out.println("Total number of links: " + linknum);
        System.out.println("Total number of elements " + elemnum);
        //D
        if(i == j){
            System.out.println("Do not have corresponding index in 1D array");
        }else{
            if(i < j){
                int temp = i;
                i = j;
                j = temp;
            }
            int index = (i - 1) * (i - 2) / 2 + j;
            System.out.println("The 1D array index for the link between " + i + " and " + j + " is: " + index);
        }

        //E
        boolean exist = false;
        ArrayList<Integer> isonode = new ArrayList<>();
        for(int x = 1; x <= nodenum; x++){
            boolean onenode = true;
            for(int y = 1; y < x; y++){
                int ind = (x - 1) * (x - 2) / 2 + y - 1;
                if(arr[ind] > 0){
                    onenode = false;
                    break;
                }
            }
            for(int y = x + 1; y <= nodenum; y++){
                int ind = (y - 1) * (y - 2) / 2 + x - 1;
                if(arr[ind] > 0){
                    onenode = false;
                    break;
                }
            }
            if(onenode){
                isonode.add(x);
                exist = true;
            }
        }
        if(exist){
            System.out.print("Has isolated nodes: ");
            for(int x = 0; x < isonode.size(); x++){
                System.out.print(isonode.get(x) + " ");
            }
            System.out.println();
        }else{
            System.out.println("No isolated nodes");
        }
        //F
        int linkpos = nodenum * (nodenum - 1) / 2;
        if((double)linknum < (double)linkpos / 2){
            System.out.println("The graph is sparse");
        }else{
            System.out.println("The graph is dense");
        }
    }
    public static int[] to1D(int[][] matrix){
        int n = matrix[0].length;
        int length = n * (n - 1) / 2;
        int[] arr = new int[length];
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                int index = ((i + 1) - 1) * ((i + 1) - 2) / 2 + (j + 1);
                arr[index - 1] = matrix[i][j];
            }
        }
        return arr;
    }
    public static int[][] to2DArray(ArrayList<String> list, int startline, int endline){
        //Becasue 5 graph in ont input file, so I need to initilize the start and end line of each graph.
        int n = endline - startline;
        int[][] matrix = new int[n + 1][n];
        for(int i = startline - 1; i < endline - 1; i++){
            String curr = list.get(i);
            String[] strarr = curr.split(", ");
            for(int j = 0; j < n; j++){
                int v = Integer.parseInt(strarr[j]);
                matrix[i - startline + 1][j] = v;
            }
        }
        String lastline = list.get(endline - 1);
        String[] lastarr = lastline.split(", ");
        matrix[n][0] = Integer.parseInt(lastarr[0].substring(2, 3));
        matrix[n][1] = Integer.parseInt(lastarr[1].substring(2, 3));
        return matrix;
    }
    public static ArrayList<Integer> findSpaceLine(ArrayList<String> list){
        int n = list.size();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            String curr = list.get(i);
            if(curr.equals("")){
                ans.add(i + 1);
            }
        }
        return ans;
    }
    public static ArrayList<String> readCSV(String filePath) {
        File csv = new File(filePath);
        csv.setReadable(true);
        csv.setWritable(true);
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(new FileInputStream(csv), "UTF-8");
            br = new BufferedReader(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line = "";
        ArrayList<String> records = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
