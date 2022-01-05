import os
import time

def progress(count, total):
    bar_len = 100
    filled_len = int(round(bar_len * count / float(total)))

    print("Current progress")
    print("|" + "█" * filled_len + "-" * (bar_len-filled_len) + "|")

for i in range(1,100 + 1):
    os.system('clear')
    progress(i,100)
    time.sleep(.050)