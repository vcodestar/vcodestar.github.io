def close_far(a, b, c):
    return (abs(a - b) <= 1 and abs(a - c) >= 2 and abs(b - c) >= 2) != (abs(a - c) <= 1 and abs(a - b) >= 2 and abs(c - b) >= 2)