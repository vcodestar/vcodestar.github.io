def reverse3(nums):
    for i in range(len(nums) - 1):
        tmp = nums[i]
        nums[i] = nums[len(nums) - i - 1]
        nums[len(nums) - i - 1] = tmp
    return nums

