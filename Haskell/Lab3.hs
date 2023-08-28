--Chasanis Evangelos cs05058
-----------------------------------------------------------------------------------------

-- ASKHSH 1

-- make a list of words given a string
-- a word consists of consecutive letters only

wordList :: String->[String]
wordList s 
    |null s=[] 
    |[iterator s]==[""] 
    =wordList(nthElem s 0 (length (iterator  s)))
    |otherwise=[iterator s]++wordList(nthElem s 0 (length (iterator s)))

nthElem::String->Int->Int->String
nthElem s index len
    |index<len
    =nthElem (tail s) (index+1) len
    |null s=[]
    |booleanGen(head s)==False
    =nthElem (tail s) (index) (len)
    |index==len
    =s
iterator::String->String
iterator s
    |null s=[]
    |(booleanGen (head s))
    =(cTS(head s))++(iterator(tail s))
    |otherwise
    =[]

booleanGen::Char->Bool
booleanGen c= if (c>='a'&&c<='z')|| (c>='A'&&c<='Z') then True else False

cTS :: Char -> String
cTS c = [c]     

conc::[u]->[u]->[u]
conc (h:t) s=h: conc t s
conc[] s=s

loga :: Float -> (Float -> Float)
loga a = \x -> log x / log a

-----------------------------------------------------------------------------------------
     
-- ASKHSH 2

-- given a list of positions, make the robot visit each position moving only horizontally and vertically and then return to base

trace :: [(Int,Int)]->[(Int,Int)]
trace s=traceCalled s 0

traceCalled :: [(Int,Int)]->Int->[(Int,Int)]
traceCalled s n
    |n==0
    =(traceHelpS1 s 0 0)++(traceCalled s 1)
    |n==1
    =if (null s) then [] else (traceHelp s (head(tupleXY(head s))) (last(tupleXY(head s))))++(traceCalled(tail s) 1)
traceHelpS1::[(Int,Int)]->Int->Int->[(Int,Int)]
traceHelpS1 s xi yi
    -------pos----
    |xi<head(tupleXY(head(s))) && yi<last(tupleXY(head(s)))
    =(xi,yi):traceHelpS1 s (xi+1) (yi+1)
    |xi==head(tupleXY(head(s))) && yi<last(tupleXY(head(s)))
    =(xi,yi):traceHelpS1 s (xi) (yi+1)
    |xi<head(tupleXY(head(s))) && yi==last(tupleXY(head(s)))
    =(xi,yi):traceHelpS1 s (xi+1) (yi)
    |xi==head(tupleXY(head(s))) && yi==last(tupleXY(head(s)))
    =[(xi,yi)]
    -----------neg--------
    |xi>head(tupleXY(head(s))) && yi>last(tupleXY(head(s)))
    =(xi,yi):traceHelpS1 s (xi-1) (yi-1)
    |xi>head(tupleXY(head(s))) && yi==last(tupleXY(head(s)))
    =(xi,yi):traceHelpS1 s (xi-1) (yi)
    |xi==head(tupleXY(head(s))) && yi>last(tupleXY(head(s)))
    =(xi,yi):traceHelpS1 s (xi) (yi-1)
    -----mix-----
    |xi<head(tupleXY(head(s))) && yi>last(tupleXY(head(s)))
    =(xi,yi):traceHelpS1 s (xi+1) (yi-1)
    |xi>head(tupleXY(head(s))) && yi<last(tupleXY(head(s)))
    =(xi,yi):traceHelpS1 s (xi-1) (yi+1)
traceHelp::[(Int,Int)]->Int->Int->[(Int,Int)]
traceHelp s xi yi
    |length s==1
    =(backToZero s xi yi)
    -------pos----
    |xi<head(tupleXY(head(tail s))) && yi<last(tupleXY(head(tail s)))
    =(xi+1,yi+1):traceHelp s (xi+1) (yi+1)
    |xi==head(tupleXY(head(tail s))) && yi<last(tupleXY(head(tail s)))
    =(xi,yi+1):traceHelp s (xi) (yi+1)
    |xi<head(tupleXY(head(tail s))) && yi==last(tupleXY(head(tail s)))
    =(xi+1,yi):traceHelp s (xi+1) (yi)
    |xi==head(tupleXY(head(tail s))) && yi==last(tupleXY(head(tail s)))
    =[]
    -----------neg--------
    |xi>head(tupleXY(head(tail s))) && yi>last(tupleXY(head(tail s)))
    =(xi-1,yi-1):traceHelp s (xi-1) (yi-1)
    |xi>head(tupleXY(head(tail s))) && yi==last(tupleXY(head(tail s)))
    =(xi-1,yi):traceHelp s (xi-1) (yi)
    |xi==head(tupleXY(head(tail s))) && yi>last(tupleXY(head(tail s)))
    =(xi,yi-1):traceHelp s (xi) (yi-1)
    -----mix-----
    |xi<head(tupleXY(head(tail s))) && yi>last(tupleXY(head(tail s)))
    =(xi+1,yi-1):traceHelp s (xi+1) (yi-1)
    |xi>head(tupleXY(head(tail s))) && yi<last(tupleXY(head(tail s)))
    =(xi-1,yi+1):traceHelp s (xi-1) (yi+1)


tupleXY::(Int,Int)->[Int]
tupleXY (x,y) =[x0,y0]
    where x0=x-0
          y0=y-0

backToZero::[(Int,Int)]->Int->Int->[(Int,Int)]
backToZero s xii yii
    |xii>0 && yii>0
    =(xii-1,yii-1):backToZero s (xii-1) (yii-1)
    |xii==0 && yii>0
    =(xii,yii-1):backToZero s (xii) (yii-1)
    |xii>0 && yii==0
    =(xii-1,yii):backToZero s (xii-1) (yii)
    |xii<0 && yii<0
    =(xii+1,yii+1):backToZero s (xii+1) (yii+1)
    |xii==0 && yii<0
    =(xii,yii+1):backToZero s (xii) (yii+1)
    |xii<0 && yii==0
    =(xii+1,yii):backToZero s (xii+1) (yii)
    |xii>0 && yii<0
    =(xii-1,yii+1):backToZero s (xii-1) (yii+1)
    |xii<0 && yii>0
    =(xii+1,yii-1):backToZero s (xii+1) (yii-1)
    |xii==0 && yii==0
    =[]
     
distance::(Int,Int)->(Int,Int)->[Int]
distance (x1 , y1) (x2 , y2) = [x3,y3]
    where
        x3 = x2-x1
        y3 = y2-y1
