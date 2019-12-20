from selenium import webdriver
from selenium.common.exceptions import ElementNotVisibleException
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.select import Select
import time


#에브리 타임 시간표 자료를 크롤링 하는 코드
url = "https://knu.everytime.kr/login?redirect=%2Ftimetable"


driver = webdriver.Firefox(executable_path="C:/Users/사용자/Google 드라이브/webdriver/geckodriver.exe")#드라이버 주소
delay = 3  # seconds
driver.get(url)
driver.find_element_by_name('userid').send_keys('Id') # 에브리타임 id
driver.find_element_by_name('password').send_keys('userpw') # 에브리타임 비밀번호
driver.find_element_by_name('password').send_keys(Keys.ENTER)
time.sleep(3)


semester = input("input semester(형식:xxxx년 x학기): ")
drop = driver.find_element_by_id("semesters")

drop.find_element_by_xpath('//option[contains(text(), "' + semester + '")]').click()

time.sleep(3)

ul_elem = driver.find_element_by_class_name("floating")

li_elem = ul_elem.find_element_by_tag_name("li")
driver.execute_script("arguments[0].click();", li_elem)
time.sleep(2)
subjects = driver.find_element_by_id("subjects")
filters = subjects.find_element_by_class_name("filter")
item = filters.find_element_by_class_name("item")

driver.execute_script("arguments[0].click();", item)
fname = input("파일이름을 입력하세요:")
f = open(fname+".txt", 'w')
time.sleep(2)
subjects = driver.find_element_by_id("subjectCategoryFilter")
filters = subjects.find_element_by_class_name("filter")
category = filters.find_element_by_class_name("category")
li_elem = category.find_elements_by_tag_name("li")
majorname=input("찾아볼 전공을 입력하세요:")
major = category.find_element_by_xpath('//li[contains(text(), "'+majorname+'")]')
driver.execute_script("arguments[0].click();", major)#전공 클릭
time.sleep(2)
list = driver.find_element_by_class_name("list")
body = list.find_element_by_tag_name("tbody")

f.write(body.text)
print(body.text)
