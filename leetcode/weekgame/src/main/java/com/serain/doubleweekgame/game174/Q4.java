/**
 * @author Serain
 * @date 2026-01-31
 * @description 计算最小翻转次数
 * 给定一个无向树，每个节点有一个初始状态和目标状态，每次操作可以翻转一条边上的所有节点状态。
 * 计算将所有节点从初始状态转换为目标状态所需的最小翻转操作，返回需要翻转的边的索引。
 * 如果无法完成转换，返回[-1]。
 * 示例：
 * 输入：n = 3, edges = [[0,1],[1,2]], start = "011", target = "110"
 * 输出：[0,1]
 */
package com.serain.doubleweekgame.game174;

import java.util.ArrayList;
import java.util.List;

public class Q4 {
    /**
     * 计算最小翻转次数
     * @param n 节点数量
     * @param edges 边的数组，每条边包含两个节点
     * @param start 初始状态字符串
     * @param target 目标状态字符串
     * @return 需要翻转的边的索引列表，无法完成返回[-1]
     */
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
    
    /**
     * 深度优先搜索遍历树，计算需要翻转的边
     * @param node 当前节点
     * @param parent 父节点
     * @param adj 邻接表
     * @param needFlip 是否需要翻转的标记数组
     * @param visited 访问标记数组
     * @param result 结果列表
     */
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
    
    /**
     * 边类，存储目标节点和边的索引
     */
    private static class Edge {
        int to;
        int index;
        
        /**
         * 构造边
         * @param to 目标节点
         * @param index 边的索引
         */
        Edge(int to, int index) {
            this.to = to;
            this.index = index;
        }
    }
}
