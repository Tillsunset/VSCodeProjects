import math

def f(x):
	return math.log(x)



x = [
6.67,
17.33,
42.67,
37.33,
30.10,
29.31,
28.74
]

x = [
6.67,
16.11,
18.89,
15.0,
10.56,
9.44,
8.89
]

n= len(x)-1

for k in range(n + 1):
	print("%.2f" % (x[k]))
	for i in range(n + 1):
		if i != k:
			print("(x - %.2f)/(%.2f)" % (x[i], x[k] - x[i]))
	print("")