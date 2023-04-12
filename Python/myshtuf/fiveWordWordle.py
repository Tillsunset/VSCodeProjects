
def invrSqrt(x):

	hex = x.hex()

	i = 0x5f3759df - (x.hex())
	y = i.fromHex()
	return y * (3/2 - (x/2 * y * y))


x = 3/2

y= x.hex()

print(invrSqrt(4.0))
print(y)