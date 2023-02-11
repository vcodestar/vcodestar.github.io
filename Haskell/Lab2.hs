--cs05058 Chasanis Evangelos
-----------------------------------------------------------------------------------------

-- ASKHSH 1


search :: Integer->Integer->Integer->Integer
search a k m=searchHelper a k m 0

searchHelper::Integer -> Integer -> Integer->Integer->Integer
searchHelper a k m n
    |k<=2 && a==0
        =1
    |n<=k 
        =searchHelper a k m (n+1)
    |n>k && ((a+n)^k)>=(m^n)
        =searchHelper a k m (n+1)
    |n>k && ((a+n)^k)<(m^n)
        =n



-----------------------------------------------------------------------------------------
     
-- ASKHSH 2

compress :: Integer->Integer
compress n
    |n<9 =n
    |otherwise 
        =compress m
            where m=compressHelper n
    
           
compressHelper::Integer -> Integer 
compressHelper n 
    |n==0
        =1
    |n `mod` 10==0 && n `div` 10 >0
        =compressHelper(n `div` 10)
    |n `mod` 10==n && n `div` 10==0 
        =n
    |n `mod` 10>0 && n `div` 10>=0
        =m*(compressHelper ((n-m)`div`10))
            where m=n`mod`10

--------------------
compress2 :: Integer->Integer
compress2 n=
    if n<9 then n else compress(compressHlpr2 n)
    
compressHlpr2::Integer -> Integer 
compressHlpr2 n 
    |n `mod` 10==0 && n `div` 10 >0
        =compressHlpr2(n `div` 10)
    |n `mod` 10==n && n `div` 10==0 
        =n
    |n `mod` 10>0 && n `div` 10>=0
        =n2*(compressHlpr2 ((n-n2)`div`10))
            where n2=n`mod`10

--------------

compress3 :: Integer->Integer
compress3 n=
    if n<9 then n else compress3(compressHlp3 n)
    
compressHlp3::Integer -> Integer 
compressHlp3 n 
    |n `mod` 10==0 && n `div` 10 >0
        =compressHlp3(n `div` 10)
    |n `mod` 10==n && n `div` 10==0 
        =n
    |n `mod` 10>0 && n `div` 10>=0
        =(n`mod`10)*(compressHlp3 ((n-(n`mod`10))`div`10))