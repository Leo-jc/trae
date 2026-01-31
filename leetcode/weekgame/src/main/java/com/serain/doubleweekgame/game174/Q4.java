package com.serain.doubleweekgame.game174;

import java.util.ArrayList;
import java.util.List;

public class Q4 {
    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(new Edge(v, i));
            adj.get(v).add(new Edge(u, i));
        }
        
        boolean[] needFlip = new boolean[n];
        for (int i = 0; i < n; i++) {
            needFlip[i] = (start.charAt(i) != target.charAt(i));
        }
        
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[n];
        dfs(0, -1, adj, needFlip, visited, result);
        
        for (boolean b : needFlip) {
            if (b) {
                List<Integer> res = new ArrayList<>();
                res.add(-1);
                return res;
            }
        }
        
        result.sort(Integer::compare);
        return result;
    }
    
    private void dfs(int node, int parent, List<List<Edge>> adj, boolean[] needFlip, boolean[] visited, List<Integer> result) {
        visited[node] = true;
        
        for (Edge edge : adj.get(node)) {
            int neighbor = edge.to;
            int edgeIndex = edge.index;
            
            if (!visited[neighbor] && neighbor != parent) {
                dfs(neighbor, node, adj, needFlip, visited, result);
                
                if (needFlip[neighbor]) {
                    result.add(edgeIndex);
                    needFlip[neighbor] = !needFlip[neighbor];
                    needFlip[node] = !needFlip[node];
                }
            }
        }
    }
    
    private static class Edge {
        int to;
        int index;
        
        Edge(int to, int index) {
            this.to = to;
            this.index = index;
        }
    }
}
