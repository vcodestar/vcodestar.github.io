def array123(nums):
  counter = 0
  for i in range(len(nums) - 2):
    if nums[i] == 1 and nums[i+1] == 2 and nums[i + 2] == 3:
      counter += 1
  return counter > 0
