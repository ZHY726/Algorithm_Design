import java.io.*;
import java.util.*;

public class Floyd_Array {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        long start1 = System.currentTimeMillis();
        int[][] matrix = to2DArray(readCSV("Project2_Input_Files/Project2_Input_File14.csv"));
        Floyd2D(matrix, 192, 163);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1 + "ms");

//        int[][] matrix = to2DArray(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
//        long start1 = System.currentTimeMillis();
//        Floyd2D(matrix, 138, 66);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1 + "ms");

//        int[][] matrix = to2DArray(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
//        long start1 = System.currentTimeMillis();
//        Floyd2D(matrix, 465, 22);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1 + "ms");

        System.out.println("Floyd 2D Array File4:");
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("Total Memory：" + totalMemory);
        System.out.println("Free Memory：" + freeMemory);
        System.out.println("Used Memory：" + usedMemory);

    }
    public static void Floyd2D(int[][] matrix, int b, int e){
        int n = matrix.length;
        int[][] dist = new int[n][n];
        int[][] next = new int[n][n];
        ArrayList<Integer> path = new ArrayList<>();
        //Initialize
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    dist[i][j] = Integer.MAX_VALUE;
                }else {
                    dist[i][j] = matrix[i][j];
                }
                next[i][j] = j;
            }
        }
        //Floyd's algorithm
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
        //output min distance
        System.out.println("Distance: " + dist[b][e] + " feet");
        //output nodes on the path
        path.add(b);
        while(b != e){
            b = next[b][e];
            path.add(b);
        }
        printlist(path);
    }
    public static void printlist(ArrayList<Integer> list){
        int n = list.size();
        System.out.print("Path: [");
        for(int i = 0; i < n; i++){
            System.out.print(list.get(i));
            if(i != n - 1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static int[][] to2DArray(ArrayList<String> list){
        int n = list.size();
        String last = list.get(n - 1);
        String[] str = last.split(",");
        int nodenum = Integer.parseInt(str[0]) + 1;
        int[][] matrix = new int[nodenum][nodenum];
        for(int i = 1; i < n; i++){
            String temp = list.get(i);
            String[] tempstr = temp.split(",");
            int node1 = Integer.parseInt(tempstr[0]);
            int node2 = Integer.parseInt(tempstr[1]);
            int dist = Integer.parseInt(tempstr[2]);
            matrix[node1][node2] = dist;
        }
        return matrix;
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
