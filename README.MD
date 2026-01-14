# 회원관리 API 목록

URL Prefix : /v1/api/~

## 회원 API

| URL                   |  METHOD  | PARAMS                                                                                                                       | DESC       |
|:----------------------|:--------:|:-----------------------------------------------------------------------------------------------------------------------------|:-----------|
| /members              |  `GET`   |                                                                                                                              | 멤버 목록 조회   |
| /members/{memberId}   |  `GET`   | `memberId<Long>`: 회원 ID                                                                                                      | 회원 상세 조회   |
| /members              |  `POST`  | `code<String>`: 출석번호 4자리 <br>`name<String>`: 회원 이름 <br> `phone<String>`: 핸드폰 번호 <br>`belt<Strin>`: 벨트 색상 <br>`grau<Int>`: 그랄 | 신규 회원 등록   |
| /members/{memberId}   |  `PUT`   | `code<String>`: 출석번호 4자리 <br>`name<String>`: 회원 이름 <br> `phone<String>`: 핸드폰 번호 <br>`belt<Strin>`: 벨트 색상 <br>`grau<Int>`: 그랄 | 회원 정보 수정   |
| /members/{memberId}   | `DELETE` | `memberId<Long>`: 회원 ID                                                                                                      | 회원 정보 삭제   |
| /validate_code/{code} |  `GET`   | `code<String>`: 출석번호 4자리                                                                                                     | 출석번호 중복 조회 |


## 출석 API

| URL                     | METHOD | PARAMS                   | DESC         |
|:------------------------|:------:|:-------------------------|:-------------|
| /attendances/{code}     | `POST` | `code<String>`: 출석번호 4자리 | 출석 등록        |
| /attendances/daily      | `GET`  | `date`: yyyymmdd         | 일별 출결 리스트 조회 |
| /attendances/{memberId} | `GET`  | `memberId`: 회원 ID        | 회원별 출결 기록 조회 |




