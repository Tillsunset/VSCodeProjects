x = [0,0,0,0,0,0]

a=[[4,-1,0,0,0,0],
   				[-1,4,-1,0,0,0],
				[0,-1,4,0,0,0],
   				[0,0,0,4,-1,0],
				[0,0,0,-1,4,-1],
				[0,0,0,0,-1,4]]

b = [0,5,0,6,-2,6]
def gaussSeidel(a, x, b):
	out = []
	for i in range(len(x)):
		temp = 0

		# print(i)
		for j in range(0,len(out)):
			if j != i:
				temp -= a[i][j]*out[j]

		for j in range(len(out),len(a[i])):
			if j != i:
				temp -= a[i][j]*x[i]

		temp += b[i]
		temp /=a[i][i]
		out.append(temp)
	return out

# for i in range(1000):
print(x)
x = gaussSeidel(a,x,b)
print(x)
x = gaussSeidel(a,x,b)
print(x)
