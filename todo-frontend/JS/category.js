let categories = [
  { id: 1, category: "업무" },
  { id: 2, category: "개인" },
  { id: 3, category: "공부" },
  { id: 4, category: "약속" },
  { id: 5, category: "기타" },
];
let nextId = categories.length + 1;

function renderCategories() {
  const tbody = document.querySelector("tbody");
  tbody.innerHTML = "";

  categories.forEach((cat) => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
            <td>${cat.id}</td>
            <td>${cat.category}</td>
            <td><button class="btn-icon delete">삭제</button></td>
        `;

    tr.querySelector(".btn-icon.delete").addEventListener("click", () => {
      categories = categories.filter((c) => c.id !== cat.id);
      renderCategories();
    });

    tbody.appendChild(tr);
  });
}
renderCategories();

document.querySelector(".btn-icon.add").addEventListener("click", () => {
  const inputText = document.querySelector("input");
  const inputName = inputText.value.trim();

  if (!inputName) {
    alert("카테고리명을 입력해주세요!");
    inputText.focus();
    return;
  }

  categories.push({ id: nextId++, category: inputName });
  inputText.value = "";
  renderCategories();
});

document.querySelector("input").addEventListener("keydown", (e) => {
  if (e.key === "Enter") document.querySelector(".btn-icon.add").click();
});
