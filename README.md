# Habitly (Android) — Launchicken Demo App

작은 습관 트래커 Android 앱입니다. [Launchicken](https://github.com/inhwangYang/codex_launchicken)의
Android 스크린샷 자동화 파이프라인을 시연·테스트하기 위한 공개 데모 레포입니다.

- Kotlin + Jetpack Compose, minSdk 26, 외부 서비스 의존성 없음
- 화면: 오늘의 습관(home) / 통계(stats) / 새 습관 만들기(new-habit) / 설정(settings)
- 의도적으로 Launchicken 연동 코드(intent extra 라우팅)가 없는 상태로 유지됩니다 —
  AI 설정 PR 기능의 반복 테스트 픽스처입니다.

빌드: `./gradlew assembleDebug`
