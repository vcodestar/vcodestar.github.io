def string_match(a, b):

    if len(a) < len(b):
        length = len(a)
    else:
        length = len(b)

    counter = 0

    for i in range(length-1):
        if a[i] == b[i] and a[i + 1] == b[i + 1]:
            counter += 1
    return counter
