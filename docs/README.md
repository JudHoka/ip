# Atom User Guide

## Introduction

Welcome to **Atom**, your intelligent chatbot assistant! 
Atom is designed to help users manage their tasks efficiently using simple text-based commands. 
With Atom, you can add, remove, mark, unmark, and search for tasks with ease.

## Features

### 1. Adding a To-Do Task
Create a simple to-do task without deadlines or time constraints.

**Usage:**
```
todo TASK_DESCRIPTION
```

**Example:**
```
todo iP Level-0 CS2113
```

**Expected Output:**
```
    Nice! I've added this task:
    [T][ ] iP Level-0 CS2113
    Now you have 1 task in the list.
```

---

### 2. Adding a Deadline Task
Create a task with a specified deadline.

**Usage:**
```
deadline TASK_DESCRIPTION /by dd-MM-yyyy (HH:mm)
```

**Example:**
```
deadline Weekly Java Quiz /by 07-02-2025 (16:00)
```

**Expected Output:**
```
    Nice! I've added this task:
    [D][ ] Weekly Java Quiz (by: 07-02-2025 (16:00))
    Now you have 2 tasks in the list.
```

---

### 3. Adding an Event Task
Create an event task that includes a start and end time.

**Usage:**
```
event TASK_DESCRIPTION /from dd-MM-yyyy (HH:mm) /to dd-MM-yyyy (HH:mm)
```

**Example:**
```
event tP Team meeting /from 12-03-2025 (10:00) /to 12-03-2025 (11:00)
```

**Expected Output:**
```
    Nice! I've added this task:
    [E][ ] tP Team meeting (from: 12-03-2025 10:00 to: 12-03-2025 11:00)
    Now you have 3 tasks in the list.
```

---

### 4. Listing All Tasks
Display all tasks currently stored in the chatbot.

**Usage:**
```
list
```

**Expected Output:**
```
    Below are the tasks in your list:
    1. [T][ ] iP Level-0 CS2113
    2. [D][ ] Weekly Java Quiz (by: 07-02-2025 (16:00))
    3. [E][ ] Team meeting (from: 12-03-2025 10:00 to: 12-03-2025 11:00)
```

---

### 5. Marking a Task as Done
Mark a specific task as completed.

**Usage:**
```
mark TASK_NUMBER
```

**Example:**
```
mark 1
```

**Expected Output:**
```
    Awesome! I've marked this task as done:
    [T][X] iP Level-0 CS2113
```

---

### 6. Unmarking a Task
Unmark a previously marked task as incomplete.

**Usage:**
```
unmark TASK_NUMBER
```

**Example:**
```
unmark 1
```

**Expected Output:**
```
    Alright, this task has been unmarked:
    [T][ ] iP Level-0 CS2113
```

---

### 7. Removing a Task
Delete a specific task from the list.

**Usage:**
```
remove TASK_NUMBER
```

**Example:**
```
remove 2
```

**Expected Output:**
```
    Got it. I have removed this task:
    [D][ ] Weekly Java Quiz (by: 07-02-2025 (16:00))
    Now you have 2 tasks in the list.
```

---

### 8. Finding Tasks by Keyword
Search for tasks that contain a specific keyword.

**Usage:**
```
find SEARCH_KEYWORD
```

**Example:**
```
find meeting
```

**Expected Output:**
```
    Here are the task(s) that match your search "meeting":
    2. [E][ ] Team meeting (from: 12-03-2025 10:00 to: 12-03-2025 11:00)
    There is 1 task that matches your search.
```

---

### 9. Exiting the Chatbot
End the chatbot session.

**Usage:**
```
bye
```

**Expected Output:**
```
    Alright, I'll catch ya next time. Have a nice day!
```

---


## Conclusion
This User Guide provides everything needed to efficiently use **Atom**. If you encounter issues, refer to the **error messages** displayed by the chatbot for guidance. Happy task managing! 🎯

