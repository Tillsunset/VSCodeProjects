import string
dictionaryPath = "/Users/sebastian/Desktop/Dictionary 2.txt"
textPath = "/Users/sebastian/Desktop/V18.txt"

text = open(textPath, "r")
dictionary = open(dictionaryPath, "r+")
tempArray = []

chars = string.ascii_letters + string.digits + '!@#$%^&*()'

words = 0
for line in text:

    words = line.count(' ')
    if line != '\n':
        words += 1
    
    if words > 1:
        for i in line.split(' '):
            tempArray.append(i.replace("\n","").replace(".","").replace(",","").replace(")","").replace("(",""))
    elif words == 1:
        tempArray.append(line[0:-1])

for i in tempArray:
    dictionary.write(i + "\n")
print()
