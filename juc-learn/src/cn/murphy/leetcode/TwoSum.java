package cn.murphy.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {


    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 13;
        System.out.println(Arrays.toString(twoSum1(nums,target)));
        System.out.println(Arrays.toString(twoSum2(nums,target)));
    }

    public static int[] twoSum1(int[] nums,int target ){
        for(int i=0;i<nums.length;i++){
            for(int j = i+1;j<nums.length;j++){
                if(target -nums[i] == nums[j]){
                    return new int[]{i,j};
                }

            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums,int target ){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            Integer tarNum = target - nums[i];
            if(map.containsKey(tarNum)){
                return new int[]{i,map.get(tarNum)};
            }
            map.put(nums[i],i);
        }

        return  null;


    }


}
