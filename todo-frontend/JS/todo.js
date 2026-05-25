// let todos = [
//   { id: 1, content: "JAVA 복습", categoryId: 3, isDone: false },
//   { id: 2, content: "JS 복습", categoryId: 3, isDone: true },
//   { id: 3, content: "운동하기", categoryId: 2, isDone: false },
//   { id: 4, content: "미니프로젝트 코드작성", categoryId: 1, isDone: true },
// ];

function getCookie(name) {
  const cookies = document.cookie.split("; ");
  console.log(cookies);
  const cookie = cookies.find((c) => c.startsWith(name + "="));
  console.log(cookie);
  return cookie ? cookie.split("=")[1] : null;
}

const userId = getCookie("loginUser");
if (!userId) {
  window.location.href = "login.html";
}

async function loadUserName() {
  const res = await fetch(`http://localhost:8080/api/users/${userId}`);
  const user = await res.json();
  document.getElementById("nav-username").textContent = user.name;
}

loadUserName();

document.getElementById("btn-logout").addEventListener("click", async (e) => {
  await fetch("http://localhost:8080/api/users/logout", {
    method: "get",
    credentials: "include",
  });
  window.location.href = "login.html";
});

let todos = [];
let nextId = todos.length + 1;

async function init() {
  loadCategories();
  loadTodos();
}
init();

async function loadTodos() {
  const res = await fetch("http://localhost:8080/api/todos");
  todos = await res.json();

  renderCount();
  renderCardList();
}

let categories = [];

async function loadCategories() {
  const res = await fetch("http://localhost:8080/api/categories");
  categories = await res.json();
  console.log(categories);
}

// 할일 현황 카운트 렌더링
function renderCount() {
  const all = todos.length;
  const done = todos.filter((t) => t.isDone).length;
  const undone = all - done;

  document.querySelectorAll(".count")[0].textContent = all;
  document.querySelectorAll(".count")[1].textContent = done;
  document.querySelectorAll(".count")[2].textContent = undone;
}

renderCount();

// 할일 카드 리스트 렌더링
function renderCardList() {
  const allRecent = [...todos].reverse().slice(0, 3);
  const doneRecent = [...todos]
    .filter((l) => l.isDone)
    .reverse()
    .slice(0, 3);
  const undoneRecent = [...todos]
    .filter((l) => !l.isDone)
    .reverse()
    .slice(0, 3);

  const cardRights = document.querySelectorAll(".card-right");

  cardRights[0].innerHTML = allRecent
    .map((l) => `<span>${l.content}</span>`)
    .join("");

  cardRights[1].innerHTML = doneRecent
    .map((l) => `<span>${l.content}</span>`)
    .join("");

  cardRights[2].innerHTML = undoneRecent
    .map((l) => `<span>${l.content}</span>`)
    .join("");
}

renderCardList();

// 닫기 버튼
document.querySelectorAll(".modal-close").forEach((btn) => {
  btn.addEventListener("click", () => {
    document.querySelectorAll(".modal-overlay").forEach((modal) => {
      modal.style.display = "none";
      document.getElementById("todo-input").value = "";
    });
  });
});

// 오버레이 클릭시 닫기
document.querySelectorAll(".modal-overlay").forEach((overlay) => {
  overlay.addEventListener("click", (e) => {
    if (e.target === overlay) {
      overlay.style.display = "none";
      document.getElementById("todo-input").value = "";
    }
  });
});

// 카드 클릭 이벤트
document.querySelector(".card-all").addEventListener("click", () => {
  document.querySelector(".modal-title").textContent = "전체 할일";
  document.getElementById("list-modal").style.display = "flex";
  renderTodoList("all");
});
document.querySelector(".card-done").addEventListener("click", () => {
  document.querySelector(".modal-title").textContent = "완료";
  document.getElementById("list-modal").style.display = "flex";
  renderTodoList("done");
});
document.querySelector(".card-undone").addEventListener("click", () => {
  document.querySelector(".modal-title").textContent = "미완료";
  document.getElementById("list-modal").style.display = "flex";
  renderTodoList("undone");
});

// 할일 추가 버튼
document.querySelector(".btn-add").addEventListener("click", () => {
  renderCategorySelect();
  document.getElementById("add-modal").style.display = "flex";
});

// todo list 렌더링
function renderTodoList(filter) {
  let filtered;

  if (filter === "all") filtered = todos;
  else if (filter === "done") filtered = todos.filter((t) => t.isDone);
  else if (filter === "undone") filtered = todos.filter((t) => !t.isDone);

  const tbody = document.getElementById("todo-tbody");
  tbody.innerHTML = "";

  filtered.forEach((todo) => {
    const tr = document.createElement("tr");

    const tdId = document.createElement("td");
    tdId.textContent = todo.id;

    const tdContent = document.createElement("td");
    tdContent.textContent = todo.content;

    const tdCategory = document.createElement("td");
    //console.log(todo);
    const category = categories.find((c) => c.id === todo.category_id);
    tdCategory.textContent = category ? category.category : "없음";

    const tdDone = document.createElement("td");
    tdDone.textContent = todo.isDone ? "☑" : "☐";
    tdDone.style.cursor = "pointer";

    tdDone.addEventListener("click", () => {
      todo.isDone = !todo.isDone;
      tdDone.textContent = todo.isDone ? "☑" : "☐";
      renderCount();
      renderCardList();
    });

    const tdEdit = document.createElement("td");
    const editBtn = document.createElement("button");
    editBtn.textContent = "수정";
    editBtn.className = "btn-icon edit";

    editBtn.addEventListener("click", (e) => {
      if (editBtn.textContent === "저장") {
        const newContent = tdContent.querySelector("input").value.trim();

        if (!newContent) return;

        todo.content = newContent;
        editBtn.textContent = "수정";

        const title = document.querySelector(".modal-title").textContent;
        if (title === "전체 할일") renderTodoList("all");
        else if (title === "완료") renderTodoList("done");
        else if (title === "미완료") renderTodoList("undone");
      }

      const currentContent = tdContent.textContent;
      tdContent.innerHTML = "";
      const editInput = document.createElement("input");
      editInput.value = currentContent;
      tdContent.appendChild(editInput);
      editInput.focus();
      editBtn.textContent = "저장";

      editInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") editBtn.click();
      });
    });

    tdEdit.appendChild(editBtn);

    const tdDelete = document.createElement("td");
    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "삭제";
    deleteBtn.className = "btn-icon delete";

    deleteBtn.addEventListener("click", (e) => {
      todos = todos.filter((t) => t.id !== todo.id);
      renderCount();
      renderCardList();

      const title = document.querySelector(".modal-title").textContent;
      if (title === "전체 할일") renderTodoList("all");
      else if (title === "완료") renderTodoList("done");
      else if (title === "미완료") renderTodoList("undone");
    });

    tdDelete.appendChild(deleteBtn);

    tr.append(tdId, tdContent, tdCategory, tdDone, tdEdit, tdDelete);

    tbody.appendChild(tr);
  });
}

// 할일 추가에서 카테고리 목록 렌더링
function renderCategorySelect() {
  const select = document.getElementById("category-select");

  categories.forEach((category) => {
    const option = document.createElement("option");
    option.value = category.id;
    option.textContent = category.category;

    select.appendChild(option);
  });
}

renderCategorySelect();

// 할일 추가 - 등록
document
  .querySelector(".btn-add-confirm")
  .addEventListener("click", async (e) => {
    const input = document.getElementById("todo-input");
    const selctCategoryId = document.getElementById("category-select").value;

    const content = input.value.trim();

    if (!content) {
      alert("할일 내용을 입력하세요");
      input.focus();
      return;
    }

    if (!selctCategoryId) {
      alert("카테고리를 선택해주세요");
      return;
    }

    /* todos.push({
      id: nextId++,
      content,
      categoryId: Number(selctCategoryId),
      isDone: false,
    }); */

    await fetch("http://localhost:8080/api/todos", {
      method: "post",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        user_id: 1,
        content,
        category_id: Number(selctCategoryId),
        isDone: false,
      }),
    });

    await loadTodos();

    document.getElementById("add-modal").style.display = "none";
  });
