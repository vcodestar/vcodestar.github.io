def squirrel_play(temp, is_summer):
    return (is_summer and  60 <= temp <= 100) or (not is_summer and 60 <= temp <= 90)