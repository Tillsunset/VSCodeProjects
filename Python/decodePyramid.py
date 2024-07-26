def readKey(key_file):
    with open(key_file, 'r') as file:
        lines = file.readlines()
        key = {int(line.split()[0]): line.split()[1].strip() for line in lines}
    return key

def decodePyramid(key):
    decodedMessage = ""
    i = 2
    j = 1
    while j < len(key):
        decodedMessage += key[j] + " "
        j += i
        i += 1
        
    return decodedMessage
        
def decode(message_file):
    key = readKey(message_file)

    decodedMessage = decodePyramid(key)

    return decodedMessage

