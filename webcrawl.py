from selenium import webdriver
from selenium.common.exceptions import ElementNotVisibleException
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.select import Select
import time
import scrapy
import requests
from bs4 import BeautifulSoup
from selenium.webdriver.support.wait import WebDriverWait


#에브리 타임 시간표 자료를 크롤링 하는 코드
url = "https://knu.everytime.kr/login?redirect=%2Ftimetable"


driver = webdriver.Firefox(executable_path="D:/cloud/google/pressx/webdriver/geckodriver.exe")#드라이버 주소
delay = 10  # seconds
driver.get(url)
driver.find_element_by_name('userid').send_keys('Id') #에브리타임 id
driver.find_element_by_name('password').send_keys('userpw') # 에브리타임 비밀번호
driver.find_element_by_name('password').send_keys(Keys.ENTER)
time.sleep(3)
ul_elem = driver.find_element_by_class_name("floating")

li_elem = ul_elem.find_element_by_tag_name("li")
driver.execute_script("arguments[0].click();", li_elem)
time.sleep(2)
subjects = driver.find_element_by_id("subjects")
filters = subjects.find_element_by_class_name("filter")
item = filters.find_element_by_class_name("item")

driver.execute_script("arguments[0].click();", item)
f = open("새파일.txt", 'w')
time.sleep(2)
subjects = driver.find_element_by_id("subjectCategoryFilter")
filters = subjects.find_element_by_class_name("filter")
category = filters.find_element_by_class_name("category")
li_elem = category.find_elements_by_tag_name("li")
driver.execute_script("arguments[0].click();", li_elem[179])# 컴퓨터학부
time.sleep(2)
list = driver.find_element_by_class_name("list")
f.write(list.text)
print(list.text)
driver.execute_script("arguments[0].click();", li_elem[180])#글로벌 sw
time.sleep(2)
list = driver.find_element_by_class_name("list")
f.write(list.text)
print(list.text)