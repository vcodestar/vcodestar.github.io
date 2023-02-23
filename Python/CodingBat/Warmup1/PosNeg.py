def pos_neg(a, b, negative):
    if negative is True:
        return a < 0 and b < 0
    else:
        return (a < 0 and b > 0) or (a > 0 and b<0)