async function notice(){
    bootstrap.Tab.getInstance(document.querySelector("#board1-list")).show();
    await board('notice',1);
}

async function board(type, page) {
    load([`boardview`]);
    let config = {
        method: "get"
    };
    console.log(type)
    let result = await fetch("/api/board/" + type + "/" + page, config);
    let json = await result.json();
    let resDiv = document.querySelector("#boardRes");
    let text = `<table class="table table-hover"><thead><tr><th>게시글 번호</th><th>제목</th><th>작성자</th><th>조회수</th></tr></thead><tbody>`;
    console.log(json);
    json.result.forEach( item => {
        text += `<tr onclick="return post(${item.postId},${type},${page})"><td>${item.postId}</td><td>${item.title}</td><td>${item.userName}</td><td>${item.hits}</td></tr>`;
    });
    text += `</tbody></table>`;

    text += pageHtml(`return board('${type}'`,`);`,page,json.pages);

    resDiv.innerHTML = text;
}

async function post(id,condition,page){
    load([`postview`]);
}
