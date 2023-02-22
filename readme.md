# 스프링을 이용하여 기본적인 CRUD 게시판 만들기 
말 그대로 무작성 실습을 해서 손에 익히기

## 진행상황

![image](https://user-images.githubusercontent.com/94429120/220396667-30657674-d57c-4b0c-9e3c-629b2ab681ca.png)     ----------->       ![image](https://user-images.githubusercontent.com/94429120/220396794-af9e772d-f6f8-4969-b54a-bcb62e767d62.png)

DB와 연동하여 저장성공

## 잘 안된 점

![image](https://user-images.githubusercontent.com/94429120/220397418-7dc2307d-4f30-4736-8e67-a74c26495245.png)
<br>
thymeleaf 문법이 적용이 안된건지 findAll 메소드가 제대로 작동이 안되는지 
<br>(제 생각은 thymeleaf 문법이 적용이 안된거같지만)
<br>출력이 안되고 있음 제 생각대로라면 밑에 주루룩 나와야함
<br>
![image](https://user-images.githubusercontent.com/94429120/220525217-52fd4fb3-c41d-474e-8215-bbd0f2e81683.png)
Thymeleaf에서는 속성 이름과 값을 구분하기 위해 콜론(:)을 사용합니다. 따라서 속성 이름과 속성 값 사이에 띄어쓰기를 넣으면 제대로 적용되지 않습니다.<br>
(이쁘게 맞춘다고 띄어쓰기했었는데 그게 문제가 되어서 적용이 안되었던 것..)
