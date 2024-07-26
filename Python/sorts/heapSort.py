import random
import time

initial = time.time()
array = []

def checkSorted(x):
	for i in range(1,len(x)):
		if x[i - 1] > x[i]:
			return False
	return True

def heapify(x):
	index = 0
	size = len(x)
	while (2 * index + 1 < size):
		index += 1
	
	for i in range(index - 1, -1, -1):
		while (True):
			rightChild = 2 * i + 2
			leftChild = 2 * i + 1
			if (size > rightChild):
				if (x[rightChild] > x[leftChild]):
					if (x[i] < x[rightChild]):
						x[i], x[rightChild] = x[rightChild], x[i]
						i = rightChild
					else:
						break
				else:
					if (x[i] < x[leftChild]):
						x[i], x[leftChild] = x[leftChild], x[i]
						i = leftChild
					else:
						break
			elif (size > leftChild):
				if (x[i] < x[leftChild]):
					x[i], x[leftChild] = x[leftChild], x[i]
					i = leftChild
				else:
					break
			else:
				break
	return x

def siftDown(x):
	for i in range(len(x) - 1, 0, -1):
		x[0], x[i] = x[i], x[0]
		x = siftDownHelper(x,i)
	return x

def siftDownHelper(x, index):
	i = 0
	while (True):
		rightChild = 2 * i + 2
		leftChild = 2 * i + 1
		if (index > rightChild):
			if (x[rightChild] > x[leftChild]):
				if (x[i] < x[rightChild]):
					x[i], x[rightChild] = x[rightChild], x[i]
					i = rightChild
				else:
					break
			else:
				if (x[i] < x[leftChild]):
					x[i], x[leftChild] = x[leftChild], x[i]
					i = leftChild
				else:
					break
		elif (index > leftChild):
			if (x[i] < x[leftChild]):
				x[i], x[leftChild] = x[leftChild], x[i]
				i = leftChild
			else:
				break
		else:
			break
	return x

while len(array) < 100000:
	array.append(random.randint(0,100000))

array = heapify(array)
array = siftDown(array)

timeTaken = time.time() - initial
# print(array)
# print(checkSorted(array))
print(timeTaken)

