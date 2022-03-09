import random
import time
testArray = []

def quicksort(x):
	if len(x) > 2:# quickSort
		lower = []
		upper = []
		same = []
		pivot1 = x.pop(-1)
		pivot2 = x.pop(-1)
		pivot3 = x.pop(-1)
		pivot = 0

		if pivot1 < pivot2:
			if pivot2 < pivot3:
				lower.append(pivot1)
				pivot = pivot2
				upper.append(pivot3)
			elif pivot2 > pivot3:
				upper.append(pivot2)
				if pivot1 < pivot3:
					lower.append(pivot1)
					pivot = pivot3
				elif pivot1 > pivot3:
					lower.append(pivot3)
					pivot = pivot1
				else:
					pivot = pivot1
					same.append(pivot3)
			else:
				lower.append(pivot1)
				pivot = pivot3
				same.append(pivot2)
		elif pivot1 > pivot2:
			if pivot1 < pivot3:
				lower.append(pivot2)
				pivot = pivot1
				upper.append(pivot3)
			elif pivot1 > pivot3:
				upper.append(pivot1)
				if pivot2 < pivot3:
					lower.append(pivot2)
					pivot = pivot3
				elif pivot2 > pivot3:
					lower.append(pivot3)
					pivot = pivot2
				else:
					pivot = pivot2
					same.append(pivot3)
			else:
				lower.append(pivot2)
				pivot = pivot3
				same.append(pivot1)
		else:
			pivot = pivot1
			same.append(pivot2)
			if pivot1 < pivot3:
				upper.append(pivot3)
			elif pivot1 > pivot3:
				lower.append(pivot3)
			else:
				same.append(pivot3)

		for e in x:
			if e < pivot:
				lower.append(e)
			elif e > pivot:
				upper.append(e)
			else:
				same.append(e)

		lower = quicksort(lower)
		upper = quicksort(upper)
		lower = lower + same 
		lower = lower + upper 
		return lower
	elif len(x) == 2:
		if x[0] < x[1]:
			return x
		else:
			return [x[1], x[0]]
	else:
		return x


while len(testArray) < 10000:
	testArray.append(random.randint(0, 100000))

	
start = time.time()
testArray = quicksort(testArray)
print(time.time() - start)
# print(testArray)
