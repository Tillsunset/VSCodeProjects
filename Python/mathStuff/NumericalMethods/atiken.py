import math

def f(x):
	return math.exp(6*x) + 3 * (math.log(2))**2 * math.exp(2 * x) - math.log(8) * math.exp(4 * x) - (math.log(2))**3

def df(x):
	return 6 * math.exp(6*x) + 6 * (math.log(2))**2 * math.exp(2 * x) - 4 * math.log(8) * math.exp(4 * x)

p = [0]
p.append(p[-1] - f(p[-1])/df(p[-1]))

while (abs(p[-1] - p[-2])> 0.0002):
	p.append(p[-1] - f(p[-1])/df(p[-1]))
print(p)

ap = []

i = 0
while(i < 2):
	ap.append(
		p[i]-((p[i+1]-p[i])**2)/(p[i+2]-2*p[i+1]+p[i])
	)
	i+=1

while(abs(ap[-1] - ap[-2])> 0.0002):
	ap.append(
		p[i]-((p[i+1]-p[i])**2)/(p[i+2]-2*p[i+1]+p[i])
	)
	i+=1

print(ap)