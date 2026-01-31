/**
 * E3507 类：包含两个解决方案，用于解决最小配对移除问题
 * <p>
 * 问题描述：给定一个整数数组，每次操作可以移除一对相邻元素，并用它们的和替换
 * 要求找到最少的操作次数，使得最终数组是非递减的
 *
 * @author Serain
 * @date 2026-01-31
 */
package com.serain.exercise;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * E3507 主类
 * <p>
 * 空类，作为两个解决方案的容器
 */
public class E3507 {

}

/**
 * Solution 类：使用 TreeSet 实现的解决方案
 * <p>
 * 通过维护一个有序集合来管理相邻元素对的和，并使用另一个集合跟踪未被删除的元素
 */
class Solution {
    /**
     * Pair 类：存储相邻元素的和及其左边元素的下标
     */
    private static class Pair {
        long s;
        int i;

        Pair(long s, int i) {
            this.s = s;
            this.i = i;
        }
    }

    /**
     * 计算最小配对移除次数，使数组变为非递减
     *
     * @param nums 输入整数数组
     * @return 最小操作次数
     */
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        // 存储相邻元素对的和及其左边元素下标，按和升序排列
        TreeSet<Pair> ts = new TreeSet<>((a, b) -> a.s != b.s ? Long.compare(a.s, b.s) : Integer.compare(a.i, b.i));
        // 递减相邻对的数量
        int decNum = 0;
        
        // 初始化：计算所有相邻对的和，并统计递减对数量
        for (int i = 0; i < n-1; i++) {
            if(nums[i] > nums[i+1]){
                decNum++;
            }
            ts.add(new Pair(nums[i]+nums[i+1], i));
        }
        
        // 跟踪未被删除的元素下标
        TreeSet<Integer> used = new TreeSet<>();
        for(int i=0;i<n;i++){
            used.add(i);
        }
        
        // 将原数组转换为长整型，避免溢出
        long[] numsLong = new long[n];
        for(int i=0;i<n;i++){
            numsLong[i] = nums[i];
        }
        
        // 操作次数
        int ans = 0;
        
        // 当仍有递减对时，继续操作
        while(decNum>0){
            ans++;
            // 取出和最小的相邻对
            Pair p = ts.pollFirst();
            int i = p.i;
            long s = p.s;
            
            // 获取i的下一个未被删除的元素下标
            Integer next = used.higher(i);
            // 如果存在且原对是递减的，递减对数量减1
            if(next!=null && numsLong[i]>numsLong[next]){
                decNum--;
            }

            // 获取i的前一个未被删除的元素下标
            Integer pre = used.lower(i);

            if(pre!=null){
                // 如果前一个元素大于当前元素，递减对数量减1
                if(numsLong[pre]>numsLong[i]){
                    decNum--;
                }
                // 如果前一个元素大于新的和，递减对数量加1
                if (numsLong[pre]>s) {
                    decNum++;
                }
                // 更新前一个元素与当前元素的对
                ts.remove(new Pair(numsLong[pre]+numsLong[i], pre));
                ts.add(new Pair(numsLong[pre]+s, pre));
            }

            // 获取next的下一个未被删除的元素下标
            Integer next2 = used.higher(next);
            if(next2!=null){
                // 如果next大于next2，递减对数量减1
                if(numsLong[next]>numsLong[next2]){
                    decNum--;
                }
                // 如果新的和大于next2，递减对数量加1
                if (s>numsLong[next2]) {
                    decNum++;
                }
                // 更新next与next2的对
                ts.remove(new Pair(numsLong[next]+numsLong[next2], next));
                ts.add(new Pair(s+numsLong[next2], i));
            }
            
            // 更新当前元素为新的和
            numsLong[i] = s;            
            // 标记next为已删除
            used.remove(next);
        }
        
        return ans;
    }   
}

/**
 * Solution2 类：使用优先队列实现的解决方案
 * <p>
 * 通过维护一个最小堆来管理相邻元素对的和，并使用双向链表结构跟踪未被删除的元素
 */
class Solution2 {
    /**
     * Pair 类：存储相邻元素的和及其左边元素的下标
     */
    private static class Pair {
        long s;
        int i;

        Pair(long s, int i) {
            this.s = s;
            this.i = i;
        }
    }

    /**
     * 计算最小配对移除次数，使数组变为非递减
     *
     * @param nums 输入整数数组
     * @return 最小操作次数
     */
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        // 优先队列：存储相邻元素的和及其左边元素的下标，按和升序排列
        PriorityQueue<Pair> h = new PriorityQueue<>((a, b) -> a.s != b.s ? Long.compare(a.s, b.s) : a.i - b.i);
        // 递减相邻对的数量
        int dec = 0;
        
        // 初始化：计算所有相邻对的和，并统计递减对数量
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            int y = nums[i + 1];
            if (x > y) {
                dec++;
            }
            h.offer(new Pair(x + y, i));
        }

        // 模拟双向链表：记录每个元素的左右邻居
        // 每个下标的左右最近的未删除下标
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            left[i] = i - 1;
            right[i] = i + 1;
        }

        // 将原数组转换为长整型，避免溢出
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nums[i];
        }

        // 操作次数
        int ans = 0;
        
        // 当仍有递减对时，继续操作
        while (dec > 0) {
            ans++;

            // 懒删除：移除堆顶的无效数据（已被删除的元素对）
            while (right[h.peek().i] >= n || h.peek().s != a[h.peek().i] + a[right[h.peek().i]]) {
                h.poll();
            }

            // 取出和最小的相邻对
            Pair p = h.poll();
            long s = p.s;
            int i = p.i;

            // 获取i的下一个元素下标
            int nxt = right[i];
            // 如果原对是递减的，递减对数量减1
            if (a[i] > a[nxt]) { // 旧数据
                dec--;
            }

            // 获取i的前一个元素下标
            int pre = left[i];
            if (pre >= 0) {
                // 如果前一个元素大于当前元素，递减对数量减1
                if (a[pre] > a[i]) { // 旧数据
                    dec--;
                }
                // 如果前一个元素大于新的和，递减对数量加1
                if (a[pre] > s) { // 新数据
                    dec++;
                }
                // 添加前一个元素与新和的对
                h.offer(new Pair(a[pre] + s, pre));
            }

            // 获取nxt的下一个元素下标
            int nxt2 = right[nxt];
            if (nxt2 < n) {
                // 如果nxt大于nxt2，递减对数量减1
                if (a[nxt] > a[nxt2]) { // 旧数据
                    dec--;
                }
                // 如果新的和大于nxt2，递减对数量加1
                if (s > a[nxt2]) { // 新数据（当前元素，下下一个数）
                    dec++;
                }
                // 添加新和与nxt2的对
                h.add(new Pair(s + a[nxt2], i));
            }

            // 更新当前元素为新的和
            a[i] = s; // 把 a[nxt] 加到 a[i] 中
            
            // 模拟双向链表的删除操作：删除nxt
            int l = left[nxt];
            int r = right[nxt];
            right[l] = r;
            left[r] = l;
            right[nxt] = n; // 标记nxt为已删除
        }
        
        return ans;
    }
}

