#Chasanis Evangelos cs05058
#Mouselimi Georgia Amalia cs05074
#Sakellariou Ioanna cs05125

clc;
clear;

m1 = 1.5;
m2 = 1;
b1 = 5;

for Kp1=1:100;
  x1(Kp1) = ((-b1 / Kp1) + sqrt((b1 / Kp1)^2 - 4*(m1+m2) / Kp1)) / (2 * (m1 + m2) / Kp1);
  x2(Kp1) = ((-b1 / Kp1) - sqrt((b1 / Kp1)^2 - 4*(m1+m2) / Kp1)) / (2 * (m1 + m2) / Kp1);
end;



for i=1:100
  plot(real(x1(i)), imag(x1(i)), 'rx');
  hold on;
  plot(real(x2(i)), imag(x2(i)), 'kx');
  title(["Kp1 value:", num2str(i)]);
  grid;
  legend('s1', 's2');
  axis([-2 2]);
  xlabel("Re(s)");
  ylabel("Im(s)");
  pause(1);
  hold off;

end;

