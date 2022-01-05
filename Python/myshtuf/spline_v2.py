import math

# Units are m , m/s , m/s^2 , m/s^3 , degrees
# Starting properties
# state format [x,y,velocity,angle]
states = [
[0,0,0,0],
[5,5],
[10,20,0,0]
]

interpolatedAngles = []

# Constants
TURNRADIUS = 2.0
DEGREESTORADIANS = math.pi/180
MAXJERK = 10.0
MAXACCELERATION = 5.0
MAXVELOCITY = 10.0
TARGETVELOCITY = 7.0
TIMEUNIT = 0.05
TIMEUNITINVERSE = 1.0/TIMEUNIT

def xVelocity(velocity, angle):
    return velocity * math.cos(angle * DEGREESTORADIANS)

def yVelocity(velocity, angle):
    return velocity * math.sin(angle * DEGREESTORADIANS)

def calculatePosition(pos, vel, acc, jer):
    return pos + vel * TIMEUNIT + (acc* TIMEUNIT**2)/2 + (jer * TIMEUNIT**3)/6

def calculateVelocity(vel, acc, jer):
    return vel + acc * TIMEUNIT + (jer* TIMEUNIT**2)/2

def calculateAcceleration(acc, jer):
    return acc + jer * TIMEUNIT

# Step 1 create a spline
# Interpolate angles
for i in range (0,len(states) - 1):
    differenceX = states[i + 1][0] - states[i + 0][0]
    differenceY = states[i + 1][1] - states[i + 0][1]
    interpolatedAngles.append(math.atan2(differenceY,differenceX))



# create spline from interpolated angles, and curve fit for position


# Step 2 convert spline to robot states
# Step 3 intregrate robot states into code