import time
import os
import shutil

# string Lines
offset = ''
line1 =  '                          (  ) (@@) ( )  (@)  ()    @@    O     @     O     @     0'
line2 =  '                     (@@@)'
line3 =  '                 (    )'
line4 =  '              (@@@@)'
line5 =  ''
line6 =  '            (   )'
line7 =  '        ====        ________                ___________'
line8 =  '    _D _|  |_______/        \__I_I_____===__|_________|'
line9 =  '     |(_)---  |   H\________/ |   |        =|___ ___|      _________________'
line10 = '     /     |  |   H  |  |     |   |         ||_| |_||     _|                \_____A'
line11 = '    |      |  |   H  |__--------------------| [___] |   =|                        |'
line12 = '    | ________|___H__/__|_____/[][]~\_______|       |   -|                        |'
line13 = '    |/ |   |-----------I_____I [][] []  D   |=======|____|________________________|_'
line14 = '  __/ =| o |=-~~\  /~~\  /~~\  /~~\ ____Y___________|__|__________________________|_'
line15 = '   |/-=|___|=O=====O=====O=====O   |_____/~\___/          |_D__D__D_|  |_D__D__D_|'
line16 = '    \_/      \__/  \__/  \__/  \__/      \_/               \_/   \_/    \_/   \_/'


def smokeFrame1():
	global line1, line2, line3, line4, line6
	line1 =  '                          (  ) (@@) ( )  (@)  ()    @@    O     @     O     @     0'
	line2 =  '                     (@@@)'
	line3 =  '                 (    )'
	line4 =  '              (@@@@)'
	line6 =  '            (   )'

def smokeFrame2():
	global line1, line2, line3, line4, line6
	line1 =  '                          (@@) (  ) (@)  ( )  @@    ()    @     0     @     0     @'
	line2 =  '                     (   )'
	line3 =  '                 (@@@@)'
	line4 =  '              (    )'
	line6 =  '            (@@@)'

def frame1():
	global line14, line15
	line14 = '  __/ =| o |=-~~\  /~~\  /~~\  /~~\ ____Y___________|__|__________________________|_'
	line15 = '   |/-=|___|=O=====O=====O=====O   |_____/~\___/          |_D__D__D_|  |_D__D__D_|'

def frame2():
	global line15, line16
	line15 = '   |/-=|___|=    ||    ||    ||    |_____/~\___/          |_D__D__D_|  |_D__D__D_|'
	line16 = '    \_/      \O=====O=====O=====O_/      \_/               \_/   \_/    \_/   \_/'

def frame3():
	global line16
	line16 = '    \_/      \_O=====O=====O=====O/      \_/               \_/   \_/    \_/   \_/'

def frame4():
	global line15, line16
	line15 = '   |/-=|___|=   O=====O=====O=====O|_____/~\___/          |_D__D__D_|  |_D__D__D_|'
	line16 = '    \_/      \__/  \__/  \__/  \__/      \_/               \_/   \_/    \_/   \_/'

def frame5():
	global line14, line15
	line14 = '  __/ =| o |=-~O=====O=====O=====O\ ____Y___________|__|__________________________|_'
	line15 = '   |/-=|___|=    ||    ||    ||    |_____/~\___/          |_D__D__D_|  |_D__D__D_|'

def frame6():
	global line14
	line14 = '  __/ =| o |=-O=====O=====O=====O~\ ____Y___________|__|__________________________|_'

width = shutil.get_terminal_size().columns - 84

for i in range(0, width):
	if i%6 == 1:
		frame1()
		smokeFrame1()
	elif i%6 == 2:
		frame2()
	elif i%6 == 3:
		frame3()
	elif i%6 == 4:
		frame4()
		smokeFrame2()
	elif i%6 == 5:
		frame5()
	elif i%6 == 0:
		frame6()

	os.system('clear')

	offset = '\n'+ ' ' * (width - i)

	print(offset + line1 + offset + line2 + offset + line3 + offset + line4 + offset + line5 + offset + line6 + offset + line7 + offset + line8 + 
		offset + line9 + offset + line10 + offset + line11 + offset + line12 + offset + line13 + offset + line14 + offset + line15 + offset + line16)

	time.sleep(0.02)
