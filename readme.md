# 스프링을 이용하여 기본적인 CRUD 게시판 만들기 
말 그대로 무작성 실습을 해서 손에 익히기

## 진행상황

### DB와 연동하여 저장성공<br>
![image](https://user-images.githubusercontent.com/94429120/220396667-30657674-d57c-4b0c-9e3c-629b2ab681ca.png)     ----------->       ![image](https://user-images.githubusercontent.com/94429120/220396794-af9e772d-f6f8-4969-b54a-bcb62e767d62.png)
### 게시물 View
![image](https://user-images.githubusercontent.com/94429120/222890394-bb75b4a6-aeea-4b43-8e6b-78557b1742da.png)
<br>
삭제, 수정, 다운로드가능(파일을 업로드 했을 시)
<br>
## 페이지 기능과 검색기능 구현
### 페이지 나누기
![image](https://user-images.githubusercontent.com/94429120/222890455-ab511d6e-cf8f-40d2-8d6d-a3af95f93436.png)
<br>
### 검색기능
![image](https://user-images.githubusercontent.com/94429120/222890509-42924305-18bf-4ab9-9d9c-a6b3575cd134.png)
<br>


## 잘 안된 점

![image](https://user-images.githubusercontent.com/94429120/220397418-7dc2307d-4f30-4736-8e67-a74c26495245.png)
<br>
thymeleaf 문법이 적용이 안된건지 findAll 메소드가 제대로 작동이 안되는지 
<br>(제 생각은 thymeleaf 문법이 적용이 안된거같지만)
<br>출력이 안되고 있음 제 생각대로라면 밑에 주루룩 나와야함
<br>
![image](https://user-images.githubusercontent.com/94429120/220525217-52fd4fb3-c41d-474e-8215-bbd0f2e81683.png)
<br>
Thymeleaf에서는 속성 이름과 값을 구분하기 위해 콜론(:)을 사용합니다. 따라서 속성 이름과 속성 값 사이에 띄어쓰기를 넣으면 제대로 적용되지 않습니다.<br>
(이쁘게 맞춘다고 띄어쓰기했었는데 그게 문제가 되어서 적용이 안되었던 것....)

### 검색이 바로 되지 않았던 이유
아래의 코드에서 keyword의 값을 통해서 일반적인 BoardList를 가져올 것인지 아니면 검색이 된 BoardList를 가져올 것인지 구분해놓았는데 실제로 전달되는 데이터는 <br>
무조건 일반적인 boardList를 전달했기 때문에 무조건 첫번째 페이지로 갔었던 것..!!
![image](https://user-images.githubusercontent.com/94429120/222890866-64623488-6260-41ee-ab96-7ed138168bf8.png)

![image](https://user-images.githubusercontent.com/94429120/222890831-54f67348-bce8-44ee-96d1-f038226e8f0c.png)

### 데이터가 전달이 된 이유
난 분명 model.addAttribute를 통해서 searchKeyword를 전달해 준 적이 없는데 무사히 전달이 되었다
<br>
찾아보니 Controller의 RequestParam의 값을 param.searchKeyword를 통해서 가져올 수 
![image](https://user-images.githubusercontent.com/94429120/222891051-59197bd0-a27c-49d7-ae84-d8f22f091916.png)

