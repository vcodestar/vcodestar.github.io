#Chasanis Evangelos cs05058
#Mouselimi Georgia Amalia cs05074
#Sakellariou Ioanna cs05125

clear;
clc;

syms d1d(m1, m2, b1, b2, g, f1, f2, d1, d2, t);
syms d1dd(m1, m2, b1, b2, g, f1, f2, d1, d2, t);
syms d1f(m1, m2, b1, b2, g, f1, f2, d1, d2, t);

syms d2d(m1, m2, b1, b2, g, f1, f2, d1, d2, t);
syms d2dd(m1, m2, b1, b2, g, f1, f2, d1, d2, t);
syms d2f(m1, m2, b1, b2, g, f1, f2, d1, d2, t);

syms d10;
syms d20;
syms h;


d1d(m1, m2, b1, b2, g, f1, f2, d1, d2, t) = ((f1 - (g * (m1 + m2))) / b1) * (1 - exp( (-b1 / (m1 + m2 )) * t));
d1dd(m1, m2, b1, b2, g, f1, f2, d1, d2,t) = ((f1 - (g * (m1 + m2))) / b1) * (b1 / (m1 + m2)) * exp((-b1 / (m1 + m2)) * t);
d1f(m1, m2, b1, b2, g, f1, f2, d1, d2, t) = d10 + h*d1d + (h^2)*d1dd;

d2d(m1, m2, b1, b2, g, f1, f2, d1, d2, t) = (f2 / b2) * (1 - exp((-b2 / m2) * t));
d2dd(m1, m2, b1, b2, g, f1, f2, d1, d2, t) = (f2 / m2) * exp((-b2 / m2) * t);
d2f(m1, m2, b1, b2, g, f1, f2, d1, d2, t) = d20 + h*d2d + (h^2)*d2dd;

for i=0:0.001:30
  d1f(m1, m2, b1, b2, g, f1, f2, d1, d2, i)
  d2f(m1, m2, b1, b2, g, f1, f2, d1, d2, i)
end;



