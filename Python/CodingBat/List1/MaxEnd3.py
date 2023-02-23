def max_end3(nums):
    if nums[0] > nums[-1]:
        cpy = nums[0]
    else:
        cpy = nums[-1]

    for i in range(len(nums)):
        nums[i] = cpy

    return nums