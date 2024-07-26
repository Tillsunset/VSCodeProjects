from machine import Pin, PWM, Timer
import machine
import time

freq = 225000000
machine.freq(freq)
print(machine.freq())

fullPosNS = 1900000
neutralNS = 1450000
fullNegNS = 1000000

pwm = PWM(Pin(1))

upPin = Pin(4, Pin.IN, Pin.PULL_UP)
upPinPassthrough = Pin(6, Pin.OUT, Pin.PULL_DOWN)

downPin = Pin(5, Pin.IN, Pin.PULL_UP)
downPinPassthrough = Pin(7, Pin.OUT, Pin.PULL_DOWN)

# autoShiftSwitch = Pin(3, Pin.IN, Pin.PULL_UP)

# tachometer = Pin(9, Pin.IN, Pin.PULL_DOWN)

def upShiftCycle(periodCount):
	global fullNegNS, neutralNS, fullPosNS

	endActiveTime = 15
	startReturn = 10

	if (periodCount > endActiveTime):
		return fullPosNS
	elif (periodCount > startReturn):
		return neutralNS
	else:
		return fullNegNS
	
def downShiftCycle(periodCount):
	global fullNegNS, neutralNS, fullPosNS

	endActiveTime = 15
	startReturn = 10

	if (periodCount > endActiveTime):
		return fullNegNS
	elif (periodCount > startReturn):
		return neutralNS
	else:
		return fullPosNS

def createShiftCallback():
	global pwm, neutralNS

	pwm.freq(500)
	pwm.duty_ns(neutralNS)

	shiftUp = False

	shiftUpPeriod = 25 # 10 ms active, 5 ms pause, 5 ms down
	shiftDownPeriod = shiftUpPeriod
	periodCount = 0

	debounceDelayConst = 50 # 100 ms debounce delay
	debounceDelay = 0

	buttonHeld = False

	currentGear = 1
	neutral = True

	def updateDutyCycle(t):
		global pwm, neutralNS
		nonlocal buttonHeld, shiftUp, shiftUpPeriod, shiftDownPeriod, periodCount, debounceDelayConst, debounceDelay, currentGear, neutral
		if not buttonHeld:
			if not neutral:
				if not upPin.value() and currentGear < 5:
					shiftUp = True
					buttonHeld = True

					periodCount = shiftUpPeriod
					debounceDelay = debounceDelayConst
					upPinPassthrough.on()
					currentGear += 1
				elif not downPin.value() and currentGear > 1:
					shiftUp = False
					buttonHeld = True

					periodCount = shiftDownPeriod
					debounceDelay = debounceDelayConst
					downPinPassthrough.on()
					currentGear -= 1
			else:
				if not upPin.value():
					neutral = False
					shiftUp = True
					buttonHeld = True

					periodCount = shiftUpPeriod
					debounceDelay = debounceDelayConst
					upPinPassthrough.on()
					currentGear = 2
				elif not downPin.value():
					neutral = False
					shiftUp = False
					buttonHeld = True

					periodCount = shiftDownPeriod
					debounceDelay = debounceDelayConst
					downPinPassthrough.on()
					currentGear = 1

		if periodCount:
			if shiftUp:
				pwm.duty_ns(upShiftCycle(periodCount))
			else:
				pwm.duty_ns(downShiftCycle(periodCount))

			periodCount -= 1
		else:
			pwm.duty_ns(neutralNS)

		if debounceDelay:
			debounceDelay -= 1
		else:
			upPinPassthrough.off()
			downPinPassthrough.off()
			if upPin.value() and downPin.value():
				buttonHeld = False

	return updateDutyCycle

def createAutoShiftCallback():
	lastTachRisingEdge = time.time_ns()
	def updateRPM(t):
		global forceUpShift
		now = time.time_ns()
		rpm = 60 * (1000000000/(now - lastTachRisingEdge))

		forceUpshift = (not autoShiftSwitch.value) and rpm > 8000

		lastTachRisingEdge = now

	return updateRPM

callbackFunc = createShiftCallback()
timer = Timer()

timer.init(freq=500, mode=Timer.PERIODIC, callback=callbackFunc)

while True:
	time.sleep(1)
