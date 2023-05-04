function index(){

    getRegion();
    getContent();
    load([`themesearch`,`maincarousel`]);
}

function load(selected) {
    let contents = document.querySelectorAll("#maindiv>div");
    contents.forEach(item => {
        item.style = "display:none";
    });
    selected.forEach(item => {
        document.querySelector("#" + item).style = "display:inline"
    })

}

function taplist(e) {
    document.querySelectorAll(".tap").forEach(function (item) {
        item.classList.remove("active");
    });
    e.classList.add("active");
}

function registUser() {
    let pw = document.querySelector("#newPw");
    let check = document.querySelector("#newPwCheck");
    if (pw.value == check.value) document.querySelector("#userUpdateForm").submit();
    else alert("새 비밀번호가 일치하지 않습니다");
}

function recommend(){
    load([`recommend`]);
}



// prefix + ", page" + suffix 를 onclick 메소드로 가지는 페이지 html 리턴
function pageHtml(prefix, suffix, page, pages){
    let text = `<nav><ul class="pagination justify-content-center"><li class="page-item"><a class="page-link" style="width: 55px" onclick="${prefix} ,1 ${suffix}">처음</a></li>`;
    if(Math.max(1,page-5)!=1){
        text += `<li class="page-item"><a class="page-link disabled">...</a></li>`;
    }
    for(let i=Math.max(1,page-5);i<page;i++){
        text += `<li class="page-item"><a class="page-link" onclick="${prefix} ,${i} ${suffix}">${i}</a></li>`;
    }
    text += `<li class="page-item"><a class="page-link active" onclick="${prefix} ,${page}${suffix}">${page}</a></li>`;
    for(let i=page+1;i<=Math.min(pages,page+5);i++){
        text += `<li class="page-item"><a class="page-link" onclick="${prefix} ,${i} ${suffix}">${i}</a></li>`;
    }
    if(Math.min(pages,page+5)!=pages){
        text += `<li class="page-item"><a class="page-link disabled">...</a></li>`;
    }
    text += `<li class="page-item"><a class="page-link" onclick="${prefix} ,${pages} ${suffix}">끝</a></li>`;
    text += `</ul></nav>`
    return text;
}