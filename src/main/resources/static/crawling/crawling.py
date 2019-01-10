import re
import json
import math
import datetime
import requests
import urllib.request
import urllib.error
import urllib.parse
from bs4 import BeautifulSoup

import codecs
OUTPUT_FILE_NAME = 'output4.txt'
text = ''
naver_client_id = "LDp9BYkvyaslYqUOz1h5"
naver_client_secret = "RDKyqh1axY"
# 파일 열기
file = codecs.open("밀크인제주.txt", 'w', encoding="utf-8")

def naver_blog_crawling(search_blog_keyword, display_count, sort_type):
    search_result_blog_page_count = get_blog_search_result_pagination_count(search_blog_keyword, display_count)
    get_blog_post(search_blog_keyword, display_count, search_result_blog_page_count, sort_type)
def get_blog_search_result_pagination_count(search_blog_keyword, display_count):
    encode_search_keyword = urllib.parse.quote(search_blog_keyword)
    url = "https://openapi.naver.com/v1/search/blog?query=" + encode_search_keyword
    request = urllib.request.Request(url)
    request.add_header("X-Naver-Client-Id", naver_client_id)
    request.add_header("X-Naver-Client-Secret", naver_client_secret)
    response = urllib.request.urlopen(request)
    response_code = response.getcode()
    if response_code is 200:
        response_body = response.read()
        response_body_dict = json.loads(response_body.decode('utf-8'))
        if response_body_dict['total'] == 0:
            blog_pagination_count = 0
        else:
            blog_pagination_total_count = math.ceil(response_body_dict['total'] / int(display_count))
            if blog_pagination_total_count >= 100:
                blog_pagination_count = 100
            else:
                blog_pagination_count = blog_pagination_total_count
            print("키워드 " + search_blog_keyword + "에 해당하는 포스팅 수 : " + str(response_body_dict['total']))
            print("키워드 " + search_blog_keyword + "에 해당하는 블로그 실제 페이징 수 : " + str(blog_pagination_total_count))
            print("키워드 " + search_blog_keyword + "에 해당하는 블로그 처리할 수 있는 페이징 수 : " + str(blog_pagination_count))
        return blog_pagination_count
def get_blog_post(search_blog_keyword, display_count, search_result_blog_page_count, sort_type):
    encode_search_blog_keyword = urllib.parse.quote(search_blog_keyword)
    for i in range(1, search_result_blog_page_count + 1):
        file.write("=" * 50 + "\n")
        url = "https://openapi.naver.com/v1/search/blog?query=" + encode_search_blog_keyword + "&display=" + str(
            display_count) + "&start=" + str((i - 1) * 10 + 1) + "&sort=" + sort_type
        request = urllib.request.Request(url)
        request.add_header("X-Naver-Client-Id", naver_client_id)
        request.add_header("X-Naver-Client-Secret", naver_client_secret)
        response = urllib.request.urlopen(request)
        response_code = response.getcode()
        if response_code is 200:
            response_body = response.read()
            response_body_dict = json.loads(response_body.decode('utf-8'))
            # print(len(response_body_dict['items']))
            for j in range(0, len(response_body_dict['items'])):
                try:
                    file.write('-' * 50 + "\n")
                    blog_post_url = response_body_dict['items'][j]['link'].replace("amp;", "")
                    get_blog_post_content_code = requests.get(blog_post_url)
                    get_blog_post_content_text = get_blog_post_content_code.text
                    get_blog_post_content_soup = BeautifulSoup(get_blog_post_content_text, 'html.parser')
                    for link in get_blog_post_content_soup.select('iframe#mainFrame'):
                        real_blog_post_url = "http://m.blog.naver.com" + link.get('src')
                        get_real_blog_post_content_code = requests.get(real_blog_post_url)
                        get_real_blog_post_content_text = get_real_blog_post_content_code.text
                        get_real_blog_post_content_soup = BeautifulSoup(get_real_blog_post_content_text, 'html.parser')
                        # print("i : " + str(i) + " j : " + str(j))
                        for blog_post_content in get_real_blog_post_content_soup.select('div.se_textView'):
                            blog_post_content_text = blog_post_content.get_text()
                            remove_html_tag = re.compile('<.*?>')
                            blog_post_title = re.sub(remove_html_tag, '', response_body_dict['items'][j]['title'])
                            blog_post_description = re.sub(remove_html_tag, '',
                                                           response_body_dict['items'][j]['description'])
                            blog_post_postdate = datetime.datetime.strptime(response_body_dict['items'][j]['postdate'],
                                                                            "%Y%m%d").strftime("%y.%m.%d")
                            blog_post_blogger_name = response_body_dict['items'][j]['blogger  name']
                            blog_post_full_contents = str(blog_post_content_text)
                            print("포스팅 URL : " + blog_post_url)
                            # print("포스팅 제목 : " + blog_post_title +"i : " + str(i) + " j : " + str(j))
                            # print("포스팅 설명 : " + blog_post_description)
                            # print("포스팅 날짜 : " + blog_post_postdate)
                            # print("블로거 이름 : " + blog_post_blogger_name)
                            # print("포스팅 내용 : " + blog_post_full_contents)
                            blog_post_full_contents
                            file.write(blog_post_full_contents + "\n")
                except:
                    j += 1
naver_blog_crawling("밀크인제주", 10, "sim")
file.write("=" * 50 + "\n")
file.close()