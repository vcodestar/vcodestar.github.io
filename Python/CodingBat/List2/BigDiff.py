def big_diff(nums):
    min = nums[0]
    max = nums[0]
    for i in range(len(nums)):
        if nums[i] < min:
            min = nums[i]
        elif nums[i] > max:
            max = nums[i]
    return max - min