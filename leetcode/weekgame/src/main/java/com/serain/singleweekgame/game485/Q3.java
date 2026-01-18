/**
 * @author serain
 * @description 拍卖系统实现，用于管理用户对物品的出价
 */
package com.serain.singleweekgame.game485;

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 题目3的主类
 */
public class Q3 {
    
}

/**
 * 拍卖系统类，支持添加、更新、删除出价，以及获取最高出价者
 */
class AuctionSystem {
    /**
     * 存储用户对物品的出价金额，键为userId和itemId的压缩值，值为出价金额
     */
    private final Map<Integer, Integer> amount = new HashMap<>(); // (userId, itemId) -> bidAmount
    
    /**
     * 存储每个物品的出价队列，按出价金额降序、用户ID降序排列
     */
    private final Map<Integer, PriorityQueue<int[]>> itemPQ = new HashMap<>(); // itemId -> [(bidAmount, userId)]

    /**
     * 添加出价
     * @param userId 用户ID
     * @param itemId 物品ID
     * @param bidAmount 出价金额
     */
    public void addBid(int userId, int itemId, int bidAmount) {
        // 把 (userId, itemId) 压缩到一个 32 位 int 中
        amount.put(userId << 16 | itemId, bidAmount);
        itemPQ.computeIfAbsent(itemId, k -> new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]))
                .offer(new int[]{bidAmount, userId});
    }

    /**
     * 更新出价
     * @param userId 用户ID
     * @param itemId 物品ID
     * @param newAmount 新的出价金额
     */
    public void updateBid(int userId, int itemId, int newAmount) {
        addBid(userId, itemId, newAmount);
        // 堆中重复的元素在 getHighestBidder 中删除（懒更新）
    }

    /**
     * 删除出价
     * @param userId 用户ID
     * @param itemId 物品ID
     */
    public void removeBid(int userId, int itemId) {
        amount.remove(userId << 16 | itemId);
        // 堆中元素在 getHighestBidder 中删除（懒删除）
    }

    /**
     * 获取物品的最高出价者
     * @param itemId 物品ID
     * @return 最高出价者的用户ID，若无出价则返回-1
     */
    public int getHighestBidder(int itemId) {
        PriorityQueue<int[]> pq = itemPQ.get(itemId);
        if (pq == null) {
            return -1;
        }

        while (!pq.isEmpty()) {
            int[] top = pq.peek();
            int bidAmount = top[0];
            int userId = top[1];
            Integer realAmount = amount.get(userId << 16 | itemId);
            if (realAmount != null && realAmount == bidAmount) {
                return userId;
            }
            // 货不对板，堆顶出价与实际不符
            pq.poll();
        }
        return -1;
    }
}

