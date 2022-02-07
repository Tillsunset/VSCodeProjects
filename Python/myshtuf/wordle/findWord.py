import json

def calcFreq(x):
	wordBank = 'wordle/wordBankFromSite.json'
	wordBank = json.loads(open(wordBank).read())
	letterFreq = 'wordle/letterOccurrence.json'
	letterFreq = json.loads(open(letterFreq).read())
	NotAccepted = 'wordle/wordsNotAccepted.json'
	NotAccepted = json.loads(open(NotAccepted).read())

	freq = {}
	for i in wordBank:
		for j in range(5):
			temp2 = str(i[j])
			temp = temp2 + str(j)
			if temp2 not in x and i not in NotAccepted:
				if i in freq:
					freq[i] += letterFreq[temp]
				else :
					freq[i] = letterFreq[temp]
	return freq

def StrEquals(x,y):
	if len(x) != len(y):
		return False
	for i in range(len(x)):
		if x[i] != y[i]:
			return False

	return True

matchedWords = []

findLetterWord = ''
findMaxVal = 0

'''
knownLettersAndPosition = 		['','','','','']
knownLettersButNotPosition = 	['','','','','']
doesNotContains = 				''
'''

knownLettersAndPosition = 		['','','','','']
knownLettersButNotPosition = 	['','','','','']
doesNotContains = 				''

doesContains = ''
for i in knownLettersAndPosition:
	doesContains += i
for i in knownLettersButNotPosition:
	doesContains += i
doesContains = ''.join(set(doesContains))

lettersToCheck = ''
for i in knownLettersAndPosition:
	lettersToCheck += i
for i in doesNotContains:
	lettersToCheck += i

wordsFreq = calcFreq(lettersToCheck)

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
		matches += 1
		matchedWords.append(i)

print(matches)

if matches < 10:
	print(matchedWords)

for i in wordsFreq:
	if wordsFreq[i] > findMaxVal:
		findMaxVal = wordsFreq[i]
		findLetterWord = i

print(findLetterWord)
