/**
 * @author Serain
 * @date 2026-01-27
 * @description 计算从节点0到节点n-1的最小成本路径
 *              问题特点：边是双向的，但反向边的成本是正向边的两倍
 */
package com.serain.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * E3650 类用于解决计算最小成本路径的问题
 * 使用Dijkstra算法优化路径搜索效率，避免超时问题
 */
public class E3650 {
    
    /**
     * 计算从节点0到节点n-1的最小成本路径
     * 
     * @param n 节点数量，节点编号从0到n-1
     * @param edges 边的数组，每条边包含三个元素：[起点, 终点, 正向成本]
     * @return 从节点0到节点n-1的最小成本，如果不可达则返回-1
     */
    public int minCost(int n, int[][] edges) {
        // 构建邻接表，存储图的结构
        // 键：节点编号，值：该节点的所有邻接节点及其对应的成本
        Map<Integer, List<int[]>> map = new HashMap<>();
        
        // 遍历所有边，构建双向邻接表
        for(int[] edge: edges){
            int a = edge[0];      // 起点
            int b = edge[1];      // 终点
            int cost = edge[2];   // 正向成本
            
            // 为起点和终点添加邻接表项（如果不存在）
            map.putIfAbsent(a, new ArrayList<>());
            map.putIfAbsent(b, new ArrayList<>());
            
            // 添加正向边：从a到b，成本为cost
            map.get(a).add(new int[]{b, cost});
            // 添加反向边：从b到a，成本为2*cost
            map.get(b).add(new int[]{a, 2*cost});
        }
        
        // 初始化距离数组，存储从节点0到每个节点的最小成本
        int[] dist = new int[n];
        // 初始化为最大值，表示尚未可达
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 节点0到自身的成本为0
        dist[0] = 0;
        
        // 使用优先队列实现Dijkstra算法
        // 队列中的元素为：[节点编号, 从起点到该节点的当前成本]
        // 优先队列按成本从小到大排序
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 将起点0加入队列，初始成本为0
        pq.offer(new int[]{0, 0});
        
        // 主循环：处理优先队列中的节点
        while (!pq.isEmpty()) {
            // 取出当前成本最小的节点
            int[] current = pq.poll();
            int node = current[0];         // 当前节点编号
            int currentCost = current[1];  // 从起点到当前节点的成本
            
            // 剪枝优化：如果当前路径的成本已经大于已知的最小成本，跳过
            if (currentCost > dist[node]) {
                continue;
            }
            
            // 处理当前节点的所有邻居
            if (map.containsKey(node)) {
                // 遍历当前节点的所有邻接边
                for (int[] next : map.get(node)) {
                    int nextNode = next[0];  // 邻接节点编号
                    int nextCost = next[1];  // 到邻接节点的边成本
                    
                    // 松弛操作：如果通过当前节点到邻接节点的路径更短，更新距离
                    if (dist[nextNode] > dist[node] + nextCost) {
                        dist[nextNode] = dist[node] + nextCost;
                        // 将更新后的邻接节点加入优先队列
                        pq.offer(new int[]{nextNode, dist[nextNode]});
                    }
                }
            }
        }
        
        // 返回结果：如果节点n-1不可达，返回-1；否则返回最小成本
        return dist[n-1] == Integer.MAX_VALUE ? -1 : dist[n-1];
    }
}
