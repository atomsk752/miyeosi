import pymysql
from geopy.geocoders import GoogleV3
import requests

point = "제주신라스테이"

# lat, lng 확인
myApi = 'AIzaSyBAPR_JscpDtaLzmvYV-oAZGQAEp8kxBic'
geolocator = GoogleV3(api_key=myApi)
location = geolocator.geocode(query=point, language='ko', exactly_one=False, timeout=5)

# keyword.txt파일에서 keyword 추출
text = open(point+"키워드.txt", 'r', encoding="utf-8").read()

# img 확인
subscription_key = 'c4f47c9718f840dcb1984fe2b22a6fe3'
assert subscription_key

search_url = "https://api.cognitive.microsoft.com/bing/v7.0/images/search"
headers = {"Ocp-Apim-Subscription-Key" : subscription_key}
params  = {"q": point, "license": "all", "imageType": "photo", "size": "large", "safeSearch": "Moderate"}
response = requests.get(search_url, headers=headers, params=params)
response.raise_for_status()
search_results = response.json()

contentUrl = [img["contentUrl"] for img in search_results["value"][:5]]

# MySQL Connection 연결
conn = pymysql.connect(host='10.10.10.101', user='honeyrock', password='12345678',
                       db='honeyrock', charset='utf8')

# Connection 으로부터 Cursor 생성
curs = conn.cursor()

# SQL문 실행
# insert
sql = "insert into tbl_point (title,lat,lng, keyword, img, category) values (%s, %s, %s, %s, %s, 'H')"
val = (point, location[0].latitude, location[0].longitude, text, contentUrl[1])
curs.execute(sql, val)
conn.commit()
print(curs.rowcount, "record inserted.")

# select
sql = "select max(pno) from tbl_point"
curs.execute(sql)
rows = curs.fetchone()
for x in rows:
    maxPno = x  # 전체 rows
print(maxPno);
# insert img
sql = "insert into tbl_point_img (pno, exiflat, exiflng, iname) values (%s, %s, %s, %s)"
val = [
    (maxPno, location[0].latitude, location[0].longitude, contentUrl[0]),
    (maxPno, location[0].latitude, location[0].longitude, contentUrl[1]),
    (maxPno, location[0].latitude, location[0].longitude, contentUrl[2]),
    (maxPno, location[0].latitude, location[0].longitude, contentUrl[3]),
    (maxPno, location[0].latitude, location[0].longitude, contentUrl[4])
    ]
curs.executemany(sql, val)
conn.commit()
print(curs.rowcount, "record inserted.")

# Connection 닫기
conn.close()