def array_front9(nums):
    counter = 0
    if len(nums) < 4:
        for i in range(len(nums)):
            if nums[i] == 9:
                counter += 1
        return counter > 0
    else:
        for i in range(4):
            if nums[i] == 9:
                counter += 1
        return counter > 0
