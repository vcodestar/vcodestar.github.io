-----------------------------------------------------------------------------------------
--cs05058 Chasanis Evangelos
-- ASKHSH 1


amount :: Int->Float->Float

amount n p
	| n<0
		=0 
	| p<0 
		=0
	| n<5 && fromIntegral(n)*p<=100 
		=fromIntegral(n)*p
	| n==5 && fromIntegral(n-1)*p<=100 
		=fromIntegral(4)*p
	| 9>n && n>5 && fromIntegral(n)*p<=100 
		=fromIntegral(n-1)*p
	| n==9 && fromIntegral(n)*0<=100
		=fromIntegral(7)*p
	| n<5 &&fromIntegral(n)*p>100
		=fromIntegral(n)*p-fromIntegral(n)*p*0.1
	| n==5 &&fromIntegral(n-1)*p>100
		=fromIntegral(n-1)*p-fromIntegral(n-1)*p*0.1
	| 9>n && n>5 && fromIntegral(n)*p>100 
		=fromIntegral(n-1)*p-fromIntegral(n-1)*p*0.1
	| n==9 && fromIntegral(n)*p>100
		=fromIntegral(7)*fromIntegral(7)*p*0.1
	| n>=12 
		=if fromIntegral(n-triples+1)*p>100 then fromIntegral(n-triples+1)*p-fromIntegral(n-triples+1)*p*0.1 else fromIntegral(n-triples+1)*p
			where triples=fromIntegral(fromIntegral(n) `div` fromIntegral(3))
	
		
	



-----------------------------------------------------------------------------------------
     
-- ASKHSH 2

cost :: (Int,Int,Int)->(Int,Int,Int)->Float


cost (h1,m1,s1) (h2,m2,s2)
	| h1 == h2 && m1==m2 && s1==s2
		=0.0

	| h1==h2 && m2<=m1+3 && s1>=s2
		=0.58
	| h1==h2 && m2<m1+3 && s1<s2
		=0.58

	| h1==h2 && m2<=m1+3 && s1<s2
		=0.58+fromIntegral(s2-s1)*0.003
	| h1==h2 && m2<m1+3 
		=0.58+fromIntegral(s2-s1)*0.003+60*0.003*fromIntegral(m2-m1-3)

	| (h1+1==h2 || h1==23 && h2==0) && m1==57 && (m2==57 || m2==58 || m2==59 || (m2==0 && s1>=s2))
		=0.58
	| (h1+1==h2 || h1==23 && h2==0) && m1==57 && (m2==57 || m2==58 || m2==59 || (m2==0 && s2-s1>0))
		=0.58+fromIntegral(s2-s1)*0.003
	| (h1+1==h2 || h1==23 && h2==0) && m1==57 &&  m2>0
		=if s2>s1 then 0.58+fromIntegral(m2-m1+57)*60*0.003+fromIntegral(s2-s1)*0.003 else  0.58+fromIntegral(m2-m1+56)*60*0.003+fromIntegral(s2-s1+60)*0.003 

	| (h1+1==h2 || h1==23 && h2==0) && m1==58 && ( m2==58 || m2==59 || m2==0 || (m2==1 && s1>=s2))
		=0.58
	| (h1+1==h2 || h1==23 && h2==0) && m1==58 && (m2==58 || m2==59 || m2==0 || (m2==1 && s2-s1>0))
		=0.58
	| (h1+1==h2 || h1==23 && h2==0) && m1==58 &&  m2>0
		=if s2>s1 then 0.58+fromIntegral(m2-m1+57)*60*0.003+fromIntegral(s2-s1)*0.003 else  0.58+fromIntegral(m2-m1+56)*60*0.003+fromIntegral(s2-s1+60)*0.003
	

	| (h1+1==h2 || h1==23 && h2==0) && m1==59 && ( m2==59 || m2==0 || m2==1 || (m2==2 && s1>=s2))
		=0.58
	| (h1+1==h2 || h1==23 && h2==0) && m1==59 && ( m2==59 || m2==0 || m2==1 || (m2==2 && s2-s1>0))
		=0.58
	| (h1+1==h2 || h1==23 && h2==0) && m1==59 &&  m2>0
		=if s2>s1 then 0.58+fromIntegral(m2-m1+57)*60*0.003+fromIntegral(s2-s1)*0.003 else  0.58+fromIntegral(m2-m1+56)*60*0.003+fromIntegral(s2-s1+60)*0.003


	|  h2-h1<0 && m2-m1<0 && s2-s1<0 
		=0.58+(fromIntegral(h2-h1)+23)*60*60*0.003+(fromIntegral(m2-m1)+56)*60*0.003+(fromIntegral(s2-s1)+60)*0.003 
		---m1>m2 gia h1>h2
	|  h2-h1<0 && m2-m1<0 && s2-s1==0 
		=0.58+(fromIntegral(h2-h1)+23)*60*60*0.003+(fromIntegral(m2-m1)+57)*60*0.003
	|  h2-h1<0 && m2-m1<0 && s2-s1>0 
		=0.58+(fromIntegral(h2-h1)+23)*60*60*0.003+(fromIntegral(m2-m1)+57)*60*0.003+(fromIntegral(s2-s1))*0.003 
		---m2>m1 h1>h2
	| h1>h2 && m2>m1 && s2-s1<0
		=0.58+(fromIntegral(h2-h1)+23)*60*60*0.003+(fromIntegral(m2-m1)-4)*60*0.003+(fromIntegral(s2-s1)+60)*0.003
	| h1>h2 && m2>m1 && s2==s1
		=0.58+(fromIntegral(h2-h1)+23)*60*60*0.003+(fromIntegral(m2-m1)-3)*60*0.003
	| h1>h2 && m2>m1 && s2>s1
		=0.58+(fromIntegral(h2-h1)+23)*60*60*0.003+(fromIntegral(m2-m1)-3)*60*0.003+(fromIntegral(s2-s1))*0.003



	| h2>h1 && m2>m1 && s2<s1
		=0.58+(fromIntegral(h2-h1))*60*60*0.003+(fromIntegral(m2-m1)-4)*60*0.003+(fromIntegral(s2-s1)+60)*0.003 
	| h2>h1 && m2>m1 && s2==s1
		=0.58+(fromIntegral(h2-h1))*60*60*0.003+(fromIntegral(m2-m1)-3)
	| h2>h1 && m2>m1 && s2>s1
		=0.58+(fromIntegral(h2-h1))*60*60*0.003+(fromIntegral(m2-m1)-3)+fromIntegral(s2-s1)*0.003
	| h2>h1 && m1>m2 && s2<s1
		=0.58+(fromIntegral(h2-h1))*60*60*0.003+(fromIntegral(m2-m1)+56)*60*0.003+(fromIntegral(s2-s1)+60)*0.003
	| h2>h1 && m1>m2 && s2==s1
		=0.58+(fromIntegral(h2-h1))*60*60*0.003+(fromIntegral(m2-m1)+57)*60*0.003
	| h2>h1 && m1>m2 && s2>s1
		=0.58+(fromIntegral(h2-h1))*60*60*0.003+(fromIntegral(m2-m1)+57)*60*0.003+(fromIntegral(s2-s1))*0.003

	
	----------------------------------------------------------------

--cs05058 Chasanis Evangelos
-----------------------------------------------------------------------------------------

-- ASKHSH 1


search :: Integer->Integer->Integer->Integer

search a k m = -2022                                         




-----------------------------------------------------------------------------------------
     
-- ASKHSH 3

compress :: Integer->Integer
compress n
    if n<=9
    |n `mod` 10==n && n `div` 10==0 
        =n
    |n `mod` 10==0 && n `div` 10 >0
        =n `div` 10
    |n `mod` 10>0 && n `div` 10>0
    =m*(compress ((n-m)`div`10))
        where m=n`mod`10    

|n `mod` 10==n && n `div` 10==0 
        =n
    |n `mod` 10==0 && n `div` 10 >0
        =n `div` 10
    |n `mod` 10>0 && n `div` 10>0
        =m*(compress ((n-m)`div`10))
            where m=n`mod`10   
compress :: Integer->Integer
compress n=
    if (n>9)
        then if n `mod` 10==0 && n `div` 10 >0
            then n`div`10
        else  (n `mod` 10)*(compress ((n-n`mod`10)`div`10))
    else  n