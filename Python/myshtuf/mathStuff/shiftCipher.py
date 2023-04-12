# # input = "KXMRYBNYGXQYNYBOC"
# # output = "" # ANCHORDOWNGODORES

# # for n in range(26):
# # 	for i in input:
# # 		# print(ord(i) - ord('A'))
# # 		output+= (chr(
# # 			ord('A') + (ord(i) - ord('A') + n) % 26
# # 		))

# # 	print(output, '\n', n)
# # 	output = ""

# input = "IbelievethatthisisapracticalworldandthatIcancountonlyonwhatIearnThereforeIbelieveinworkhardwork"

# key = "EAGLE"

# output = ""  # MbkwmivkeletzsmwiyltvaiemgarhsvljlrhtnlxMcgygsutesrlezrahgeMiaxyXlexpjsrkTfilopziithsvknlvhwuco

# for n in range(len(input)):
# 	if 'a' <= input[n] and 'z' >= input[n]:
# 		output += (chr(
# 			ord('a') + (ord(input[n]) - ord('a') + (ord(key[n % len(key)]) - ord('A')) % 26) % 26
# 		))
# 	elif 'A' <= input[n] and 'Z' >= input[n]:
# 		output += (chr(
# 			ord('A') + (ord(input[n]) - ord('A') + (ord(key[n % len(key)]) - ord('A')) % 26) % 26
# 		))
# print(output)
# print(len(input) == len(output))

m = 3
p= 31
q= 47
e= 77
n= p * q
a = m ** e

c = a % n
print(c)
print( (c ** 233) % n)