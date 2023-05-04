

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
        text += `<tr><td>${item.postId}</td><td>${item.title}</td><td>${item.userName}</td><td>${item.hits}</td></tr>`;
    });
    text += `</tbody></table><nav><ul class="pagination justify-content-center">`;
    text += `<li class="page-item"><a class="page-link" onclick="return board('${type}',1);">처음</a></li>`;
    if(Math.max(1,page-5)!=1){
        text += `<li class="page-item"><a class="page-link disabled">...</a></li>`;
    }
    for(let i=Math.max(1,page-5);i<page;i++){
        text += `<li class="page-item"><a class="page-link" onclick="return board('${type}',${i});">${i}</a></li>`;
    }
    text += `<li class="page-item"><a class="page-link active" onclick="return board('${type}',${page});">${page}</a></li>`;
    for(let i=page+1;i<=Math.min(json.pages,page+5);i++){
        text += `<li class="page-item"><a class="page-link" onclick="return board('${type}',${i});">${i}</a></li>`;
    }
    if(Math.min(json.pages,page+5)!=json.pages){
        text += `<li class="page-item"><a class="page-link disabled">...</a></li>`;
    }
    text += `<li class="page-item"><a class="page-link" onclick="return board('${type}',${json.pages});">끝</a></li>`;
    text+=`</ul></nav>`
    resDiv.innerHTML = text;
}
