import math

def f(x):
	return (2-math.exp(x)+x**2)/3

p=[0]
p.append(f(p[-1]))
p.append(f(p[-1]))
p.append(p[-3]-((p[-2]-p[-3])**2)/(p[-1]-2*p[-2]+p[-3]))

i = 0
while (abs(p[-1] - p[-4]) > 0.00001):
	p.append(f(p[-1]))
	p.append(f(p[-1]))
	p.append(p[-3]-((p[-2]-p[-3])**2)/(p[-1]-2*p[-2]+p[-3]))

print(p)
