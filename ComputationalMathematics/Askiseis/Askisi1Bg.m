#Chasanis Evangelos cs05058
#Mouselimi Georgia Amalia cs05074
#Sakellariou Ioanna cs05125

clear;
clc;

m1 = 1.5;
m2 = 1;
b1 = 5;
d1des = 1.2125;
Kp1 =  6.625;


h = 0.001;
t = 0:h:30;
t = 0.001;

x1(1) = 0.2565; #d1,0 afou x1 = d1
x2(1) = 0; #d1'(0) afou x2 = d1'

for n=2:30001
  x1(n) = x1(n-1) +  h * x2(n-1);
  x2d(n) = (-Kp1 / (m1 + m2)) * x1(n-1) - (b1 / (m1 + m2)) * x2(n-1) + (Kp1 / (m1 + m2)) *d1des;
  x2(n) = x2(n-1) + h * x2d(n);
end;

disp(x1(1))
disp(x1(3000))
disp(x1(4000))
disp(x1(8000))
disp(x1(20000))
disp(x1(30001))

t = 0:0.001:30;

subplot(211);
plot(t, x1, 'k');
grid;
title("d1(t) Graph");
xlabel("Time(sec)");
ylabel("d1(t)");

subplot(212);
plot(t, x2, 'b');
grid;
title("d1'(t) Graph");
xlabel("Time(sec)");
ylabel("d1'(t)");

saveas(1,"1BgGraph.png");


