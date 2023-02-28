import java.io.*;
import java.util.*;

public class Dijkstra_Array {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        int[][] matrix = to2DArray(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
        long start1 = System.currentTimeMillis();
        Dijkstra2D(matrix, 192, 163);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1 + "ms");

//        int[][] matrix = to2DArray(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
//        long start1 = System.currentTimeMillis();
//        Dijkstra2D(matrix, 138, 66);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1 + "ms");

//        int[][] matrix = to2DArray(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
//        long start1 = System.currentTimeMillis();
//        Dijkstra2D(matrix, 465, 22);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1 + "ms");

        System.out.println("Dijkstra 2D Array File4:");
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("Total Memory：" + totalMemory);
        System.out.println("Free Memory：" + freeMemory);
        System.out.println("Used Memory：" + usedMemory);
    }
    public static void Dijkstra2D(int[][] matrix, int b, int e){
        int n = matrix.length;
        int[] dist = new int[n];
        int[] prev = new int[n];
        ArrayList<Integer> path = new ArrayList<>();
        //Initialize
        for(int i = 0; i < n; i++){
            if(i == b){
                continue;
            }
            dist[i] = Integer.MAX_VALUE;
        }
        for(int i = 0; i < n; i++){
            prev[i] = -1;
        }
        //Dijkstra's algorithm
        PriorityQueue<Integer> heap = new PriorityQueue<>((x, y) -> dist[x] - dist[y]);
        heap.offer(b);
        while(!heap.isEmpty()){
            int temp = heap.poll();
            if(temp == e){
                break;
            }
            for(int i = 0; i < n; i++){
                if(matrix[temp][i] != 0){
                    if(dist[temp] + matrix[temp][i] < dist[i]){
                        dist[i] = dist[temp] + matrix[temp][i];
                        prev[i] = temp;
                        heap.offer(i);
                    }
                }
            }
        }
        //output min distance
        System.out.println("Distance: " + dist[e] + " feet");
        //output nodes on the path
        while(e != -1){
            path.add(e);
            e = prev[e];
        }
        printlistrev(path);
    }
    public static void printlistrev(ArrayList<Integer> list){
        int n = list.size();
        System.out.print("Path: [");
        for(int i = n - 1; i >= 0; i--){
            System.out.print(list.get(i));
            if(i != 0){
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
