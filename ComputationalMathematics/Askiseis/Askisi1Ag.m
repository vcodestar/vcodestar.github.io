#Chasanis Evangelos cs05058
#Mouselimi Georgia Amalia cs05074
#Sakellariou Ioanna cs05125

clear;
clc;
m1=1.5;
m2=1;
b1=5;
b2=7;
g=9.81;
f1=30+(5125/500);
f2=10;
d1=0+(5125/20000);
d2=0;

h = 0.001;
t=0:h:30;

t=0.001; #t = 0.000 tin y(1) ara sto loop gia y(2) to t = t + h;


d1d(1) = ((f1 - (g * (m1 + m2))) / b1) * (1 - exp( (-b1 / (m1 + m2 )) * 0));
d1dd(1) = ((f1 - (g * (m1 + m2))) / b1) * (b1 / (m1 + m2)) * exp((-b1 / (m1 + m2)) * 0);
d2d(1)= (f2 / b2) * (1 - exp((-b2 / m2) * 0));
d2dd(1) = (f2 / m2) * exp((-b2 / m2) * 0);
y(1) = d1;
y2(1) = d2;
for m=2:30001
       d1d(m) = ((f1 - (g * (m1 + m2))) / b1) * (1 - exp( (-b1 / (m1 + m2 )) * t));
       d1dd(m) = ((f1 - (g * (m1 + m2))) / b1) * (b1 / (m1 + m2)) * exp((-b1 / (m1 + m2)) * t);
       d2d(m) = (f2 / b2) * (1 - exp((-b2 / m2) * t));
       d2dd(m) = (f2 / m2) * exp((-b2 / m2) * t);
       y(m) = y(m-1) + h * d1d(m) + ((h * h) / 2) * d1dd(m);
       y2(m) = y2(m - 1) + h * d2d(m) + ((h * h) / 2) * d2dd(m);
       t = t + 0.001;
  end

 t = 0:0.001:30;

 subplot(211);
 plot(t,  y, 'm');
 title("d1(t)");
 grid;
 xlabel("Time(sec)");
 ylabel("d1(t)");

 subplot(212);
 plot(t, d1d, 'b');
 title("d1'(t)");
 grid;
 xlabel("Time(sec)");
 ylabel("d1'(t)");
saveas(1,"1Agd1.png")

 figure;

 subplot(211);
 plot(t,  y2, 'k');
 title("d2(t)");
 grid;
 xlabel("Time(sec)");
 ylabel("d2(t)");

 subplot(212);
 plot(t, d2d, 'r');
 title("d2'(t)");
 grid;
 xlabel("Time(sec)");
 ylabel("d2'(t)");
 saveas(2,"1Agd2.png")


