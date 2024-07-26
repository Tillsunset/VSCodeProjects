import math
import time
startTime = time.time()

sum = 0
iteration = 0

while iteration <= 84:
	sum += (((2 << (iteration)) * math.pow(math.factorial(iteration), 2)
			 )/(math.factorial(2*iteration + 1)))
	print(str(sum) + "\t" + str(iteration))
	iteration += 1


print(time.time() - startTime)
