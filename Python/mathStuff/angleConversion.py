import math

def toPolar2(x, jy):
	r = math.sqrt(x**2 + jy**2)
	degrees = math.degrees(math.atan2(jy, x))
	return r, degrees

def toRect2(r, degrees):
	return r * math.cos(math.radians(degrees)), r * math.sin(math.radians(degrees))

def toPolar(y):
	x, jy = y[0], y[1]
	r = math.sqrt(x**2 + jy**2)
	degrees = math.degrees(math.atan2(jy, x))
	return r, degrees

def toRect(x):
	r, degrees = x[0], x[1]
	return r * math.cos(math.radians(degrees)), r * math.sin(math.radians(degrees))

def add(x, y):
	x1, y1, x2, y2 = x[0], x[1], y[0], y[1]
	return x1 + x2 , y1 + y2

def sub(x, y):
	x1, y1, x2, y2 = x[0], x[1], y[0], y[1]
	return x1 - x2, y1 - y2

def multiply(x, y):
	r1, degrees1, r2, degrees2 = x[0], x[1], y[0], y[1]
	return r1 * r2, degrees1 + degrees2

def divide(x, y):
	r1, degrees1, r2, degrees2 = x[0], x[1], y[0], y[1]
	return r1 / r2, degrees1 - degrees2

def congugate2(x, jy):
	return x, -jy

def congugate(y):
	x, jy = y[0], y[1]
	return x, -jy

print(
	toPolar2(22/25 ,- (26 )/25)
)