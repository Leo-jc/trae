package com.serain.singleweekgame.game485;

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Q3 {
}

class AuctionSystem {
    private final Map<Integer, Integer> amount = new HashMap<>();
    private final Map<Integer, PriorityQueue<int[]>> itemPQ = new HashMap<>();

    public void addBid(int userId, int itemId, int bidAmount) {
        amount.put(userId << 16 | itemId, bidAmount);
        itemPQ.computeIfAbsent(itemId, k -> new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]))
                .offer(new int[]{bidAmount, userId});
    }

    public void updateBid(int userId, int itemId, int newAmount) {
        addBid(userId, itemId, newAmount);
    }

    public void removeBid(int userId, int itemId) {
        amount.remove(userId << 16 | itemId);
    }

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

