def no_teen_sum(a, b, c):
    return fix_teen(a) + fix_teen(b) + fix_teen(c)


def fix_teen(n):
    if n < 13 or n > 19:
        return n
    elif n == 15 or n == 16:
        return n
    return 0