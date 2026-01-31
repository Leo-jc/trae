package com.serain.singleweekgame.game486;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q3 {
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