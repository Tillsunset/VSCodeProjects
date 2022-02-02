import json

def calcFreq(x):
	words5l = 'wordle/wordBankFromSite.json'
	words5l = json.loads(open(words5l).read())
	letterOccurrence = 'wordle/letterOccurrence.json'
	letterOccurrence = json.loads(open(letterOccurrence).read())

	freq = {}
	for i in words5l:
		for j in range(5):
			temp2 = str(i[j])
			temp = temp2 + str(j)
			if temp2 not in x:
				if i in freq:
					freq[i] += letterOccurrence[temp]
				else :
					freq[i] = letterOccurrence[temp]
	return freq

def StrEquals(x,y):
	if len(x) != len(y):
		return False
	for i in range(len(x)):
		if x[i] != y[i]:
			return False

	return True

wordsNots = 'wordle/wordsNotAccepted.json'
wordsNots = json.loads(open(wordsNots).read())

maxWord = []

findLetterWord = ''
findMaxval = 0


# knownLettersAndPosition = ['','','','','']
# knownLettersButNotPosition = ['','','','','']
# doesNotContains = ''

knownLettersAndPosition = ['s','','','','']
knownLettersButNotPosition = ['','','','et','ys']
doesNotContains = 'an'


doesContains = ''
for i in knownLettersAndPosition:
	doesContains += i
for i in knownLettersButNotPosition:
	doesContains += i
doesContains = ''.join(set(doesContains)) 

if len(doesContains) < 5:
	findFreq = doesContains
	for i in doesNotContains:
		findFreq += i
	wordsFreq = calcFreq(findFreq)

matches = 0
for i in wordsFreq:
	validWord = True

	for j in doesContains:
		if j not in i:
			validWord = False
			break

	if validWord:
		for j in doesNotContains:
			if j in i:
				validWord = False
				break

	if validWord:
		for j in range(5):
			if len(knownLettersAndPosition[j]) > 0 and i[j] != knownLettersAndPosition[j]:
				validWord = False
				break

	if validWord:
		for j in range(5):
			if len(knownLettersButNotPosition[j]) > 0:
				for k in knownLettersButNotPosition[j]:
					if i[j] == k:
						validWord = False
						break

	if validWord:
		for j in wordsNots:
			if StrEquals(i,j):
				validWord = False
				break

	if validWord:
		matches += 1
		maxWord.append(i)

print(matches)
if matches < 10:
	print(maxWord)
else:
	for i in wordsFreq:
		validWord = True

		if validWord:
			for j in wordsNots:
				if StrEquals(i,j):
					validWord = False
					break

		if validWord:
			matches += 1
			if wordsFreq[i] > findMaxval:
				findMaxval = wordsFreq[i]
				findLetterWord = i

	print(findLetterWord)


