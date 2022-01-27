import math

import numpy
import pylab as plt


def CatmullRomSpline(P0, P1, P2, P3, nPoints=20):
    """
    P0, P1, P2, and P3 should be (x,y) point pairs that define the Catmull-Rom spline.
    nPoints is the number of points to include in this curve segment.
    """
    # Convert the points to numpy so that we can do array multiplication
    P0, P1, P2, P3 = map(numpy.array, [P0, P1, P2, P3])

    # Parametric constant: 0.5 for the centripetal spline, 0.0 for the uniform spline, 1.0 for the chordal spline.
    alpha = 0
    # Premultiplied power constant for the following tj() function.
    # alpha = alpha / 2

    def tj(ti, Pi, Pj):
        xi, yi = Pi
        xj, yj = Pj
        return ((xj - xi) ** 2 + (yj - yi) ** 2) ** alpha + ti

    # Calculate t0 to t4
    t0 = 0
    t1 = tj(t0, P0, P1)
    t2 = tj(t1, P1, P2)
    t3 = tj(t2, P2, P3)

    # Only calculate points between P1 and P2
    t = numpy.linspace(t1, t2, nPoints)

    # Reshape so that we can multiply by the points P0 to P3
    # and get a point for each value of t.
    t = t.reshape(len(t), 1)
    # print(t)
    A1 = (t1 - t) / (t1 - t0) * P0 + (t - t0) / (t1 - t0) * P1
    A2 = (t2 - t) / (t2 - t1) * P1 + (t - t1) / (t2 - t1) * P2
    A3 = (t3 - t) / (t3 - t2) * P2 + (t - t2) / (t3 - t2) * P3
    # print(A1)
    # print(A2)
    # print(A3)
    B1 = (t2 - t) / (t2 - t0) * A1 + (t - t0) / (t2 - t0) * A2
    B2 = (t3 - t) / (t3 - t1) * A2 + (t - t1) / (t3 - t1) * A3

    C = (t2 - t) / (t2 - t1) * B1 + (t - t1) / (t2 - t1) * B2
    # print(C)
    return C


def CatmullRomChain(P):
    """
    Calculate Catmull-Rom for a chain of points and return the combined curve.
    """
    sz = len(P)

    # The curve C will contain an array of (x, y) points.
    C = []
    for i in range(sz - 3):
        C.extend(CatmullRomSpline(P[i], P[i + 1], P[i + 2], P[i + 3]))

    return C


# Define a set of points for curve to go through
Points = [[0, 1], [2, 1], [3, 1], [4, 0.5], [5, 1], [4, 2], [6, 2.66]]

# Calculate the Catmull-Rom splines through the points
c = CatmullRomChain(Points)

# Convert the Catmull-Rom curve points into x and y arrays and plot
x, y = zip(*c)
plt.plot(x, y)

# Plot the control points
px, py = zip(*Points)
plt.plot(px, py, 'or')

desiredX = 0.0
desiredY = 0.0
deltaX = 0.0 # change in x for each step of curve
deltaY = 0.0 # change in y for each step of curve
deltaPos = 0.0 # change in position for each step of curve
length = 0 # length of curve traveled

xPos = 0.0 # current x position
yPos = 0.0 # current y position
lengthTraveled = 0.0 # distance traveled
vel = 0 # max speed 5 ft/s
accel = 0 # max accel 15 ft/s^2
jerk = 0 # max jerk 15 ft/s^3

desiredAng = 0.0
deltaAng = 0.0

ang = 0.0
angVel = 0.0 # max 5/3 rad/s
angAccel = 0.0 # max 5 rad/s^2
angJerk = 0.0 # max 5 rad/s^3

totalJerk = 0

changeInAngle = 0.0

totalArcLength = 4.791188091806965 # reference only, ft
totalAngleChange = 8.563880809395204 # reference only, radians

for i in range(0, len(c)-1):
    desiredX = x[i + 1]
    desiredY = y[i + 1]
    deltaX = desiredX - x[i]
    deltaY = desiredY - y[i]
    deltaPos = ((deltaX)**2 + (deltaY)**2)**0.5
    length += deltaPos

    lastDesiredAngel = desiredAng
    desiredAng = math.atan2(deltaY, deltaX)
    print(desiredAng)
    #changeInAngle += abs(desiredAng - lastDesiredAngel)
    if (changeInAngle < abs(desiredAng - lastDesiredAngel)):
        changeInAngle = abs(desiredAng - lastDesiredAngel)

    #while lengthTraveled < length:
    #    deltaX = desiredX - xPos
    #    deltaY = desiredY - yPos
    #    deltaPos = ((deltaX)**2 + (deltaY)**2)**0.5

    #    desiredAng = math.atan2(deltaY, deltaX)
    #    deltaAng = desiredAng - ang

    #    angJerk = deltaAng


print(changeInAngle, 'this')
# print(c)
# print(x)
# print(y)
# print(deltaPos)
# print(ang)
# print(length, 'ft, length of curve')
# plt.show()