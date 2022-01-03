# MiniApp

몰입캠프 1주차 프로젝트

## Content 

>Tab1: Home   

직접 그림을 그려 HomeFragment에 넣었다. 이외에는 기능없음

> Tab2: Contacts
스마트폰에 있는 연락처를 연동해서 리사이클러뷰를 이용해 핸드폰에 보여준다.


> Tab3: Gallery

사진 추가 버튼을 누르고 스마트폰에 저장된 이미지를 가져온다.(Bundle을 사용해서 Fragment에 정보를 전달)
가져온 이미지를 리사이클러뷰의 그리드매니저레이아웃을 이용해 이미지를 한줄에 3개씩 보여준다.
사진 클릭시 PhotoFragment에서 intent로 갤러리에서 가져온 이미지를 전달한다. viewpager2와 photoview를 사용해 슬라이드, 줌 인/아웃 기능을 구현했다.   


> Tab4: Game

가속도 센서와 진동센서를 이용해서 만든 간단한 낚시 게임이다

## Detail

메뉴
상단 상태바 - 
### 사용한 라이브러리

Glide
PhotoView



