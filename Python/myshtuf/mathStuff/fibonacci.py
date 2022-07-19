time = 1
number1, number2 = 1, 1


while time <= 5000:
	print(number2, '\n', time)
	number1, number2 = number2, number1 + number2
	time = time + 1
