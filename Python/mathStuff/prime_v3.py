import math

num = 2

while num < 100000:

	for i in range(2, int(math.sqrt(num)+1)):
		if (num % i) == 0:
			break
	else:
		print(num)

	num = num + 1
