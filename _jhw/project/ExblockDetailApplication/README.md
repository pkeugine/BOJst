> # Description

### AndroidManifest
- 타이틀바 제거
- http 접속 허용
- gps 권한 허용  
- TmapKey 정보 [strings.xml에도 추가]

### Gradle
- TmapSDK 라이브러리 [파일 직접 추가]
- retrofit2 라이브러리
- butterknife 라이브러리

### Activity
- BaseActivity `공통 액티비티`
  - Metadata 가져오기(TmapKey)
  - 로딩 다이얼로그 보여주기
  - 로딩 다이얼로그 없애기
- WaitingDialog `로딩 다이얼로그`
- Detail `상세정보 데이터 클래스`
  - 직렬화 통신을 위해 Parcelable 사용 [현재 불필요]
- Review `리뷰정보 데이터 클래스`
- RetrofitExService `retrofit2 인터페이스`
  - 네트워크 통신 역할
  - Tmap api 통신
  - DB 통신
- Main2Activity `POI ID 버튼 액티비티`
  - 임의의 poiId를 버튼에 따라 다음 액티비티로 전송
  - 임의의 이전 액티블록 좌표를 다음 액티비티로 전송
- MainActivity `상세 정보 액티비티`
  - retrofit2 콜백 함수를 이용해 tmap api, db 통신
  - 추출한 결과 값으로 뷰 완성, 지도 표시
- GpsTracker `GPS 관련 액티비티`
  - gps 권한을 이용하여 디바이스 현재 위치 좌표 추출
- PathSearchActivity `경로탐색 액티비티`
  - 현 위치로부터 지점까지 도보 및 자동차 경로 탐색
- RecordInfoActivity `여행기록 상세 액티비티`
  - 현재 개략적인 UI 만 구현한 상태 (다른 액티비티와 연결 되어 있진 않음)
