import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Quizzes4 {
    public static void main(String[] args) {
        //case 1:
        System.out.println("Test case 1:");
        output(ReadFromTXT("test1.txt"));
        //case 2:
        System.out.println("Test case 2:");
        output(ReadFromTXT("test2.txt"));
        //case 3:
        System.out.println("Test case 3:");
        output(ReadFromTXT("test3.txt"));
        //case 4:
        System.out.println("Test case 4:");
        output(ReadFromTXT("test4.txt"));
    }
    public static boolean connected(int[][] matrix){
        int n = matrix.length;
        boolean[] visited = new boolean[n];
        dfs_connected(matrix, visited, 0);
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
    public static void dfs_connected(int[][] matrix,boolean[] visited, int index){
        visited[index] = true;
        int n = matrix.length;
        for(int i = 0; i < n; i++){
            if(matrix[index][i] == 1 && !visited[i]){
                dfs_connected(matrix, visited, i);
            }
        }
    }
    public static boolean complete(int[][] matrix){
        int n = matrix.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j != i && j < n; j++){
                if(matrix[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    public static int[] indegree(int[][] matrix){
        int n = matrix.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 1){
                    ans[j]++;
                }
            }
        }
        return ans;
    }
    public static int[] outdegree(int[][] matrix){
        int n = matrix.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 1){
                    ans[i]++;
                }
            }
        }
        return ans;
    }
    public static int[] totaldegree(int[][] matrix){
        int n = matrix.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            ans[i] = indegree(matrix)[i] + outdegree(matrix)[i];
        }
        return ans;
    }
    public static int[][] ReadFromTXT(String path){
        File file1 = new File(path);
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        List<List<Integer>> matrixlist = new ArrayList<>();
        try {
            fis = new FileInputStream(file1);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String lineTxt;
            while ((lineTxt = br.readLine()) != null) {
                List<Integer> cur = new ArrayList<>();
                int n = lineTxt.length();
                for(int i = 0; i < n; i++){
                    cur.add(lineTxt.charAt(i) - '0');
                }
                matrixlist.add(cur);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = matrixlist.get(0).size();
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = matrixlist.get(i).get(j);
            }
        }
        return matrix;
    }
    public static void output(int[][] matrix){
        if(connected(matrix)){
            System.out.println("Connected");
        }else{
            System.out.println("Not Connected");
        }
        if(complete(matrix)){
            System.out.println("Complete");
        }else{
            System.out.println("Not Complete");
        }
        int n = matrix.length;
        for(int i = 0; i < n; i++){
            int j = i + 1;
            System.out.println("Total degree of node" + j + ": " + totaldegree(matrix)[i] + "; in-degree: " + indegree(matrix)[i] + "; out-degree: " + outdegree(matrix)[i]);
        }
    }
}
