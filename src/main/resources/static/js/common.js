// src/main/resources/static/js/common.js

// jQuery를 로드하기 위한 <script> 태그를 생성
const script = document.createElement('script');

// 생성한 <script> 태그의 src 속성에 jQuery의 CDN URL을 지정
script.src = 'https://code.jquery.com/jquery-3.6.0.min.js';

// jQuery CDN의 무결성을 보장하기 위한 integrity 속성을 설정
script.integrity = 'sha256-KyZXEAg3QhqLMpG8r+Knujsl5+5hb7ieP1b/6V6CwoE=';

// 외부 리소스를 로드할 때 크로스 도메인 요청의 안전성을 위해 crossOrigin 속성을 설정
script.crossOrigin = 'anonymous';

// 설정된 <script> 태그를 HTML 문서의 <head>에 추가하여 jQuery를 로드
document.head.appendChild(script);

// 문서가 준비되면 실행할 함수를 정의
$(document).ready(function() {
    // jQuery가 정상적으로 로드되었는지 확인하기 위해 콘솔에 메시지를 출력
    console.log('jQuery is ready!');

    // 여기에 공통적으로 사용할 JS 코드를 작성 (예: 페이지 로드 시 특정 동작 수행)
});
