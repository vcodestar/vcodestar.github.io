def string_bits(str):
    strl=[]
    for i in range(0,len(str)):
        if(i % 2 == 0):
            strl.append(str[i])

    return ''.join(strl)