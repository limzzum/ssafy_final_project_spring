var worldcups = [], selected = [];
var round = 8, step = 1;
async function worldcup(){
    worldcups = [];
    selected = [];
    round = 8;
    step = 1;
    let config = {
        method : "get",
    };
    let result = await fetch("/api/worldcup",config);
    let json = await result.json();
    console.log(json);
    json.result.forEach(item=>{
        worldcups.push({
            id:item.id,
            title:item.title,
            img:item.img,
            content:item.content,
            wins:item.wins,
        })
    });
    show();
    load([`worldcup`]);
}

async function worldcupResult(id){
    document.querySelector("#worldcuptitle").innerHTML=`🛫나의 마음 속 1등 관광지는 바로 ${worldcups[0].title }입니다!!!🛫`;
    document.querySelector("#sel1").innerHTML =
        `<img src="/img/${worldcups[0].img}" class="align-middle img-className rounded-3" />
        <h2 style="text-align: center;" >${worldcups[0].content}</h2>`
    let config = {
        method : "get",
    };
    let result = await fetch("/api/worldcup/"+id,config);
    let json = await result.json();
    console.log(json);
    let others = document.querySelector("#others");
    others.innerHTML = "";
    let totalWin = 0;
    for(let i of json.result){
        console.log(i.wins);
        totalWin+=i.wins;
    }
    json.result.forEach(item=>{
        others.innerHTML += `<tr>
        <td className="align-middle"><img src="/img/${item.img }"
                                          className="img-fluid rounded-3 img-thumbnail" style="width:100px;"/></td>
        <td className="align-middle">${item.title}</td>
        <td className="align-middle">${item.content}</td>
        <td className="align-middle">${item.wins}</td>
        <td className="align-middle"><div class="progress">
        <div class="progress-bar" role="progressbar" aria-valuenow="${(100*item.wins/totalWin).toFixed(0)}" aria-valuemin="0" aria-valuemax="100">${(100*item.wins/totalWin).toFixed(0)}%</div>
        </div>
        </td>
    </tr>`;
    });
    load([`worldcup-result`]);
}
function shuffle(array) {
    for (let index = array.length - 1; index > 0; index--) {
        const randomPosition = Math.floor(Math.random() * (index + 1));
        const temporary = array[index];
        array[index] = array[randomPosition];
        array[randomPosition] = temporary;
    }
}
function show(){
    if(worldcups.length == 0){
        if(round>1){
            shuffle(selected);
            worldcups = selected;
            selected = [];
            round/=2;
            step = 1;
        }
        if(round==1){
            worldcupResult(worldcups[0].id);
            return;
        }
    }
    document.getElementById("worldtitle").innerHTML= (round==2)?`국내 관광지 월드컵 결승`:`국내 관광지 월드컵 ${round }강 (${step++ }/${round/2 })`;
    document.getElementById("s1").innerHTML= `
				<img src="/img/${worldcups[0].img }" class="img-fluid rounded-3" id="img1" />
				<h2 style="text-align: center;" id="text1">${worldcups[0].content }</h2>`;
    document.getElementById("s2").innerHTML= `
				<img src="/img/${worldcups[1].img }" class="img-fluid rounded-3" id="img1" />
				<h2 style="text-align: center;" id="text1">${worldcups[1].content }</h2>`;
}
function select1(){
    selected.push(worldcups.shift());
    worldcups.shift();
    show();
}
function select2(){
    worldcups.shift();
    selected.push(worldcups.shift());
    show();
}