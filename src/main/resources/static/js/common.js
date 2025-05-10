// jQuery를 로드하기 위한 <script> 태그를 생성
const script = document.createElement('script');

// jQuery CDN URL 및 무결성 설정
script.src = 'https://code.jquery.com/jquery-3.6.0.min.js';
script.integrity = 'sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=';
script.crossOrigin = 'anonymous';

// jQuery가 로드된 후 실행할 함수 등록
script.onload = function () {
    $(document).ready(function () {
        console.log('jQuery is ready!');

        // 여기에 공통적으로 사용할 JS 코드 작성 가능
    });
};

// <head>에 스크립트 추가
document.head.appendChild(script);

// 클립보드 복사 함수 정의
function copyToClipboard() {
    const uuidTextElement = document.getElementById('uuidText');
    const contentDiv = document.getElementById('contentDiv');
    const originalText = uuidTextElement.innerText;

    // 텍스트 복사
    const tempTextArea = document.createElement('textarea');
    tempTextArea.value = originalText;
    document.body.appendChild(tempTextArea);
    tempTextArea.select();
    document.execCommand('copy');
    document.body.removeChild(tempTextArea);

    // UI 피드백
    uuidTextElement.innerText = '클립보드에 복사되었습니다';
    contentDiv.classList.add('disabled');

    // 3초 후 복원
    setTimeout(() => {
        uuidTextElement.innerText = originalText;
        contentDiv.classList.remove('disabled');
    }, 3000);
}
