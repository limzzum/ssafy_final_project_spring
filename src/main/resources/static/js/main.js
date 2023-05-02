
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
    console.log("search")
    let sido = document.getElementById("search-area").value
    let type = document.getElementById("search-content-id").value
    let keyword = document.getElementById("search-keyword").value
    console.log(sido);
    console.log(type);
    console.log(keyword);
    if(!sido || !type || !keyword){
        alert("null값 안돼요");
        return;
    }
    let config = {
        method:"POST",
        headers:{
            "Content-Type": "application/json"
        },
        body:JSON.stringify({
            "sido":sido.toString(),
            "type":type.toString(),
            "keyword":keyword.toString()
        })
        };
    let response = await fetch("/api/place/list", config);
    let json = await response.json();
    let result = json.result
    console.log(result);
    load([`themesearch`,`themetrip`]);
    let tripList = document.querySelector("#trip-list").innerHTML
    result.forEach(place =>{
        let trip = `<tr>
                    <td><img src="${place.image}" alt="" height="100px" onerror="this.src=/img/김민섭.PNG"/></td>
                    <td>${place.title}</td>
                    <td>${place.addr1}</td>
                    <td>${place.lat}</td>
                    <td>${place.lng}</td>
                    </tr>`
        tripList += trip;
    })
    document.querySelector("#trip-list").innerHTML = tripList;

}