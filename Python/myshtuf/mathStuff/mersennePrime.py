import math


def isPrime(k):
	for i in range(2, int(math.sqrt(k) + 1)):
		if (k % i) == 0:
			return False
	else:
		return True


num = 2

# don't fucking change anything, it is as fast as it's going to get
# it'll get to 2^61-1 in about 20 minutes without any false positives
# don't try waiting for any more, will take longer than you will live

while True:

	testnum = (2**num)-1
	for i in range(2, int(math.sqrt(num))+1):
		if (num % i) == 0:
			break
	else:
		for g in range(2, int(math.sqrt(testnum)+1)):
			if isPrime(g):
				if (testnum % g) == 0:
					break
		else:
			print(testnum)
	num += 1
