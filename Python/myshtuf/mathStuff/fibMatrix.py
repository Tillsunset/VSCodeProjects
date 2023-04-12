import math

def multiply(F, M):
     
    x = (F[0][0] * M[0][0] +
         F[0][1] * M[1][0])
    y = (F[0][0] * M[0][1] +
         F[0][1] * M[1][1])
    z = (F[1][0] * M[0][0] +
         F[1][1] * M[1][0])
    w = (F[1][0] * M[0][1] +
         F[1][1] * M[1][1])
     
    return [[x,y],
			[z,w]]

# Strassen algo: slower than original due to overhead
# def multiply(f, m):

# 	f00 = f[0][0]
# 	f01 = f[0][1]
# 	f10 = f[1][0]
# 	f11 = f[1][1]
# 	m00 = m[0][0]
# 	m01 = m[0][1]
# 	m10 = m[1][0]
# 	m11 = m[1][1]

# 	p1 = (f00 + f11) * (m00 + m11)
# 	p2 = (f10 + f11) * (m00)
# 	p3 = (f00) * (m01 - m11)
# 	p4 = (f11) * (-m00 + m10)
# 	p5 = (f00 + f01) * (m11)
# 	p6 = (-f00 + f10) * (m00 + m01)
# 	p7 = (f01 - f11) * (m10 + m11)

# 	return [[p1 + p4 - p5 + p7, p3 + p5],
# 			[p2 + p4, p1 + p2 - p3 + p6]]


# Assuming m = [[1,1],[1,0]], no speed up
# def mulOptimze(F):
     
#     y = F[0][0]
#     w = F[1][0]
#     x = y + F[0][1]
#     z = w + F[1][1]
     
#     return [[x,y],
# 			[z,w]]

def fib(n):
	if n == 2:
		return 1
	elif n == 1:
		return 0
	elif n < 1:
		return -1

	f = [[1,1],[1,0]]
	temp = f

	n -= 2
	for i in range(math.floor(math.log2(n) + 1) - 2, -1, -1):
		f = multiply(f, f)
		if (n & (1 << i)):
			f = multiply(f, temp)
			# f = mulOptimze(f)

	return f[0][0]


# for i in range(1, 11):
# 	print(fib(i))
# # print(math.log10(fib(i)))
i = 5000000
fib(i)