package com.serain.doubleweekgame.game174;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：4. 最小边翻转次数
 * 作者：serain
 * 日期：2026-01-19
 * 描述：计算将 start 转换为 target 所需的最小边翻转次数，返回需要翻转的边的索引列表。
 * 解法：使用深度优先搜索，检查每个节点的翻转需求并记录需要翻转的边。
 */
public class Q4 {
    /**
     * Find the minimum number of edge flips to convert start to target
     * 
     * @param n Number of nodes
     * @param edges Edge array with edge indices
     * @param start Starting colors
     * @param target Target colors
     * @return List of edge indices to flip, or [-1] if impossible
     */
    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        // Convert edges to adjacency list with edge indices
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
        
        // Check if solvable: each node's required flip parity must match
        boolean[] needFlip = new boolean[n];
        for (int i = 0; i < n; i++) {
            needFlip[i] = (start.charAt(i) != target.charAt(i));
        }
        
        // Perform DFS to find edges to flip
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[n];
        dfs(0, -1, adj, needFlip, visited, result);
        
        // Check if all nodes are now correct
        for (boolean b : needFlip) {
            if (b) {
                return List.of(-1);
            }
        }
        
        // Sort the result by edge indices to get lex smallest order
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
                
                // If the child node still needs to be flipped, flip this edge
                if (needFlip[neighbor]) {
                    result.add(edgeIndex);
                    // Flipping the edge toggles both nodes
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
