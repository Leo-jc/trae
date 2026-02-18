/**
 * @author Serain
 * @date 2026-01-31
 * @description 计算特殊节点数量
 * 给定一个无向树，计算满足到三个给定节点的距离构成勾股数的节点数量。
 * 示例：
 * 输入：n = 5, edges = [[0,1],[0,2],[1,3],[1,4]], x = 3, y = 4, z = 2
 * 输出：2
 */
package com.serain.singleweekgame.game486;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q3 {
    /**
     * 计算特殊节点数量
     * @param n 节点数量
     * @param edges 边的数组
     * @param x 第一个目标节点
     * @param y 第二个目标节点
     * @param z 第三个目标节点
     * @return 特殊节点的数量
     */
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int[] dist = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        dfs(0, 0, dist, graph, parent);

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
            int dX = dist[i] + dist[x] - 2 * dist[lcaX];
            int dY = dist[i] + dist[y] - 2 * dist[lcaY];
            int dZ = dist[i] + dist[z] - 2 * dist[lcaZ];
            int[] dis = {dX, dY, dZ};
            Arrays.sort(dis);
            if (dis[0] * dis[0] + dis[1] * dis[1] == dis[2] * dis[2]) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 深度优先搜索遍历树，计算节点深度和父节点
     * @param u 当前节点
     * @param level 当前深度
     * @param dist 深度数组
     * @param graph 邻接表
     * @param parent 父节点数组
     */
    private void dfs(int u, int level, int[] dist, Map<Integer, List<Integer>> graph, int[] parent) {
        dist[u] = level;
        if (graph.containsKey(u)) {
            for (int v : graph.get(u)) {
                if (dist[v] == 0 && v != 0 && parent[v] == -1) {
                    parent[v] = u;
                    dfs(v, level + 1, dist, graph, parent);
                }
            }
        }
    }

    /**
     * 计算两个节点的最近公共祖先
     * @param u 第一个节点
     * @param v 第二个节点
     * @param depth 深度数组
     * @param up 倍增表
     * @return 最近公共祖先
     */
    private int lowestCommonAncestor(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        int diff = depth[u] - depth[v];
        for (int k = 0; diff > 0; k++, diff >>= 1) {
            if ((diff & 1) == 1) {
                u = up[u][k];
            }
        }
        if (u == v) return u;
        for (int k = up[0].length - 1; k >= 0; k--) {
            if (up[u][k] != up[v][k]) {
                u = up[u][k];
                v = up[v][k];
            }
        }
        return up[u][0];
    }
}