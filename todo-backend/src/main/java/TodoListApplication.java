import DTO.CategoryDTO;
import DTO.TodoDTO;
import DTO.UserDTO;
import Service.CategoryService;
import Service.TodoService;
import Service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TodoListApplication {
    static Scanner sc = new Scanner(System.in);
    static TodoService todoService = new TodoService();
    static UserService userService = new UserService();
    static CategoryService categoryService = new CategoryService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. User Management");
            System.out.println("2. Category Management");
            System.out.println("3. Todo Management");
            System.out.println("0. Exit");
            System.out.print("Select: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> userMenu();
                case 2 -> categoryMenu();
                case 3 -> todoMenu();
                case 0 -> { System.out.println("Exit."); return; }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    // ========================
    // User Management
    // ========================
    static void userMenu() {
        while (true) {
            System.out.println("\n--- User Management ---");
            System.out.println("1. Find All Users");
            System.out.println("2. Create User");
            System.out.println("3. Modify User");
            System.out.println("4. Delete User");
            System.out.println("0. Back");
            System.out.print("Select: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> findAllUsers();
                case 2 -> createUser();
                case 3 -> modifyUser();
                case 4 -> deleteUser();
                case 0 -> { return; }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    static void findAllUsers() {
        List<UserDTO> users = userService.findAllUser();
        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        for (UserDTO u : users) {
            System.out.printf("[%d] %s%n", u.getId(), u.getName());
        }
    }

    static void createUser() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        UserDTO dto = new UserDTO();
        dto.setName(name);

        System.out.println(userService.createUser(dto) ? "Create success." : "Create failed.");
    }

    static void modifyUser() {
        System.out.print("User id to modify: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("New name: ");
        String name = sc.nextLine();

        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setName(name);

        System.out.println(userService.modifyUser(dto) ? "Modify success." : "Modify failed.");
    }

    static void deleteUser() {
        System.out.print("User id to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println(userService.deleteUser(id) ? "Delete success." : "Delete failed.");
    }

    // ========================
    // Category Management
    // ========================
    static void categoryMenu() {
        while (true) {
            System.out.println("\n--- Category Management ---");
            System.out.println("1. Find All Categories");
            System.out.println("2. Create Category");
            System.out.println("3. Delete Category");
            System.out.println("0. Back");
            System.out.print("Select: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> findAllCategories();
                case 2 -> createCategory();
                case 3 -> deleteCategory();
                case 0 -> { return; }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    static void findAllCategories() {
        List<CategoryDTO> categories = categoryService.findAllCategory();
        if (categories == null || categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }
        for (CategoryDTO c : categories) {
            System.out.printf("[%d] %s%n", c.getId(), c.getCategory());
        }
    }

    static void createCategory() {
        System.out.print("Category name: ");
        String name = sc.nextLine();

        CategoryDTO dto = new CategoryDTO();
        dto.setCategory(name);

        System.out.println(categoryService.createCategory(dto) ? "Create success." : "Create failed.");
    }

    static void deleteCategory() {
        System.out.print("Category name to delete: ");
        String name = sc.nextLine();
        System.out.println(categoryService.deleteCategory(name) ? "Delete success." : "Delete failed.");
    }

    // ========================
    // Todo Management
    // ========================
    static void todoMenu() {
        while (true) {
            System.out.println("\n--- Todo Management ---");
            System.out.println("1. Find All Todos");
            System.out.println("2. Create Todo");
            System.out.println("3. Modify Todo");
            System.out.println("4. Delete Todo");
            System.out.println("5. Toggle Done");
            System.out.println("6. Find By Date");
            System.out.println("0. Back");
            System.out.print("Select: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> findAllTodos();
                case 2 -> createTodo();
                case 3 -> modifyTodo();
                case 4 -> deleteTodo();
                case 5 -> toggleDone();
                case 6 -> findByDate();
                case 0 -> { return; }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    static void findAllTodos() {
        List<TodoDTO> todos = todoService.findAllTodo();
        if (todos == null || todos.isEmpty()) {
            System.out.println("No todos found.");
            return;
        }
        for (TodoDTO t : todos) {
            UserDTO user = userService.findUserById(t.getUser_id());
            CategoryDTO cat = categoryService.findCategoryById(t.getCategory_id());
            System.out.printf("[%d] %s | author: %s | category: %s | date: %s | done: %s%n",
                    t.getId(),
                    t.getContent(),
                    user != null ? user.getName() : "unknown",
                    cat != null ? cat.getCategory() : "unknown",
                    t.getUpload_date(),
                    t.isDone() ? "Y" : "N"
            );
        }
    }

    static void createTodo() {
        System.out.print("Content: ");
        String content = sc.nextLine();
        System.out.print("User id: ");
        int userId = Integer.parseInt(sc.nextLine());
        System.out.print("Category Name: ");
        String categoryName = sc.nextLine();

        CategoryDTO cat = categoryService.findCategoryByName(categoryName);
        if (cat == null) {
            System.out.println("Category not found.");
            return;
        }

        TodoDTO dto = new TodoDTO();
        dto.setContent(content);
        dto.setUser_id(userId);
        dto.setCategory_id(cat.getId());
        dto.setDone(false);

        System.out.println(todoService.createTodo(dto) ? "Create success." : "Create failed.");
    }

    static void modifyTodo() {
        System.out.print("Todo id to modify: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("New content: ");
        String content = sc.nextLine();
        System.out.print("New category id: ");
        int categoryId = Integer.parseInt(sc.nextLine());

        TodoDTO dto = new TodoDTO();
        dto.setId(id);
        dto.setContent(content);
        dto.setCategory_id(categoryId);

        System.out.println(todoService.modifyTodo(dto) ? "Modify success." : "Modify failed.");
    }

    static void deleteTodo() {
        System.out.print("Todo id to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println(todoService.deleteTodo(id) ? "Delete success." : "Delete failed.");
    }

    static void toggleDone() {
        System.out.print("Todo id to toggle: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("isDone (true/false): ");
        boolean isDone = Boolean.parseBoolean(sc.nextLine());
        System.out.println(todoService.toggleTodoDone(id, isDone) ? "Toggle success." : "Toggle failed.");
    }

    static void findByDate() {
        System.out.print("Date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        List<TodoDTO> todos = todoService.findTodoByDate(date);
        if (todos == null || todos.isEmpty()) {
            System.out.println("No todos on that date.");
            return;
        }
        for (TodoDTO t : todos) {
            System.out.printf("[%d] %s | done: %s%n", t.getId(), t.getContent(), t.isDone() ? "V" : "X");
        }
    }
}