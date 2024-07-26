import random
import time
import math
startTime = time.time()

array = []
bigArray = [array]

def isSmallArraySorted(x):
	i = x[-1]
	for j in range(len(i)-1):
		if i[j] > i[j+1]:
			return False
	return True

def stalinSort(x):
	unsortedArray = x[-1]
	sortedArray = []
	gulag = []
	failedChecks = 0
	maxFailedChecks = math.ceil(math.log(len(unsortedArray)) + 1)

	while failedChecks < maxFailedChecks and len(unsortedArray) > 1:
		if unsortedArray[0] <= unsortedArray[1]:
			sortedArray.append(unsortedArray.pop(0))
			failedChecks = 0
		elif unsortedArray[0] > unsortedArray[1]:
			gulag.append(unsortedArray.pop(1))
			failedChecks +=1
	
	sortedArray.append(unsortedArray.pop(0))
	gulag = gulag + x[-1]
	x[-1] = sortedArray
	x.append(gulag)


def merges(x, y):
	tempArray = []

	while len(x) > 0 and len(y) > 0:
		if x[0] < y[0]:
			tempArray.append(x.pop(0))
		elif x[0] > y[0]:
			tempArray.append(y.pop(0))
		else:
			tempArray.append(x.pop(0))
			tempArray.append(y.pop(0))
	
	if len(x) > 0:
		tempArray = tempArray + x
	elif len(y) > 0:
		tempArray = tempArray + y

	return tempArray

def weave(x):
	i = 0

	while len(x) > 1:
		while i < len(x) - 1:
			x[i] = merges(x[i], x.pop(i + 1))
			i+=1
		i = 0

	return x

while len(array) < 10000:
	array.append(random.randint(0,2147483647))

# print(bigArray)
while not isSmallArraySorted(bigArray):
	stalinSort(bigArray)

print(time.time() - startTime)
# print(bigArray)

while len(bigArray) > 1:
	bigArray = weave(bigArray)

print(time.time() - startTime)
# print(bigArray)