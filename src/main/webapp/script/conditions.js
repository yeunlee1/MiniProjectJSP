// 페이지가 로드되면 실행될 함수 설정
window.onload = function () {
    // 전체 선택/해제할 체크박스 요소 가져오기
    const checkAll = document.getElementById('chkAll');
    // 모든 개별 체크박스 요소 가져오기
    const chks = document.querySelectorAll('.chk');
    // 개별 체크박스의 총 개수
    const chkBoxLength = chks.length;

    // 전체 선택/해제 상태를 업데이트하는 함수 정의
    function updateCheckAll() {
        let count = 0;
        // 모든 개별 체크박스를 반복하며 선택된 개수 확인
        chks.forEach(function (value) {
            if (value.checked) {
                count++;
            }
        });
        // 모든 개별 체크박스가 선택되었을 때 전체 선택/해제 체크박스 체크
        checkAll.checked = count === chkBoxLength;
    }

    // 전체 선택/해제 체크박스의 클릭 이벤트 핸들러 설정
    checkAll.addEventListener('click', function (event) {
        // 모든 개별 체크박스의 선택 상태를 전체 선택/해제 체크박스의 상태로 설정
        chks.forEach(function (value) {
            value.checked = event.target.checked;
        });
    });

    // 각 개별 체크박스의 클릭 이벤트 핸들러 설정
    for (const chk of chks) {
        chk.addEventListener('click', function () {
            // 개별 체크박스가 클릭될 때마다 전체 선택/해제 상태 업데이트
            updateCheckAll();
        });
    }

    // 폼 제출 시 필수 체크 확인하는 이벤트 핸들러 설정
    const form = document.getElementById('joinForm');
    form.addEventListener('submit', function (event) {
        let allChecked = true;
        // 각 필수 체크박스가 선택되었는지 확인
        chks.forEach(function (checkbox) {
            // 필수 체크박스인 경우, 선택 여부 확인
            if (checkbox.required && !checkbox.checked) {
                allChecked = false;
                return; // 폼 제출 중단
            }
        });

        // 필수 항목에 동의하지 않은 경우, 경고 메시지 표시 및 폼 제출 중단
        if (!allChecked) {
            alert('필수 항목에 동의해주세요.');
            event.preventDefault(); // 폼 제출 중단
        }
    });
}
