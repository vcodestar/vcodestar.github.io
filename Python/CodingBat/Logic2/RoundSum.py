def round_sum(a, b, c):
    return round10(a) + round10(b) + round10(c)

def round10(num):
    diff = num % 10
    if diff < 5:
        return num - diff
    else:
        return num + (10 - diff) #e.g. 56 % 10 = 6, 60 = 56 + (10 - "6")