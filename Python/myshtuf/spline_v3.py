import matplotlib.pyplot as plt

states = [
[0,10,20],
[0,20,0]
]

Points = [[0, 1], [2, 1], [3, 1], [4, 0.5], [5, 1], [4, 2], [6, 2]]

states = [[],[]]
for i in Points:
	states[0].append(i[0])
	states[1].append(i[1])


TIMEUNIT = 0.001

def bezier(arr, t):
	if len(arr) <= 1:
		return arr[0]
	return bezier(arr[:-1],t) * (1.0 - t) + bezier(arr[1:],t) * t

t = 0.0
xpos = []
ypos = []
while t <= 1.0:
	# print('x pos: %s\ny pos: %s' % (bezier(states[0],t), bezier(states[1],t)))
	# mp.plot(bezier(states[0],t), bezier(states[1],t))
	xpos.append(bezier(states[0],t))
	ypos.append(bezier(states[1],t))
	t+= TIMEUNIT

plt.scatter(xpos,ypos)

print("")