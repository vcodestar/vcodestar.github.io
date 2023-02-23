def last2(str):
    l2 = str[-2::]
    count =0
    for i in range(len(str) - 2):
        if str[i] == l2[0] and str[i + 1] == l2[1]:
            count += 1
    return count