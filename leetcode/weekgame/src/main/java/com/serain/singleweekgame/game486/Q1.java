/**
 * @author Serain
 * @date  
 */
package com.serain.singleweekgame.game486;

public class Q1 {
    public int minimumPrefixLength(int[] nums) {
        int ans = nums.length-1;
        while(ans>0){
            if(nums[ans]>nums[ans-1]){
                ans--;
            }else{
                break;
            }
        }
        return ans;
    }
}
