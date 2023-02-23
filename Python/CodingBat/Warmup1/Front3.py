def front3(str):
    if len(str) < 3:
        return str+str+str
    else:
        return str[0:3]+str[0:3]+str[0:3]