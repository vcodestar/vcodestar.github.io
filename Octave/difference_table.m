v=input("Enter vector: ","s")
f=cellfun("str2num",strsplit(v," ")) ;
f2=f;
til=0;
while til<1
len=length(f2);
D=[];
for i=1:(length(f2)-1)
  D(i)=f2(i+1)-f2(i);
  if i==(len-1)
    disp(D)
  endif
end
f2=D;
if length(f2)==2
  til=1;
  D=f2(2)-f2(1);
  disp(D)
  endif
endwhile
