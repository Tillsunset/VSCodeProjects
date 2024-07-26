import math

def f(x):
	return (5/(x**2))+2


high = 2
low = 2.91

# i = 50

# while (i > 0):
# 	mid = (high + low )/2
# 	fmid = f(f(mid))

# 	if (fmid < high):
# 		high = mid
# 	else:
# 		low = mid
# 	print(fmid)
# 	print(f(fmid))

# 	i -= 1

i = 50

while (i > 0):
	midfx = f(high)

	print(high)
	print(high-2.6906474480286136)
	print(50-i)
	high = midfx

	i -= 1