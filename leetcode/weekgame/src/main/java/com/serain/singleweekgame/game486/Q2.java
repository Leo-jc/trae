/**
 * @author Serain
 * @date  
 */
package com.serain.singleweekgame.game486;

public class Q2 {
    public int[] rotateElements(int[] nums, int k) {
        int[] temp = new int[nums.length];
        int[] pos = new int[nums.length];
        int index = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>=0){
                temp[index] = nums[i];
                pos[index++] = i;
            }
        }
        int[] newTemp = new int[index];
        for(int i=0;i<index;i++){
            newTemp[i] = temp[(i+k)%index];
        }
        for(int i=0;i<index;i++){
            nums[pos[i]] = newTemp[i];
        }
        return nums;
    }
}
