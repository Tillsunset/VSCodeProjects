sum = 0
iteration = 0


def factorial(n):
	if n == 0:
		return 1
	else:
		return n * factorial(n - 1)


while iteration < 20:
	sum += 1 / factorial(iteration)
	print(sum)
	iteration = iteration + 1
