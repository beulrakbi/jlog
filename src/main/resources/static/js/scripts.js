/*!
* Start Bootstrap - Clean Blog v6.0.9 (https://startbootstrap.com/theme/clean-blog)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-clean-blog/blob/master/LICENSE)
*/
window.addEventListener('DOMContentLoaded', () => {
    let scrollPos = 0;
    const mainNav = document.getElementById('mainNav');
    const headerHeight = mainNav.clientHeight;
    window.addEventListener('scroll', function() {
        const currentTop = document.body.getBoundingClientRect().top * -1;
        if ( currentTop < scrollPos) {
            // Scrolling Up
            if (currentTop > 0 && mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-visible');
            } else {
                console.log(123);
                mainNav.classList.remove('is-visible', 'is-fixed');
            }
        } else {
            // Scrolling Down
            mainNav.classList.remove(['is-visible']);
            if (currentTop > headerHeight && !mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-fixed');
            }
        }
        scrollPos = currentTop;
    });
})

function wrapSelection(wrapper) {
    let textarea = document.getElementById('content');
    let start = textarea.selectionStart;
    let end = textarea.selectionEnd;
    let selected = textarea.value.substring(start, end);
    textarea.setRangeText(wrapper + selected + wrapper);
}

function prependSelection(prefix) {
    let textarea = document.getElementById('content');
    let start = textarea.selectionStart;
    let end = textarea.selectionEnd;
    let selected = textarea.value.substring(start, end);
    textarea.setRangeText(prefix + selected);
}

// 링크 삽입
function insertLink() {
    const url = prompt("링크 URL을 입력하세요:");
    const text = prompt("링크 텍스트를 입력하세요:");
    if(!url || !text) return;

    const textarea = document.getElementById('content');
    const start = textarea.selectionStart;
    const end = textarea.selectionEnd;
    const selected = textarea.value.substring(start, end);

    const linkText = `[${text}](${url})`;

    if(selected.length > 0){
        // 선택 영역이 있으면 대체
        textarea.setRangeText(linkText);
    } else {
        // 선택 영역 없으면 커서 위치에 삽입
        textarea.setRangeText(linkText, start, start, "end");
    }
    textarea.focus();
}