// common.js

// 쿠키에서 특정 이름의 쿠키 값을 가져오는 함수
function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

// 공통 fetch 함수
function authFetch(url, options = {}) {
    var accessToken = getCookie("accessToken");
    if (!options.headers) {
        options.headers = {};
    }
    options.headers['Authorization'] = 'Bearer ' + accessToken;

    // JSON 요청의 경우 Content-Type 헤더 설정
    if (!options.headers['Content-Type']) {
        options.headers['Content-Type'] = 'application/json';
    }

    return fetch(url, options);
}

// 쿠키에 값을 설정하는 함수
function setCookie(name, value, days) {
    console.log("name = " + name + ", value=" + value);
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}
