import math
import time
from decimal import *

getcontext().prec = 50
startTime = time.time()

sum = Decimal(0)
iteration = 0

while iteration <= 100:
	sum += Decimal(Decimal((2 << (iteration)) *
				   math.pow(math.factorial(iteration), 2))/(math.factorial(2*iteration + 1)))
	print(str(sum) + "\t" + str(iteration))
	iteration += 1

print(time.time() - startTime)
