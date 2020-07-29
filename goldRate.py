import requests 
import time
from bs4 import BeautifulSoup

def getRate():
    
    page = requests.get('https://www.ig.com/en/commodities/markets-commodities/gold')
    soup = BeautifulSoup(page.content,'html.parser')
    mydivs = soup.findAll("div", {"data-field": "BID"})

    rate = mydivs[0].get_text()

    t = time.localtime()
    current_time = time.strftime("%H:%M:%S", t)

    with open('rates2.txt','a') as f:
        f.write(rate + " AT " + current_time +"\n")
    
    time.sleep(250)

cnt = 1
while cnt < 15:
    
    getRate()
    cnt += 1