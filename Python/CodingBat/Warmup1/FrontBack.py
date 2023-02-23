def front_back(str):
    if len(str) < 1:
        return str
    else:
        strl = list(str)
        temp = strl[0]
        strl[0] = strl[-1]
        strl[-1] = temp
        return ''.join(strl)

