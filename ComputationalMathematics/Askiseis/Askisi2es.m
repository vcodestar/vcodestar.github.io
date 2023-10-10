#Chasanis Evangelos cs05058
#Mouselimi Georgia Amalia cs05074
#Sakellariou Ioanna cs05125

clear;
clc;

c1 = -0.95625;
c2 = -0.74443;
d1des = 1.2125;

h = 0.001;
t = 0:h:30;
t = 0.001;

d1(1) = d1des + c1 .* exp(-0) .* cos(0 * sqrt(165) / 10) + c2 .* exp(-0) .* sin(0 * sqrt(165) / 10);
d1d(1) = exp(-0) * cos(0 * sqrt(165) / 10) * (-c1 + c2 * sqrt(165) / 10) + exp(-0) * sin(0 * sqrt(165) / 10);
for i=2:30001
  d1(i) = d1des + c1 .* exp(-t) .* cos(t * sqrt(165) / 10) + c2 .* exp(-t) .* sin(t * sqrt(165) / 10);
  d1d(i) = exp(-t) * cos(t * sqrt(165) / 10) * (-c1 + c2 * sqrt(165) / 10) + exp(-t) * sin(t * sqrt(165) / 10) * (-c2 -c1 * sqrt(165) / 10);

  t = t + h;
end

disp(d1(1))
disp(d1(3000))
disp(d1(4000))
disp(d1(8000))
disp(d1(20000))
disp(d1(30001))


t = 0:0.001:30;

subplot(211);

plot(t, d1, 'k');
grid;
title("d1(t) Graph");
xlabel("Time(sec)");
ylabel("d1(t)");
subplot(212);

plot(t, d1d, 'm');
title("d1'(t) Graph");
xlabel("Time(sec)");
ylabel("d1'(t)");
grid;

saveas(1,"2Graph.png");

