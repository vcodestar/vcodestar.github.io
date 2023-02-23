def sum67(nums):
  result = 0
  switch = True
  for val in nums:
    if val == 6:
      switch = False
    if switch:
      result +=val
    if val == 7:
      switch = True
  return result