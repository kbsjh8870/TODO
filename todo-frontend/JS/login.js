// 로그인 버튼
document.querySelector(".btn-login").addEventListener("click", () => {
  const name = document.getElementById("login-name").value.trim();
  const password = document.getElementById("login-password").value.trim();

  if (!name) {
    alert("이름을 입력해주세요!");
    document.getElementById("login-name").focus();
    return;
  }

  if (!password) {
    alert("비밀번호를 입력해주세요!");
    document.getElementById("login-password").focus();
    return;
  }

  // TODO: Spring -> fetch로 로그인 요청
  alert("로그인 준비중");
});

// 회원가입 버튼
document.querySelector(".btn-register").addEventListener("click", () => {
  // TODO: 회원가입 페이지로 이동
  alert("회원가입 준비중");
});

// 엔터키로 로그인
document.addEventListener("keydown", (e) => {
  if (e.key === "Enter") document.querySelector(".btn-login").click();
});
