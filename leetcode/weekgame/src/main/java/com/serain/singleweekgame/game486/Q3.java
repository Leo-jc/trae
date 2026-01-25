/**
 * @author Serain
 * @date 2026-01-25
 */
package com.serain.singleweekgame.game486;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q3 类：包含特殊节点计算和多叉树最近公共祖先实现
 */
public class Q3 {
    /**
     * 计算特殊节点数量
     * 
     * @param n 节点数量
     * @param edges 边列表
     * @param x 参数x
     * @param y 参数y
     * @param z 参数z
     * @return 特殊节点数量
     */
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        // 构建邻接表
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        // 初始化深度和父节点数组
        int[] dist = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1); // 初始化为 -1，表示未访问
        dfs(0, 0, dist, graph, parent);

        // 构建倍增表
        int log = Integer.numberOfTrailingZeros(Integer.highestOneBit(n)) + 1;
        int[][] up = new int[n][log];
        for (int i = 0; i < n; i++) {
            up[i][0] = parent[i] == -1 ? i : parent[i];
        }
        for (int k = 1; k < log; k++) {
            for (int i = 0; i < n; i++) {
                up[i][k] = up[up[i][k - 1]][k - 1];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int lcaX = lowestCommonAncestor(i, x, dist, up);
            int lcaY = lowestCommonAncestor(i, y, dist, up);
            int lcaZ = lowestCommonAncestor(i, z, dist, up);
            // 计算距离：dist[i] + dist[target] - 2 * dist[lca]
            int dX = dist[i] + dist[x] - 2 * dist[lcaX];
            int dY = dist[i] + dist[y] - 2 * dist[lcaY];
            int dZ = dist[i] + dist[z] - 2 * dist[lcaZ];
            int[] dis = {dX, dY, dZ};
            Arrays.sort(dis);
            // 勾股定理判断
            if (dis[0] * dis[0] + dis[1] * dis[1] == dis[2] * dis[2]) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 深度优先搜索
     * 
     * @param u 当前节点
     * @param level 当前深度
     * @param dist 距离数组
     * @param graph 图结构
     * @param parent 父节点数组
     */
    private void dfs(int u, int level, int[] dist, Map<Integer, List<Integer>> graph, int[] parent) {
        dist[u] = level;
        if (graph.containsKey(u)) {
            for (int v : graph.get(u)) {
                if (dist[v] == 0 && v != 0 && parent[v] == -1) { // 避免回到父节点
                    parent[v] = u;
                    dfs(v, level + 1, dist, graph, parent);
                }
            }
        }
    }

    /**
     * 查找两个节点的最近公共祖先（基于倍增法，O(log n)）
     * 
     * @param u 第一个节点
     * @param v 第二个节点
     * @param depth 节点深度数组
     * @param up 倍增父表，up[u][k] 表示节点 u 的第 2^k 级祖先
     * @return 最近公共祖先节点
     */
    private int lowestCommonAncestor(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        // 将 u 提升到与 v 同一深度
        int diff = depth[u] - depth[v];
        for (int k = 0; diff > 0; k++, diff >>= 1) {
            if ((diff & 1) == 1) {
                u = up[u][k];
            }
        }
        if (u == v) return u;
        // 一起向上倍增找 LCA
        for (int k = up[0].length - 1; k >= 0; k--) {
            if (up[u][k] != up[v][k]) {
                u = up[u][k];
                v = up[v][k];
            }
        }
        return up[u][0];
    }

    /**
     * 主方法：测试specialNodes方法
     */
    public static void main(String[] args) {
        Q3 q3 = new Q3();

        // 测试用例：n = 4, edges = [[0,1],[0,2],[0,3]], x = 1, y = 2, z = 3
        int n = 4;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}};
        int x = 1;
        int y = 2;
        int z = 3;

        int result = q3.specialNodes(n, edges, x, y, z);
        System.out.println("测试结果: " + result);
    }
}