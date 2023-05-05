function registUser() {
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
        document.querySelector("#loginModal")["area-hidden"]="true";
        document.querySelector("#navbar-logout").style="display:none; white-space:nowrap;";
        document.querySelector("#navbar-login").style="display:flex; white-space:nowrap;";
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