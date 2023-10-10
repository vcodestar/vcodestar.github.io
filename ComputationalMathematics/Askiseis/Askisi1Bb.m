#Chasanis Evangelos cs05058
#Mouselimi Georgia Amalia cs05074
#Sakellariou Ioanna cs05125

clc;
clear;
#x1 = d1, x2 = d1'
syms x1n(m1, m2, b1, d1des, Kp1);
syms x2dn(m1, m2, b1, d1des, Kp1);
syms x2n(m1, m2, b1, d1des, Kp1);

syms h;
syms x1n_1; #x1(n - 1)
syms x2n_1; #x2(n - 1)


x1n(m1, m2, b1, d1des, Kp1) = x1n_1 +  h * x2n_1;
x2dn(m1, m2, b1, d1des, Kp1) = (-Kp1 / (m1 + m2)) * x1n_1 - (b1 / (m1 + m2)) * x2n_1 + (Kp1 / (m1 + m2)) *d1des;
x2n(m1, m2, b1, d1des, Kp1) = x2n_1 + h * x2dn;

for i=0:0.001:30
  x1n(m1, m2, b1, d1des, Kp1)
  x2n(m1, m2, b1, d1des, Kp1)
end;
