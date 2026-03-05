package main.java.com.serain.exercise;

public class E31 {
    /**
     * 找出整数数组的下一个字典序排列。
     * 该算法通过以下步骤实现：
     * 1. 从右向左找到第一个小于右侧相邻数字的位置 i
     * 2. 如果找到位置 i，则从右向左找到 nums[i] 右边最小的大于 nums[i] 的数字位置 j，并交换两者
     * 3. 反转位置 i+1 到末尾的子数组，使其变为升序
     * 
     * @param nums 要处理的整数数组，将在原数组上进行修改
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int n = nums.length;

        // 第一步：从右向左找到第一个小于右侧相邻数字的数 nums[i]
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 如果找到了，进入第二步；否则跳过第二步，反转整个数组
        if (i >= 0) {
            // 第二步：从右向左找到 nums[i] 右边最小的大于 nums[i] 的数 nums[j]
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // 交换 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // 第三步：反转 [i+1, n-1]（如果上面跳过第二步，此时 i = -1）
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
}
