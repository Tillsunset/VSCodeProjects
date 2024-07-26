import math

g = 32.17
t = 1

def f(x):
	return (2-math.exp(x)+x**2)/3


high = -0.5
low = 0


# print(f(.2))
i = 20
while (i > 0):
	midx = (high + low)/2
	midfx = f(midx)

	print(midx)
	print(midfx)
	if midfx > 0:
		high = midx
	elif midfx < 0:
		low = midx

	i -= 1