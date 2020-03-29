> # Activity list

- ExblockApplication `메인 액티블록`
- ExblockDetailApplication `상세 보기`
- ExloginApplication `소셜 로그인 관련 (카카오)`
- ExpoiApplication `위치 기반 주변 POI 검색`
- ExprojApplication2 `리뷰 리스트`
- ExTmapApplication `Tmap 경로 탐색`
- projectspace `서버 통신 관련`





---
> ### 2019.09.08 (일) _수정 사항_
- ExblockDetailApplication
  - 현재 위치 표시 기능 추가
  - 불필요 import 제거
  - Parcelable(직렬화) 기능 제거 [ 현재 불필요 ]
- ExblockApplication
  - 불필요한 DetailActivity 제거
- 새폴더 제거
  - ExprojApplication(리뷰리스트 수정전) 제거
  - projectspace 밖으로 이동
- projectspace
  - 불필요 jsp 파일 제거 (index.jsp 제외)



---
> ### 2019.09.17 (화) _수정 사항_
- ExblockDetailApplication
  - 기본 경로 탐색 화면 -> 일반 지도 화면
  - 내 위치, 경로탐색 버튼 추가
  - 경로 탐색 화면 추가
  - 구동 확인을 위해 DB 호출 부분 주석 처리
  - 부가정보 정보 구분
  - 적정 줌 레벨 및 중심 좌표 설정
  - RecordInfoActivity 추가 ( 여행기록 상세 화면 )
    - 개략적인 UI만 구현
- 추후 구현 사항
  - 여행기록 상세 화면 구현
  - UI 구조 확정
- 기타
  - DB 구조
  - 도로명 주소 지정
  - 이전 액티블록 좌표 가져오기
  - 리뷰 없을 시 디폴트 내용
  - UI 이미지 구체화
