--Chasanis Evangelos cs05058
-----------------------------------------------------------------------------------------

-- ASKHSH 1

-- given a list of integers s =[x1, x2.... xk] return the sum = x1 * (x2 + x3 ... + xk) + 
-- (x1 + x2) * (x3 + x4 .... + xk) + .... + (x1 + x2 + ... + xk-2) * (xk-1 + xk) +
-- (x1 + x2 + ...+ xk-1) * xk

xsum :: [Integer]->Integer
xsum s
    |length s<2=0
    |otherwise=call s 1

sumIntList :: [Integer] ->Integer->Integer->Integer
sumIntList (h:t) n k
    |n<k
    =h + sumIntList t (n+1) k
    |n==k
    =h
    |null (h:t)= 0

find::[Integer]->Integer->Integer->[Integer]
find (h:t) index stop
    |index<stop
    =find t (index+1) stop
    |index==stop
    =(h:t)
    | null (h:t)=[0]

call::[Integer]->Integer->Integer
call s index 
    |null s=0
    |index==(toInteger(length s))=0
    |otherwise
    =(calc s index )+(call s (index+1) )

calc::[Integer]->Integer->Integer
calc (h:t) k
    |k>=(toInteger(length(h:t)))=0
    |otherwise= (par1 (h:t) k)*(par2 (h:t) (k) )
par1::[Integer]->Integer->Integer
par1 (h:t) k=sumIntList (h:t) 0 (k-1)

par2::[Integer]->Integer->Integer
par2 (h:t) n=sumIntList ((find (h:t) 0 n)) 0 (toInteger(length (find (h:t) 0 n))-1) 


-----------------------------------------------------------------------------------------
     
-- ASKHSH 2

-- given two lists, return a list after the application of each function of the first list on every element of the second list
-- the list must be in an ascending order
-- e.g. apply [\x->mod x 10, \x->rem x 10] [-100..100] should return : [-9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9]

apply :: Ord u => [v->u]->[v]->[u]
apply p s 
    |null p=[]
    |null s=[]  
    |otherwise= quicksort(perp p s)

perp::Ord u=>[v->u]->[v]->[u]
perp p s
    |null p=[]
    |null s=[]  
    |otherwise= (mapList (head p) s)++(perp (tail p) s)

mapList :: (u -> v) -> [u] -> [v]
mapList f [] = []
mapList f (h:t) = f h : (mapList f t)

quicksort :: Ord a => [a] -> [a]
quicksort []     = []
quicksort (h:t) = (quicksort lesser) ++ [h] ++ (quicksort greater)
    where
        lesser= [t| t<- (h:t), t<h]
        greater=[t|t<-(h:t), t>h]
