
function load(selected) {
    let contents = document.querySelectorAll("#maindiv>div");
    contents.forEach(item => {
        item.style = "display:none";
    });
    selected.forEach(item=>{
        document.querySelector("#"+item).style = "display:inline"
    })
}


function taplist(e) {
    document.querySelectorAll(".tap").forEach(function (item) {
        item.classList.remove("active");
    });
    e.classList.add("active");
}

function registUser(){
    let pw = document.querySelector("#newPw");
    let check = document.querySelector("#newPwCheck");
    if(pw.value==check.value) document.querySelector("#userUpdateForm").submit();
    else alert("새 비밀번호가 일치하지 않습니다");
}


//관광지 search

async function searchPlace(){
    let sido = document.querySelector("#search-area").value();
    let type = document.querySelector("#search-content-id").value();
    let keyword = document.querySelector("#search-keyword").value();
    if(!sido || !type || !keyword){
        alert("null값 안돼요");
        return;
    }
    let config = {
        method:"POST",
        contentType:"application/json",
        body:{
            "sido":sido,
            "type":type,
            "keyword":keyword
        }
        };
    let response = await fetch("/api/place/list", config);
    let json = await response.json();
    console.log(json);

}