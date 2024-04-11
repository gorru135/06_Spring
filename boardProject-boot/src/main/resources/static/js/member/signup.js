// **** 회원 가입 유효성 검사 ****

// 필수 입력 항목의 유효성 검사 여부를 체크하기 위한 객체 
// - true == 해당 항목은 유효한 형식으로 작성됨
// - false == 해당 항목은 유효하지 않은 형식으로 작성됨 
const checkobj={
    "memberEmail" : false,
    "memberPw" : false,
    "memberPwConfirm" : false,
    "memberNickname" : false,
    "memberTel" : false,
    "authkey" : false
};

// ----------------------------------------------------

/* 이메일 유효성 검사 */

// 1) 이메일 유효성 검사에 사용될 요소 얻어오기 
const memberEmail = document.querySelector("#memberEmail");
const emailMessage = document.querySelector("#emailMessage");

// 2) 이메일이 입력(input) 될 때 마다 유효성 검사 수행 
memberEmail.addEventListener("input", e => {

    // 이메일 인증 후 이메일이 변경된 경우 
    checkobj.authkey = false;
    document.querySelector("#authKeyMessage").innerText = "";

    // 작성된 이메일 값 얻어오기 
    const inputEmail = e.target.value;

    //console.log(inputEmail);

    // 3) 입력된 이메일이 없을 경우 
    if(inputEmail.trim().length === 0){
        emailMessage.innerText="메일을 받을 수 있는 이메일을 입력해주세요"
        
        // 메시지에 색상을 추가하는 클래스 모두 제거 
        emailMessage.classList.remove('confirm','error');

        // 이메일 유효성 검사 여부를 false 변경 
        checkobj.memberEmail = false;

        // 잘못 입력한 띄어쓰기가 있을 경우 없앰
        memberEmail.value="";

        return;
    }

    // 4) 입력된 이메일이 있을 경우 정규직 검사 
    //    (알맞은 형태로 작성했는지 검사)
    const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // 입력 받은 이메일이 정규식과 일치하지 않는 경우 
    // (알맞은 이메일 형태가 아닌 경우)
    if( !regExp.test(inputEmail) ){
        emailMessage.innerText="알맞은 이메일 형식으로 작성해주세요."
        emailMessage.classList.add('error'); // 글자를 빨간색으로 변경
        emailMessage.classList.remove('confirm'); // 초록색 제거 
        checkobj.memberEmail = false; // 유효하지 않은 이메일임을 기록 
        return;

    }

    // 5) 유효한 이메일 형식인 경우 중복 검사 
    // 비동기(ajax)
    fetch("/member/checkEmail?memberEmail="+ inputEmail)
    .then(resp => resp.text() )
    .then( count => {

        if(count == 1){
            emailMessage.innerText = "이미 사용중인 이메일 입니다.";
            emailMessage.classList.add('error');
            emailMessage.classList.remove('confirm');
            checkobj.memberEmail = false;
            return;
        } 

        // 중복이 x 경우 
        emailMessage.innerText = "사용 가능한 이메일 입니다.";
        emailMessage.classList.add('confirm');
        emailMessage.classList.remove('error');
        checkobj.memberEmail = true;

    })
    .catch(error =>{
        // fetch() 수행 중 예외 발생 시 처리
        console.log(error); // 발생한 예외 출력 
    });
    
});

// -----------------------------------------------------------------
/* 이메일 인증 */

// 인증번호 받기 버튼 

const sendAuthKeyBtn = document.querySelector("#sendAuthKeyBtn");

// 인증번호 입력 input
const authkey = document.querySelector("#authkey");

//인증번호 입력 후 확인 버튼
const checkAuthKeyBtn = document.querySelector("#checkAuthKeyBtn");

// 인증번호 관련 메시지 출력 span
const authKeyMessage = document.querySelector("#authKeyMessage");

let authTimer; // 타이머 역활을 할 setInterval을 지정할 변수 

const initMin = 4; // 타이머 초기값 (분)
const initSec = 59;
const initTime = "05:00";

// 실제 줄어드는 시간을 저장할 변수 
let min = initMin;
let sec = initSec;

// 인증번호 받기 버튼 클릭 시 
sendAuthKeyBtn.addEventListener("click",()=>{

     checkobj.authkey = false;
     authKeyMessage.innerText = "";

    // 중복되지 않은 유효한 이메일을 입혈한 경우가 아니라면

    if(! checkobj.memberEmail){
        alert("유효한 이메일 작성 후 클릭해 주세요");
        return;
    }

    // 클릭 시 타이머 숫자 소기화
    min = initMin;
    sec = initSec;

    // 어던 동작중인 인터벌 구헌 
    clearInterval(authTimer);
    // *************************************************
    
    // 비동기로 서버에서 메일 보내기
    fetch("/email/signup", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : memberEmail.value
    })



    // *************************************************

    // 메일은 비동기로 서버에서 보내라고 하고 
    // 화면에서는 타이머 시작하기 

    authKeyMessage.innerText = initTime;
    authKeyMessage.classList.remove("confirm","error");

    alert("인증번호가 발송되었습니다.");

    // setInterval(함수, 지연시간(ms))
    // - 지연시간(ms) 만큼 시간이 지날 때 마다 함수 수행

    // cleatInternal(Interval이 저장된 변수)
    // - 매개변수로 전달받음 interval을 멈춤 

    // 인증 시간 출력(1초 마다 동작)
    authTimer = setInterval(() => {

        authKeyMessage.innerText = `${addZero(min)}:${addZero(sec)}`

        // 0분 0초인 경우 ("00:00" 출력 후)
        if(min == 0 && sec ==0){
            checkobj.authkey = false; // 인증 못함
            clearInterval(authTimer); // interval 멈춤 
            authKeyMessage.classList.add("error");
            authKeyMessage.classList.remove("confirm");
            return;
        }

        // 0초인 경우(0초를 출력한 후)
        
        if(sec == 0){
            sec = 60;
            min --;
        }

        sec --; // 1초 감소 

    }, 1000); // 1초 지연시간
});

// 전달 받은 숫자가 10 미만인 경우(한자리) 앞에 0 붙여서 반환 
function addZero(number){
    if(number < 10) return "0" + number;
    else return number;
}

