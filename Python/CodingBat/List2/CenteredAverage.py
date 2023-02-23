def centered_average(nums):

    min = nums[0]
    max = nums[0]

    sum = 0

    for i in range(len(nums)):
        if nums[i] < min:
            min = nums[i]
        elif nums[i] > max:
            max = nums[i]
        sum += nums[i]

    sum = (sum - min - max) / (len(nums) - 2)

    return sum 


