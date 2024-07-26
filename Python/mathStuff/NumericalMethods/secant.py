import math

def f(x):
	return math.exp(x)+2**(-x)+2*math.cos(x)-6

def findLastPositive():
	i = -1
	while(f(p[i]) < 0):
		i -= 1
	return i

def findLastNegative():
	i = -1
	while(f(p[i]) > 0):
		i -= 1
	return i


def pNext():
	u = findLastNegative()
	t = findLastPositive()
	return p[-1] - q[-1]*(p[u] - p[t])/(q[u] - q[t])


p = [1,2]
q = []

q.append(f(p[0]))
q.append(f(p[1]))

p.append(pNext())
q.append(f(p[-1]))
p.append(pNext())
q.append(f(p[-1]))
p.append(pNext())
q.append(f(p[-1]))
p.append(pNext())
q.append(f(p[-1]))
p.append(pNext())

print(p)
print(q)


