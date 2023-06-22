//https://leetcode.com/problems/two-sum/

// run it on leetcode or create your own main with an array of nums and a target

class Solution {
    public int[] twoSum(int[] nums, int target) 
    {
        int ret[] = {0,0};

        for(int i = 0; i < nums.length - 1; i++)
        {
            for(int j = i + 1; j < nums.length; j++)
            {
                if(nums[i] + nums[j] == target)
                {
                    //System.out.println(nums[i] + nums[j]);
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        }
        return null;
    }
}
