# MiniApp

몰입캠프 1주차 프로젝트

## Content 

>**Tab1: Home**   

직접 그림을 그려 HomeFragment에 넣었다. 이외에는 기능없음

> **Tab2: Contacts**
스마트폰에 있는 연락처를 연동해서 리사이클러뷰를 이용해 핸드폰에 보여준다.


> **Tab3: Gallery**

사진 추가 버튼을 누르고 스마트폰에 저장된 이미지를 가져온다.(Bundle을 사용해서 Fragment에 정보를 전달)
가져온 이미지를 리사이클러뷰의 그리드매니저레이아웃을 이용해 이미지를 한줄에 3개씩 보여준다.
사진 클릭시 PhotoFragment에서 intent로 갤러리에서 가져온 이미지를 전달한다. viewpager2와 photoview를 사용해 슬라이드, 줌 인/아웃 기능을 구현했다.   
<img src="https://user-images.githubusercontent.com/77565951/148003792-34658228-c9c8-46f8-9162-7dd76c4cd64e.gif"/>

> **Tab4: Game**

가속도 센서와 진동센서를 이용해서 만든 간단한 낚시 게임이다   
<img src="https://user-images.githubusercontent.com/77565951/148007584-b501974d-41f2-48f5-8a65-83747c73e4d1.gif"/>
<img src="https://user-images.githubusercontent.com/77565951/148007625-130e7725-9ab8-47b4-b73d-b8b96e9375ae.gif"/>

## Detail

전체 순서도   
<img src="./files/flowchart.png"/>   

메뉴 : 하단 스크롤시 숨겨짐, 상단 스크롤시 다시 나타남(CoordinatorLayout 사용)

### 사용한 라이브러리

Glide
PhotoView



