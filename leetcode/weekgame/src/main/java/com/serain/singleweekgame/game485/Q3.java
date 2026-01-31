/**
 * @author Serain
 * @date 2026-01-31
 * @description 拍卖系统实现
 * 实现一个拍卖系统，支持添加出价、更新出价、移除出价和获取最高出价者的功能。
 * 示例：
 * 输入：addBid(1, 1, 100), addBid(2, 1, 200), getHighestBidder(1)
 * 输出：2
 */
package com.serain.singleweekgame.game485;

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Q3 {
}

/**
 * 拍卖系统类，用于处理用户的出价操作
 */
class AuctionSystem {
    private final Map<Integer, Integer> amount = new HashMap<>();
    private final Map<Integer, PriorityQueue<int[]>> itemPQ = new HashMap<>();

    /**
     * 添加出价
     * @param userId 用户ID
     * @param itemId 物品ID
     * @param bidAmount 出价金额
     */
    public void addBid(int userId, int itemId, int bidAmount) {
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
    }

    /**
     * 移除出价
     * @param userId 用户ID
     * @param itemId 物品ID
     */
    public void removeBid(int userId, int itemId) {
        amount.remove(userId << 16 | itemId);
    }

    /**
     * 获取最高出价者
     * @param itemId 物品ID
     * @return 最高出价者的用户ID，不存在返回-1
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
            pq.poll();
        }
        return -1;
    }
}

