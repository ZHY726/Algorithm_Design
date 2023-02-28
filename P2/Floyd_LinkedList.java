import java.io.*;
import java.util.*;

public class Floyd_LinkedList{
    public static void main(String[] args){
        Runtime runtime = Runtime.getRuntime();

        Graph1 graph= toGraph(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
        long start1 = System.currentTimeMillis();
        FloydLL(graph, 192, 163);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1 + "ms");

//        Graph1 graph= toGraph(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
//        long start1 = System.currentTimeMillis();
//        FloydLL(graph, 138, 66);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1 + "ms");

//        Graph1 graph= toGraph(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
//        long start1 = System.currentTimeMillis();
//        FloydLL(graph, 465, 22);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1 + "ms");

        System.out.println("Floyd Linked List File4:");
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("Total Memory：" + totalMemory);
        System.out.println("Free Memory：" + freeMemory);
        System.out.println("Used Memory：" + usedMemory);
    }
    public static class Node1{
        int vertex, weight;
        Node1(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    public static class Graph1{
        int n;
        LinkedList<Node1>[] ll;
        Graph1(int n) {
            this.n = n;
            ll = new LinkedList[n];
            for(int i = 0; i < n; i++){
                ll[i] = new LinkedList<>();
            }
        }
        void add(int start, int end, int weight){
            ll[start].add(new Node1(end, weight));
        }
    }
    public static Graph1 toGraph(ArrayList<String> list){
        int n = list.size();
        String last = list.get(n - 1);
        String[] str = last.split(",");
        int nodenum = Integer.parseInt(str[0]) + 1;
        Graph1 graph = new Graph1(nodenum);
        for(int i = 1; i < n; i++){
            String temp = list.get(i);
            String[] tempstr = temp.split(",");
            int node1 = Integer.parseInt(tempstr[0]);
            int node2 = Integer.parseInt(tempstr[1]);
            int dist = Integer.parseInt(tempstr[2]);
            graph.add(node1, node2, dist);
        }
        return graph;
    }
    public static void FloydLL(Graph1 graph, int b, int e){
        int[][] dist = new int[graph.n][graph.n];
        int[][] next = new int[graph.n][graph.n];

        for (int i = 0; i < graph.n; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            Arrays.fill(next[i], -1);
            dist[i][i] = 0;
            for (Node1 node : graph.ll[i]){
                dist[i][node.vertex] = node.weight;
                next[i][node.vertex] = node.vertex;
            }
        }

        for (int k = 0; k < graph.n; k++){
            for (int i = 0; i < graph.n; i++){
                for (int j = 0; j < graph.n; j++){
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        for (int i = 0; i < graph.n; i++){
            for (int j = 0; j < graph.n; j++){
                if (i != j && dist[i][j] != Integer.MAX_VALUE){
                    if(i == b && j == e){
                        System.out.println("Distance: " + dist[i][j] + " feet");
                        System.out.print("Path: [");
                        pathNode(next, i, j);
                        System.out.println("]");
                    }
                }
            }
        }
    }
    public static void pathNode(int[][] next, int u, int v){
        if (next[u][v] == -1){
            System.out.println("No path exists between " + u + " and " + v);
        }else{
            System.out.print(u + ", ");
            while(u != v){
                u = next[u][v];
                if(u != v) {
                    System.out.print(u + ", ");
                }else{
                    System.out.print(u);
                }
            }
        }
    }
    public static ArrayList<String> readCSV(String filePath){
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
