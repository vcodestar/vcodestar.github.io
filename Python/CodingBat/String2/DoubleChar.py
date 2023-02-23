def double_char(str):
    res = ""
    for i in range(len(str)):
        res += str[i]+str[i]

    return res