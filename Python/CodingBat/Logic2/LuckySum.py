def lucky_sum(a, b, c):
    if a != 13 and b != 13 and c != 13:
        return a + b + c
    elif a == 13:
        return 0
    elif b == 13:
        return a
    else:
        return a + b