def in1to10(n, outside_mode):
    return (1 <= n <= 10 and not outside_mode) or ((n <= 1 or n >= 10) and outside_mode)