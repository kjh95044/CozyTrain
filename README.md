### Commit Convention

(https://kdjun97.github.io/git-github/commit-convention/)

커밋 컨벤션의 첫 글자는 대문자입니다.

`ex) Feat: Add Key mapping #이슈번호`

| Feat | 기능 (새로운 기능) |
| --- | --- |
| Fix | 버그 (버그 수정) |
| Refactor | 리팩토링 |
| Design | CSS 등 사용자 UI 디자인 변경 |
| Comment | 필요한 주석 추가 및 변경 |
| Style | 스타일 (코드 형식, 세미콜론 추가: 비즈니스 로직에 변경❌) |
| Docs | 문서 수정 (문서 추가, 수정, 삭제, README) |
| Test | 테스트 (테스트 코드 추가, 수정, 삭제: 비즈니스 로직에 변경❌) |
| Chore | 기타 변경사항 (빌드 스크립트 수정, assets, 패키지 매니저 등) |
| Init | 초기 생성 |
| Rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업만 한 경우 |
| Remove | 파일을 삭제하는 작업만 수행한 경우 |

<br/>

### 브랜치 전략 - Git Flow

[https://velog.io/@u-nij/Git-Flow-Commit-message-Issue-이용해서-협업하기](https://velog.io/@u-nij/Git-Flow-Commit-message-Issue-%EC%9D%B4%EC%9A%A9%ED%95%B4%EC%84%9C-%ED%98%91%EC%97%85%ED%95%98%EA%B8%B0)


<img src="https://github.com/harinplz/algorithm_study/assets/62701446/ffea5603-561a-4b77-9251-aad272e9bb62" width="500">

- master : 제품으로 출시될 수 있는 브랜치
- develop : 다음 출시 버전을 개발하는 브랜치 (그냥 개발용 브랜치)
- feature : 기능을 개발하는 브랜치
    - 이슈를 생성한다.
    - 이슈번호가 이슈에 할당이 된다.
    - `feature/이슈번호-login(기능 영어로)` : 브랜치 생성 이름
        
        ![image](https://github.com/harinplz/algorithm_study/assets/62701446/3f4713a0-fdf7-4d3b-8f37-3005ad9f27b4)
        
    - feature 브랜치는 끝나면 develop에 merge

<br/>

## 🗒️ 이슈 생성 방법

<img src="https://github.com/harinplz/algorithm_study/assets/62701446/09763f76-1393-42c2-b24f-771db07ae27d" width="800">

1. 좌측 패널의 Issues 클릭해서 Issues로 이동
2. 우측의 New issue 버튼 클릭

<br/>

<img src="https://github.com/harinplz/algorithm_study/assets/62701446/4e2ca163-f384-413d-aad4-6146834a4e68" width="800">

- Title : 이슈 제목 (구현 기능 간단 요약)
- Description : 이슈 상세 설명 (구현 기능 설명)
- Assignee : 자신 할당하기
- Labels (FE, BE, Mobile)
- 다 만들었다면 Create Issue

<br/>

<img src="https://github.com/harinplz/algorithm_study/assets/62701446/28fd4c8a-c8c6-4f62-945c-f8a78ec1b2f8" width="800">

- Issue가 만들어졌으면 상단에 이슈 번호가 생성된다.
- 이슈번호를 사용하여 브랜치 컨벤션을 따라 브랜치를 생성하고, 만든 브랜치에서 커밋을 할 때 커밋 컨벤션을 따라 커밋 메시지를 작성한다.
- 이슈에 해당하는 기능을 모두 구현했으면 브랜치 merge 후 Close issue를 한다.

<br/>

<img src="https://github.com/harinplz/algorithm_study/assets/62701446/e9f98261-b2f5-42a1-a6c3-4ab6cf3ee2bf" width="800">

- 커밋 컨벤션에 따라 커밋 메시지에 `#이슈번호`를 붙였다면 
이슈 상세 화면에서 지금까지 해당 이슈와 관련하여 어떤 코드를 커밋했는지 자동으로 들고와준다. 👍
