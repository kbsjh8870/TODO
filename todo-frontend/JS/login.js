// 로그인 버튼
document.querySelector(".btn-login").addEventListener("click", async () => {
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

  // 로그인 요청
  const res = await fetch("http://localhost:8080/api/users/login", {
    method: "post",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, password }),
  });

  const user = await res.json();

  if (user && user.id) {
    window.location.href = "index.html";
  } else {
    alert("이름 또는 비밀번호가 틀렸습니다.");
  }
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
