# ✅ ToDoList

A desktop task management application built with **Java Swing**, featuring user authentication, persistent task storage, and an intuitive GUI.

---

## 📋 Features

### 🔐 User Authentication
- **Sign Up** with full validation:
  - Full name: minimum 6 characters with a space
  - Username: minimum 6 alphanumeric characters, must be unique
  - Email: must be a valid `@gmail.com` address (the current validation enforces Gmail only)
  - Password: minimum 8 characters including at least one digit and one special character
  - Gender selection
- **Log In** with username and password verification
- Password input is masked for security

### 📝 Task Management
- Add unlimited tasks
- Mark tasks as complete using checkboxes (completed tasks appear grayed out)
- Delete individual tasks using the trash icon
- Tasks remain **editable** while incomplete
- Tasks are **automatically saved** when closed or deleted

### 💾 Data Persistence
- Each user's tasks are stored in a dedicated text file
- Tasks are loaded automatically upon login, preserving previous sessions

---

## 🚀 Getting Started

### Prerequisites

- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/) installed (Java 17 LTS or newer is recommended; Java 13 is the minimum supported version)
- A display environment (X11 on Linux, or run on Windows/macOS)

### Clone the Repository

```bash
git clone https://github.com/BugLrd/ToDoList.git
cd ToDoList
```

### Compile

```bash
javac Start.java Entity/*.java Frames/*.java
```

### Run

```bash
java Start
```

---

## 🖥️ Application Flow

```
Welcome Screen
     │
     ├── Log In ──────────────────────┐
     │                                ▼
     └── Sign Up ──────────── Main Todo List
                                      │
                              ┌───────┼────────┐
                              ▼       ▼        ▼
                            Add    Complete  Delete
                            Task    Task      Task
```

---

## 📁 Project Structure

```
ToDoList/
├── Start.java              # Application entry point
├── Entity/
│   ├── User.java           # User data model
│   └── UserManager.java    # User registration and file-based storage
├── Frames/
│   ├── WelcomeFrame.java   # Welcome / splash screen
│   ├── LogInFrame.java     # Login interface
│   ├── SignUpFrame.java    # Registration interface with input validation
│   └── MainFrame.java      # Main task management interface
└── Resources/
    ├── Image/
    │   ├── welcome.jpg     # Welcome screen background
    │   ├── LoginBg.jpg     # Login screen background
    │   ├── SignUpBg.jpg    # Sign-up screen background
    │   └── trash.png       # Delete task button icon
    └── Text/
        ├── Users.txt       # Stores registered user credentials
        └── [username].txt  # Per-user task file (created on first login)
```

---

## 🗄️ Data Storage Format

**User credentials** — `Resources/Text/Users.txt`
```
username ---> password ---> fullname ---> gender ---> email
```

**Task file** — `Resources/Text/[username].txt`
```
[checked]	Buy groceries
[unchecked]	Read a book
```

---

## 🛠️ Built With

- **Java** — Core programming language
- **Java Swing** — GUI framework
- **File I/O** — Plain text files for data persistence
- **IntelliJ IDEA** — Recommended IDE

---

## 📄 License

This project is open source. Feel free to use, modify, and distribute it.
