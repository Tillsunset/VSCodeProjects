import random
input = 'this is a test'
output = ''
for i in input:
	output += random.choice([i.lower(), i.capitalize()])

print(output)