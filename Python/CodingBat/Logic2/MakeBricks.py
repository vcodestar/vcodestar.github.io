def make_bricks(small, big, goal):
  if goal > small + big * 5:
    return False
  else:
    return goal % 5 <= small
