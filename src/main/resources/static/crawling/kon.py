from konlpy.tag import Twitter
from collections import Counter


def get_tags(text, ntags):
    spliter = Twitter()
    # konlpy의 Twitter객체
    nouns = spliter.nouns(text)
    # nouns 함수를 통해서 text에서 명사만 분리/추출
    count = Counter(nouns)
    # Counter객체를 생성하고 참조변수 nouns할당
    return_list = []  # 명사 빈도수 저장할 변수

    value_to_remove = ["곳","수","것","맛","때","정말","더","저","진짜","바로",
                      "이","여기","날","꼭","안","거","그","또","저희","정도",
                      "제","좀","타고","요","보고","그냥","중","때문","조금",
                      "아주","다른","듯","쪽","등","이번","내","총","전","나",
                      "속"] #삭제할 명사

    for n, c in count.most_common(ntags):
        if n not in value_to_remove:
            temp = {'tag': n, 'count': c}
            return_list.append(temp)
    # most_common 메소드는 정수를 입력받아 객체 안의 명사중 빈도수
    # 큰 명사부터 순서대로 입력받은 정수 갯수만큼 저장되어있는 객체 반환
    # 명사와 사용된 갯수를 return_list에 저장합니다.
    return return_list


def main():
    text_file_name = "메종글래드.txt"
    # 분석할 파일
    noun_count = 50
    # 최대 많은 빈도수 부터 20개 명사 추출
    output_file_name = "메종글래드키워드.txt"
    # count.txt 에 저장
    open_text_file = open(text_file_name, 'r', encoding="utf-8")
    # 분석할 파일을 open
    text = open_text_file.read()  # 파일을 읽습니다.
    tags = get_tags(text, noun_count)  # get_tags 함수 실행
    open_text_file.close()  # 파일 close
    open_output_file = open(output_file_name, 'w', encoding="utf-8")
    # 결과로 쓰일 count.txt 열기
    for tag in tags:
        noun = tag['tag']
        open_output_file.write('{},'.format(noun))
    # 결과 저장
    open_output_file.close()

main()