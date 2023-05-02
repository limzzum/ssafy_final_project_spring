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

async function board(type, page) {
    load([`boardview`]);
    let config = {
        method: "get"
    };
    let result = await fetch("/api/board/" + type + "/" + page, config);
    let json = await result.json();
    let resDiv = document.querySelector("#boardRes");
    let text = "";
    console.log(json);
    json.result.forEach( item => {
        text += `<tr><td>${item.postId}</td><td>${item.title}</td><td>${item.userNo}</td><td>${item.hits}</td></tr>`;
        console.log(`<tr><td>${item.postId}</td><td>${item.title}</td><td>${item.userNo}</td><td>${item.hits}</td></tr>`);
    });
    resDiv.innerHTML = `<h3>` + type + `</h3><table class="table table-hover"><thead><tr><th>번호</th><th>제목</th><th>작성자</th><th>조회수</th></tr></thead><tbody>`+text+`</tbody></table>`;
}

async function searchPlace() {
    let sido = document.querySelector("#search-area").value();
    let type = document.querySelector("#search-content-id").value();
    let keyword = document.querySelector("#search-keyword").value();
    if (!sido || !type || !keyword) {
        alert("null값 안돼요");
        return;
    }
    let config = {
        method: "POST",
        contentType: "application/json",
        body: {
            "sido": sido,
            "type": type,
            "keyword": keyword
        }
    };
    let response = await fetch("/api/place/list", config);
    let json = await response.json();
    console.log(json);
}