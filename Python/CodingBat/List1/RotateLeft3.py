def rotate_left3(nums):
    elm0 = nums[0]
    for i in range(len(nums) - 1):
        nums[i] = nums[i + 1]
    nums[len(nums) - 1] = elm0
    return nums