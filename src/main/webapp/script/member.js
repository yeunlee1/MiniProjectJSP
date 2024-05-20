//id,pw validation
function loginCheck(){
   if(document.frm.userid.value.length==0){//값의 길이가 0일 때 트루
      alert("아이디를 입력하세요");
      frm.userid.focus(); //커서
      return false;
   }
   if(document.frm.pwd.value==""){//문자열 비엇을 때
      alert("패스워드를 입력하세요");
      frm.pwd.focus();// 커서
      return false;
   }
}

// 회원가입 정보 검증 및 처리
function joinCheck() {
    if (!validateID()) {
        return false;
    }

    if (!validateName()) {
        return false;
    }

    if (!validatePassword()) {
        return false;
    }

    if (!validateBirthday()) {
        return false;
    }

    if (!validateEmail()) {
        return false;
    }
  
  if (!validateEmail2()) {
        return false;
    }
    return true;
}

// 아이디 유효성 검사
function validateID() {
    var userid = document.frm.userid.value;
    if (userid === "") {
        alert("아이디를 입력하세요.");
        document.frm.userid.focus();
        return false;
    }

    if(document.frm.userid.value.length < 3){
        alert("아이디를 3글자 이상이어야 합니다.");
        frm.userid.select(); // 수정시 select()
        return false;
    }
    
    if(document.frm.userid.value != document.frm.reid.value){
        alert("아이디를 중복체크를 해주세요");
        frm.userid.focus();
        return false;
    }    
    return true;
}

// 아이디 중복체크
function idCheck2() {
    var userid = document.frm.userid.value;
    if (userid ==="") {
        alert("아이디를 입력해 주세요.");
        document.frm.userid.focus();
        return;
    }
}

function idCheck() {
    var userid = document.frm.userid.value;
    if (userid === "") {
        alert("아이디를 입력해 주세요.");
        document.frm.userid.focus();
        return;
    }
    var leftPosition = (screen.width - 600) / 2; // 창의 가로 중앙 위치
    var topPosition = (screen.height - 400) / 2; //
    var url = "idCheck.do?userid=" + userid; // 비교 체크
     window.open(url, "blank1", "resizable=no,width=600,height=400,left=" + leftPosition + ",top=" + topPosition);
}

function idok(){
    if(document.frm.userid.value==""){
        alert("아이디를 입력해 주세요");
        document.form.userid.focus();
        return;
    }
   if(document.frm.userid.value != document.frm.reid.value){
    alert("체크한 값과 다릅니다. 중복체크를 다시 해주세요");
    document.form.userid.focus();                 
    return;
    }


    opener.frm.userid.value=document.frm.userid.value;
    opener.frm.reid.value=document.frm.userid.value;
    self.close();
}

// 이름 유효성 검사
function validateName() {
    var name = document.frm.name.value;
    var containsNumber = /\d/.test(name); // 정규식을 사용하여 숫자 포함 여부 확인
    
    if (name === "") {
        alert("이름을 입력하세요.");
        document.frm.name.focus();
        return false;
    }

    if (containsNumber) {
        alert("이름에 숫자를 포함할 수 없습니다.");
        document.frm.name.focus();
        return false;
    }

    return true;
}

// 비밀번호 유효성 검사
function validatePassword() {
    var password = document.frm.pwd.value;
    if (password === "") {
        alert("비밀번호를 입력하세요.");
        document.frm.pwd.focus();
        return false;
    }
    // 비밀번호 유효성 검사 추가

    return true;
}

function validateBirthday() {
    var inputDate = document.getElementsByName("birthdate")[0].value;
    var datePattern = /^\d{8}$/; // yyyymmdd 형식의 정규표현식

    // 입력된 값이 올바른 형식인지 확인
    if (!datePattern.test(inputDate)) {
        alert("생년월일을 올바른 형식으로 입력해주세요. (YYYY-MM-DD)");
        return false;
    }

    var inputYear = parseInt(inputDate.substring(0, 4)); // 생년 추출
    var inputMonth = parseInt(inputDate.substring(4, 6)); // 월 추출
    var inputDay = parseInt(inputDate.substring(6, 8)); // 일 추출

    // 월과 일이 올바른 범위에 있는지 확인
    if (inputMonth < 1 || inputMonth > 12 || inputDay < 1 || inputDay > 31) {
        alert("올바른 생년월일을 입력해주세요.");
        return false;
    }

    // 현재 날짜 가져오기
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear(); // yyyy 형태로 현재 연도 가져오기
    var currentMonth = currentDate.getMonth() + 1; // 현재 월 가져오기
    var currentDay = currentDate.getDate(); // 현재 일 가져오기

    // 생일이 지났는지 여부 확인
    if (currentMonth < inputMonth || (currentMonth === inputMonth && currentDay < inputDay)) {
        currentYear--; // 아직 생일이 지나지 않았으면 현재 연도에서 1을 빼줌
    }

    var age = currentYear - inputYear; // 나이 계산

    // 나이가 19세 미만인 경우 알림 띄우기
    if (age < 19) {
        alert("19세 미만은 가입할 수 없습니다.");
        return false;
    }

    return true;
}

// 이메일 유효성 검사
function validateEmail() {
    var email = document.frm.email.value;
    
    // 이메일이 빈 문자열인지 확인
    if (email === "") {
        alert("이메일을 입력해주세요.");
        frm.email.focus();
        return false;
    }
    
    // 이메일 형식을 검증
    if (!validateEmailFormat(email)) {
        alert("올바른 이메일 형식이 아닙니다.");
        frm.email.focus();
        return false;
    }
    
    return true;
}

   function validateEmail2() {
   if(document.frm.email.value != document.frm.reid2.value){
        alert("이메일을 중복체크를 해주세요");
        frm.userid.focus();
        return false;
    } 
    return true;
}
// 이메일 중복체크
function emailCheck() {
   var email = document.frm.email.value;
    if (!validateEmail()) {
        return false;
    }
    var leftPosition = (screen.width - 600) / 2; // 창의 가로 중앙 위치
    var topPosition = (screen.height - 400) / 2; // 창의 세로 중앙 위치
    var url="emailCheck.do?email="+ email;
    window.open(url, "blank1", "resizable=no,width=600,height=400,left=" + leftPosition + ",top=" + topPosition);
}

function emailCheck2() {
    if (!validateEmail()) {
        return false;
    }
}

function emailok(){
    if (!validateEmail()) {
        return false;
    }
     if(document.frm.email.value != document.frm.reid2.value){
         alert("체크한 값과 다릅니다. 중복체크를 다시 해주세요");
      document.form.userid.focus();                 
      return;
      }

    
    
    opener.frm.email.value=document.frm.email.value;
    opener.frm.reid2.value=document.frm.email.value;
    self.close();
}

// 이메일 형식 검사
function validateEmailFormat(email) {
    var pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return pattern.test(email);
}

function joinCheck2() {
	if(document.frm.name.value==""){
		alert("이름을 입력하세요");
		frm.name.focus();
		return false;
	}
	if(document.frm.pwd.value==""){
		alert("pw를 입력하세요");
		frm.pwd.focus();
		return false;
	}
}



function cancel() {
  window.history.back();
  
 
}