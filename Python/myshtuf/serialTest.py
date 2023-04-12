import serial

ser = serial.Serial()
ser.port = '/dev/cu.usbserial-14230'
ser.open()
ser.write(b'hello')

while True:
	print(ser.read(1))
	ser.write(1)

ser.close()