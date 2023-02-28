import java.io.*;
import java.util.*;

public class Dijkstra_LinkedList{
    public static void main(String[] args){
        Runtime runtime = Runtime.getRuntime();

        Graph2 graph= toGraph(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
        long start1 = System.currentTimeMillis();
        DijkstraLL(graph, 192, 163);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1 + "ms");

//        Graph2 graph= toGraph(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
//        long start1 = System.currentTimeMillis();
//        DijkstraLL(graph, 138, 66);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1 + "ms");

//        Graph2 graph= toGraph(readCSV("Project2_Input_Files/Project2_Input_File4.csv"));
//        long start1 = System.currentTimeMillis();
//        DijkstraLL(graph, 465, 22);
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1 + "ms");

        System.out.println("Dijkstra Linked List File4:");
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("Total Memory：" + totalMemory);
        System.out.println("Free Memory：" + freeMemory);
        System.out.println("Used Memory：" + usedMemory);
    }
    public static class Node2{
        int vertex, weight;
        Node2(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    public static class Graph2{
        int n;
        LinkedList<Node2>[] ll;
        Graph2(int n) {
            this.n = n;
            ll = new LinkedList[n];
            for(int i = 0; i < n; i++){
                ll[i] = new LinkedList<Node2>();
            }
        }
        void add(int start, int end, int weight){
            Node2 node = new Node2(end, weight);
            ll[start].add(node);
        }
    }
    public static Graph2 toGraph(ArrayList<String> list){
        int n = list.size();
        String last = list.get(n - 1);
        String[] str = last.split(",");
        int nodenum = Integer.parseInt(str[0]) + 1;
        Graph2 graph = new Graph2(nodenum);
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
    static void DijkstraLL(Graph2 graph, int b, int e){
        int[] dist = new int[graph.n];
        int[] prev = new int[graph.n];
        boolean[] visited = new boolean[graph.n];
        for(int i = 0; i < graph.n; i++){
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        dist[b] = 0;
        prev[b] = -1;
        for(int i = 0; i < graph.n - 1; i++){
            int min = Integer.MAX_VALUE;
            int minv = -1;
            for (int j = 0; j < dist.length; j++) {
                if (!visited[j] && dist[j] <= min) {
                    min = dist[j];
                    minv = j;
                }
            }
            visited[minv] = true;
            for(Node2 node : graph.ll[minv]){
                int v = node.vertex;
                int weight = node.weight;
                if (!visited[v] && dist[minv] != Integer.MAX_VALUE && dist[minv] + weight < dist[v]) {
                    dist[v] = dist[minv] + weight;
                    prev[v] = minv;
                }
            }
        }
        System.out.println("Distance: " + dist[e] + " feet");
        System.out.print("Path: [");
        pathNode(prev, e, dist, e);
        System.out.println("]");
    }
    static void pathNode(int[] prev, int vertex, int[] dist, int e){
        if(vertex < 0){
            return;
        }
        pathNode(prev, prev[vertex], dist, e);
        if(vertex != e){
            System.out.print(vertex + ", ");
        }else{
            System.out.print(vertex);
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