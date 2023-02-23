def make_chocolate(small, big, goal):
    if goal - 5 * big > 0:
        remainder = goal - 5 * big
    else:
        remainder = goal % 5

    if small >= remainder:
        return remainder

    return -1


