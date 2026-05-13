let todos = [
  { id: 1, content: "JAVA 복습", categoryId: 3, isDone: false },
  { id: 2, content: "JS 복습", categoryId: 3, isDone: true },
  { id: 3, content: "운동하기", categoryId: 2, isDone: false },
  { id: 4, content: "미니프로젝트 코드작성", categoryId: 1, isDone: true },
];
let nextId = todos.length + 1;

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
