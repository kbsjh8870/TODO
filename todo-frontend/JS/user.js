// 더미 유저
let users = [
  { id: 1, name: "홍길동" },
  { id: 2, name: "김철수" },
];
let nextId = users.length + 1;

// 유저 테이블 렌더링
function renderUsers() {
  const tbody = document.querySelector("tbody");
  tbody.innerHTML = "";

  users.forEach((user) => {
    const tr = document.createElement("tr");

    const tdId = document.createElement("td");
    tdId.textContent = user.id;

    const tdName = document.createElement("td");
    tdName.textContent = user.name;

    const tdEdit = document.createElement("td");
    const editBtn = document.createElement("button");
    editBtn.className = "btn-icon edit";
    editBtn.textContent = "수정";
    tdEdit.appendChild(editBtn);

    const toDelete = document.createElement("td");
    const deleteBtn = document.createElement("button");
    deleteBtn.className = "btn-icon delete";
    deleteBtn.textContent = "삭제";
    toDelete.appendChild(deleteBtn);

    tr.append(tdId, tdName, tdEdit, toDelete);

    // 유저 삭제
    tr.querySelector(".btn-icon.delete").addEventListener("click", (e) => {
      users = users.filter((us) => us.id !== user.id);

      renderUsers();
    });

    // 유저 수정
    tr.querySelector(".btn-icon.edit").addEventListener("click", (e) => {
      const tdName = tr.querySelectorAll("td")[1];
      const currentName = tdName.textContent;
      const editBtn = tr.querySelector(".btn-icon.edit");

      if (editBtn.textContent === "저장") {
        const newName = tdName.querySelector("input").value.trim();
        if (!newName) return;
        user.name = newName;
        editBtn.textContent = "수정";
        renderUsers();
        return;
      }

      tdName.innerHTML = `<input type='text' value='${currentName}'>`;

      const editInput = tdName.querySelector("input");
      editInput.focus();
      editBtn.textContent = "저장";

      editInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") editBtn.click();
      });
    });

    tbody.appendChild(tr);
  });
}
renderUsers();

// 유저 등록 - 버튼 클릭
document.querySelector(".btn-icon.add").addEventListener("click", (e) => {
  const inputText = document.querySelector("input");
  const inputName = inputText.value.trim();

  if (!inputName) {
    alert("이름을 입력해주세요!");
    inputText.focus();
    return;
  }

  users.push({ id: nextId++, name: inputName });

  inputText.value = "";

  renderUsers();
});

// 유저 등록 - 입력 엔터
document.querySelector("input").addEventListener("keydown", (e) => {
  if (e.key === "Enter") {
    document.querySelector(".btn-icon.add").click();
  }
});
