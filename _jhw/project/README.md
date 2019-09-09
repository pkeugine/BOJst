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
