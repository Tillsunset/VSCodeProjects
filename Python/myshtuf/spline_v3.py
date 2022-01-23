import math
from operator import truediv

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
MinCuvature = 0
MaxCuvature = 1
DEGREESTORADIANS = math.pi/180
MAXJERK = 10.0
MAXACCELERATION = 5.0
MAXVELOCITY = 10.0
TARGETVELOCITY = 7.0
TIMEUNIT = 0.05
TIMEUNITINVERSE = 1.0/TIMEUNIT

currentRadius = MinCuvature
clockwise = True

