function taplist(e) {
  document.querySelectorAll(".tap").forEach(function (item) {
    item.classList.remove("active");
  });
  e.classList.add("active");
}

document.getElementById("userid").innerText = user.userid;
console.log(user);
document.getElementById("nickname").innerText = user.nickname;
document.getElementById("email").innerText = user.email;

function moveContent() {
  location.href = "boardContent.html";
}
