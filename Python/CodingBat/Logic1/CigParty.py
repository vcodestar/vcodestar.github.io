def cigar_party(cigars, is_weekend):
    return (is_weekend and cigars >= 40) or (not is_weekend and 40 <= cigars <= 60)