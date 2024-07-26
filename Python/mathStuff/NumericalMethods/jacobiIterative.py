x = [0,0,0,0]

a=[[10,5,0,0],
   				[5,10,-4,0],
				[0,-4,8,-1],
				[0,0,-1,5]]

b = [6,25,-11,-11]
def jacobi(a, x, b):
	out = []
	for i in range(len(x)):
		temp = 0
		for j in range(len(a[i])):
			if j != i:
				temp += a[i][j]*x[i]
		temp += b[i]
		temp /=a[i][i]
		out.append(temp)
	return out

# for i in range(1000):
print(x)
x = jacobi(a,x,b)
print(x)
x = jacobi(a,x,b)
print(x)
