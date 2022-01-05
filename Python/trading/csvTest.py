import csv

pathRead = 'D:\Secondary Downloads\Pc upgrades - GPU.csv'
pathWrite = 'D:\Secondary Downloads\CSVWriterTest.csv'

reader = csv.reader(open(pathRead))
writer = csv.writer(open(pathWrite,'w'))
for row in reader:
    index1 = row[0].find(")")
    index3 = row[0].find("(")
    index2 = row[0].find(",")
    title = row[0][:index3]
    score = row[0][index1+1:index2+4]
    newRow = []
    newRow.append(title)
    newRow.append(score)
    writer.writerow(newRow)
    print(title)
    print(score)