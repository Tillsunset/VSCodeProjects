from html.parser import HTMLParser
from bs4 import BeautifulSoup
import csv
import requests
import re

filePath = 'classList.csv'
writer = csv.writer(open(filePath, 'a'))
reader = csv.reader(open(filePath))

url = 'https://registrar.vanderbilt.edu/faculty-staff/course-renumbering/course-renumbering-lookup-tool.php?w=letter&id=A'
html = requests.get(url).text
soup = BeautifulSoup(html, 'html.parser')
data = soup.find_all(re.compile("tr"))
print(len(data))

for element in data:
    for tag in element.children:
        print(type(tag))
    print(type(element.contents))
    print(len(element.contents))
    print(type(element.contents[0]))
    print(len(element.contents[0]))

    test = element.contents
    string = test[0]
    print(type(test))
    print(len(test))
    print(type(string))
