import java.io.*;
import java.util.*;

public class Quizzes6{
    public static void main(String[] args){
        int[][] matrix = to2DArray(readCSV("Quiz6_Input_File.csv"));
        prim(matrix);
    }
    public static void prim(int[][] matrix){
        int n = matrix.length;
        int[] par = new int[n];
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        System.out.println("Node Node Distance");
        //Initialization
        for(int i = 0; i < n; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        heap.offer(new int[]{0, 0});
        //Prim's algorithm(I use heap for optimization)
        while(!heap.isEmpty()){
            int[] temp = heap.poll();
            int cur = temp[0];
            int weight = temp[1];
            if(!visited[cur]){
                visited[cur] = true;
                if(cur != 0){
                    if(par[cur] < 10){
                        if(cur < 10){
                            System.out.println(par[cur] + "    " + cur + "    " + weight);
                        }else if(cur < 100){
                            System.out.println(par[cur] + "    " + cur + "   " + weight);
                        }else{
                            System.out.println(par[cur] + "    " + cur + "  " + weight);
                        }
                    }else{
                        if(cur < 10){
                            System.out.println(par[cur] + "   " + cur + "    " + weight);
                        }else if(cur < 100){
                            System.out.println(par[cur] + "   " + cur + "   " + weight);
                        }else{
                            System.out.println(par[cur] + "   " + cur + "  " + weight);
                        }
                    }
                }
                for(int i = 0; i < n; i++){
                    if(matrix[cur][i] != 0 && !visited[i] && matrix[cur][i] < dist[i]){
                        par[i] = cur;
                        dist[i] = matrix[cur][i];
                        heap.offer(new int[]{i, dist[i]});
                    }
                }
            }
        }
        int sum = 0;
        for(int i = 1; i < n; i++){
            sum += matrix[i][par[i]];
        }

        System.out.println("Total distance: " + sum + "feet");
    }
    public static int[][] to2DArray(ArrayList<String> list){
        int n = list.size() - 1;
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            String temp = list.get(i + 1);
            String[] temparr = temp.split(",");
            for(int j = 0; j < n; j++){
                matrix[i][j] = Integer.parseInt(temparr[j]);
            }
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
                //System.out.println(line);
                records.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
