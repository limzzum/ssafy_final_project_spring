async function registUser() {
    let userId = document.querySelector("#regId").value;
    let userName = document.querySelector("#regName").value;
    let userPwd = document.querySelector("#regpw").value;
    let email = document.querySelector("#regEmail").value;
    if(!userId){
        alert("아이디를 입력하세요");
        return;
    }
    if(!userName){
        alert("이름을 입력하세요");
        return;
    }
    if(!userPwd){
        alert("비밀번호를 입력하세요");
        return;
    }
    if(!email){
        alert("이메일을 입력하세요");
        return;
    }
    let config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "userId": userId.toString(),
            "userName": userName.toString(),
            "userPwd": userPwd.toString(),
            "email": email.toString(),
        })
    };
    let response = await fetch("/api/user/regist", config);
    let json = await response.json();
    console.log(json);
    var user = json.user;
    console.log(user);
    if(json.msg){
        alert(json.msg);
    }
}

async function updateUser() {
    // let pw = document.querySelector("#newPw");
    // let check = document.querySelector("#newPwCheck");
    // if (pw.value == check.value) document.querySelector("#userUpdateForm").submit();
    // else alert("새 비밀번호가 일치하지 않습니다");
}

async function login(){
    let userId = document.querySelector("#loginId").value;
    let userPwd = document.querySelector("#loginPwd").value;
    console.log(userId,userPwd);
    let config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "userId": userId.toString(),
            "userPwd": userPwd.toString(),
        })
    };
    let response = await fetch("/api/user/login", config);
    let json = await response.json();
    console.log(json);
    var user = json.user;
    console.log(user);
    if(json.msg){
        alert(json.msg);
    }
    if(user!=null){
        sessionStorage.setItem("islogin","true");
        sessionStorage.setItem("userNo",user.userNo);
        sessionStorage.setItem("userId",user.userId);
        sessionStorage.setItem("userName",user.userName);
        sessionStorage.setItem("email",user.email);
        document.querySelector("#loginModal")["area-hidden"]="true";
        document.querySelector("#navbar-logout").style="display:none; white-space:nowrap;";
        document.querySelector("#navbar-login").style="display:flex; white-space:nowrap;";
        document.querySelector("#mypageId").innerHTML = sessionStorage.getItem("userId");
        document.querySelector("#mypageName").innerHTML = sessionStorage.getItem("userName");
        document.querySelector("#mypageEmail").innerHTML = sessionStorage.getItem("email");
    }
}

async function logout(){
    let config = {
        method: "GET",
    };
    let response = await fetch("/api/user/logout", config);
    let json = await response.json();
    console.log(json);
    console.log(json.msg);
    alert(json.msg);
    document.querySelector("#navbar-login").style="display:none; white-space:nowrap;";
    document.querySelector("#navbar-logout").style="display:flex; white-space:nowrap;";
    sessionStorage.clear();
}

async function mypage(){
    load([`mypage`]);
}