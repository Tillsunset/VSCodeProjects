import math
import time
from decimal import *

getcontext().prec = 50
startTime = time.time()

sum = Decimal(2)
term = Decimal(2)
iteration = 1

while iteration <= 100:
	term = Decimal(term * 2 * iteration * iteration) / \
		Decimal((2 * iteration + 1) * (2 * iteration))
	sum += term
	print(str(sum) + "\t" + str(iteration))
	iteration += 1

print(time.time() - startTime)
