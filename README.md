1. 도메인

```html
Book
- id (long)
- title(제목, string)
- author(저자, string)
- publisher(출판사, string)
- publicationDate(발행일, date)
- isbn(고유 식별 번호, string)
- purchaseDate(구매일, date)
- category(카테고리, string)
- location(위치, string)
- bookshelfId(FK, long)

Bookshelf
- id(long, pk)
- name(string, pk)
- floor(책장 층수)(int)
```


2. 기본 요구 사항

```html
Bookshelf
1. 책장 추가
 - 사용자는 새로운 책장을 추가할 수 있어야 한다.
 - 책장의 책장 층수, 이름 등의 정보를 입력할 수 있어야 한다.
2. 책장 상세 검색
 - 사용자는 특정 책장의 상세 정보를 조회할 수 있어야 한다.
 - 책장의 위치, 카테고리 등의 정보를 상세하게 확인할 수 있어야 한다.
3. 책장 정보 수정
 - 사용자는 책장의 정보를 수정할 수 있어야 한다.
 - 수정 가능한 정보로는 위치, 카테고리 등이 있어야 한다.
4. 책장 삭제
 - 사용자는 특정 책장을 삭제할 수 있어야 한다.
 - 삭제하려는 책장을 고를 수 있어야 한다.
```
