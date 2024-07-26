time = 1
number1, number2 = 1, 0


while time <= 10:
	# print(number2, '\n', time)
	print(number2)
	number1, number2 = number2, number1 + number2
	time = time + 1
