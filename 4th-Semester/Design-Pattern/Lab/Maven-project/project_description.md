# Maven SQLite Blog Application — Full Project Description

A console-based Java application built with Maven that demonstrates:
- **SQLite database connectivity** via JDBC
- **Relational data** with foreign keys
- **BCrypt password hashing** for authentication
- **Four Gang-of-Four design patterns**: Singleton, DAO, Factory Method, Strategy
- **Role-based access control** for Admin, Author, and Guest users

---

## 1. High-Level Architecture

```
┌────────────────────────────────────────────────────────────────┐
│                         App.java (Entry Point)                 │
│  - Runs connection test                                        │
│  - Runs seeder                                                 │
│  - Shows login/register menu                                   │
│  - Calls ViewFactory → dispatches to role-specific View        │
└─────────────────────────┬──────────────────────────────────────┘
                          │
         ┌────────────────┼──────────────────┐
         ▼                ▼                  ▼
   AuthService         ViewFactory      DatabaseSeeder
  (register/login)   (Factory Method)   (Tables + Data)
         │                │
         ▼                ▼
      UserDao       AdminView / AuthorView / GuestView
      BlogDao            (Strategy Pattern)
         │
         ▼
   DatabaseConnection  (Singleton)
         │
         ▼
      blog.db  (SQLite file on disk)
```

---

## 2. Project File Map (Every File Explained)

```
Maven-project/
│
├── pom.xml                                    ← Maven build config
├── blog.db                                    ← SQLite database (auto-created)
│
└── src/main/java/com/blogapp/
    │
    ├── App.java                               ← Main entry point
    │
    ├── model/                                 ← Plain data classes (POJOs)
    │   ├── Role.java
    │   ├── User.java
    │   └── Blog.java
    │
    ├── db/                                    ← Database infrastructure
    │   ├── DatabaseConnection.java
    │   ├── ConnectionTest.java
    │   └── DatabaseSeeder.java
    │
    ├── dao/                                   ← Data Access Object layer
    │   ├── UserDao.java
    │   ├── UserDaoImpl.java
    │   ├── BlogDao.java
    │   └── BlogDaoImpl.java
    │
    ├── service/                               ← Business logic layer
    │   └── AuthService.java
    │
    └── ui/                                    ← Console UI (Strategy views)
        ├── View.java
        ├── AdminView.java
        ├── AuthorView.java
        ├── GuestView.java
        └── ViewFactory.java
```

---

## 3. pom.xml — The Maven Build Configuration

**File:** [`pom.xml`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/pom.xml)

Maven is the **build and dependency management tool**. The `pom.xml` (Project Object Model) is the heart of any Maven project — it defines:

| Setting | Value | Purpose |
|---|---|---|
| `groupId` | `com.blogapp` | Namespace / organization identifier |
| `artifactId` | `maven-sqlite-blog` | Project name |
| `version` | `1.0-SNAPSHOT` | Current development version |
| Java source/target | `17` | Uses text blocks, switch expressions |

### Dependencies declared:

**1. `org.xerial:sqlite-jdbc:3.45.3.0`**
- The SQLite JDBC driver — allows Java to open, read, and write `.db` files using standard `java.sql.*` API
- No external database server needed; everything is a single file (`blog.db`)

**2. `org.mindrot:jbcrypt:0.4`**
- BCrypt password hashing library
- Passwords are **never stored in plain text** — only their bcrypt hash is saved in the DB

### Plugins declared:

| Plugin | Purpose |
|---|---|
| `maven-compiler-plugin` | Compiles Java 17 source code |
| `exec-maven-plugin` | Lets you run `mvn exec:java` to start the app |
| `maven-assembly-plugin` | Packages everything into a single runnable fat JAR |

---

## 4. Model Layer — Plain Data Classes

Models are **pure data holders** — they have no logic, no SQL, no I/O. They just carry data between layers.

---

### [`Role.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/model/Role.java)

```java
public enum Role {
    ADMIN, AUTHOR, GUEST
}
```

**What:** A Java `enum` defining the three user roles.

**Why an enum instead of a String?**
- Compile-time safety — you can't accidentally type `"AUTHO"` as a Role
- Used directly in `switch` expressions (like in `ViewFactory`)
- Stored as a `TEXT` string in SQLite (`"ADMIN"`, `"AUTHOR"`, `"GUEST"`) and parsed back with `Role.valueOf()`

---

### [`User.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/model/User.java)

```java
public class User {
    private final int    id;
    private final String username;
    private final String passwordHash;
    private final Role   role;
}
```

**What:** Maps directly to a row in the `users` table.

**Key design decisions:**
- All fields are `final` — a `User` object is **immutable** once created. This prevents accidental mutation.
- `passwordHash` stores the BCrypt hash, never the plain password
- Has only **getters**, no setters — immutable by design

---

### [`Blog.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/model/Blog.java)

```java
public class Blog {
    private final int    id;
    private final String title;
    private final String content;
    private final int    authorId;      // FK to users.id
    private final String authorName;   // populated by JOIN
    private final String createdAt;
}
```

**What:** Maps to a row in the `blogs` table, enriched with `authorName`.

**Key detail — `authorName`:**
The `blogs` table only stores `author_id` (the FK integer). The human-readable `authorName` is fetched via a SQL `JOIN` with `users` at query time and placed directly into this object. The view layer then just calls `blog.getAuthorName()` — no second query needed.

---

## 5. Database Layer

---

### [`DatabaseConnection.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/db/DatabaseConnection.java)

**Pattern: Singleton**

```java
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:blog.db");
        this.connection.createStatement().execute("PRAGMA foreign_keys = ON");
    }

    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

**What:** Provides a single, shared `java.sql.Connection` to the SQLite database file.

**Why Singleton?**
- Opening a database connection is expensive — doing it every time a DAO runs a query would be wasteful
- All DAOs share **one** connection throughout the app's lifetime
- `synchronized` makes it thread-safe

**What is `PRAGMA foreign_keys = ON`?**
- By default, SQLite **does not enforce foreign keys** — it ignores them
- This pragma activates FK enforcement for this connection session
- Now if you try to insert a `blog` with a non-existent `author_id`, SQLite will reject it with an error

**Connection URL:** `jdbc:sqlite:blog.db`
- `jdbc:` — JDBC protocol prefix
- `sqlite:` — driver type
- `blog.db` — creates/opens this file in the current working directory (the project root)

---

### [`ConnectionTest.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/db/ConnectionTest.java)

```java
ResultSet rs = stmt.executeQuery("SELECT 2 + 2 AS result");
int result = rs.getInt("result");
// expected: 4
```

**What:** Verifies the JDBC connection is alive by running a simple arithmetic query.

**Why `SELECT 2 + 2`?**
- It requires no tables to exist — it works on a brand new empty database
- If this query runs and returns `4`, the JDBC driver is loaded, the connection is open, and SQL can be executed
- Acts as a "smoke test" before attempting anything else

**Output on success:**
```
[✓] Query: SELECT 2 + 2 AS result
[✓] Result: 4  →  Connection is WORKING!
```

---

### [`DatabaseSeeder.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/db/DatabaseSeeder.java)

**What:** Creates the database schema and populates it with sample data.

**Two phases:**

#### Phase 1 — DDL (Data Definition Language): Create Tables

```sql
CREATE TABLE IF NOT EXISTS users (
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    username      TEXT    NOT NULL UNIQUE,
    password_hash TEXT    NOT NULL,
    role          TEXT    NOT NULL CHECK(role IN ('ADMIN','AUTHOR','GUEST'))
);

CREATE TABLE IF NOT EXISTS blogs (
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    title      TEXT    NOT NULL,
    content    TEXT    NOT NULL,
    author_id  INTEGER NOT NULL,
    created_at TEXT    NOT NULL DEFAULT (datetime('now')),
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);
```

**Key constraints explained:**

| Constraint | Table | Meaning |
|---|---|---|
| `PRIMARY KEY AUTOINCREMENT` | both | SQLite auto-generates a unique integer id for each row |
| `UNIQUE` | users.username | No two users can share the same username |
| `NOT NULL` | multiple | These columns must always have a value |
| `CHECK(role IN (...))` | users.role | SQLite rejects any role string not in the allowed set |
| `FOREIGN KEY (author_id) REFERENCES users(id)` | blogs | Every blog must have a valid user as author |
| `ON DELETE CASCADE` | blogs | If a user is deleted, all their blogs are automatically deleted too |

#### Phase 2 — DML (Data Manipulation Language): Insert Sample Data

Inserts **4 users** and **5 blogs** only if the table is currently empty (idempotent — safe to call multiple times):

| User | Password | Role | Blogs |
|---|---|---|---|
| admin | admin123 | ADMIN | — |
| alice | alice123 | AUTHOR | 3 blogs |
| bob | bob123 | AUTHOR | 2 blogs |
| charlie | charlie123 | GUEST | — |

**Why BCrypt in the seeder?**
```java
BCrypt.hashpw(plainPw, BCrypt.gensalt())
```
Even the seeded test passwords are hashed before insertion — the plain text `"alice123"` is never written to the database. This mirrors real production behaviour.

---

## 6. DAO Layer — Data Access Objects

The DAO pattern separates SQL queries from business logic. Every class that needs data asks the DAO — it never writes SQL itself.

---

### [`UserDao.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/dao/UserDao.java) — Interface

```java
public interface UserDao {
    Optional<User> findByUsername(String username);
    User           save(String username, String passwordHash, String role);
    List<User>     findAllAuthors();
}
```

**Why an interface?**
- `AuthService` depends on `UserDao` (the interface), not `UserDaoImpl` (the concrete class)
- This is called **programming to an interface** — a fundamental OOP principle
- You could swap `UserDaoImpl` for a `MockUserDaoImpl` in tests without changing `AuthService`

**Why `Optional<User>` instead of `User`?**
- A user search can legitimately return nothing (username not found)
- `Optional` forces the caller to handle the empty case explicitly — no accidental `NullPointerException`

---

### [`UserDaoImpl.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/dao/UserDaoImpl.java) — SQLite Implementation

**What:** Contains the actual SQL that talks to the `users` table.

**All three operations use `PreparedStatement`:**
```java
PreparedStatement ps = conn.prepareStatement(
    "SELECT id, username, password_hash, role FROM users WHERE username = ?"
);
ps.setString(1, username);
```

**Why `PreparedStatement` and not string concatenation?**
- **SQL Injection prevention** — if a user types `'; DROP TABLE users; --` as their username, a `PreparedStatement` treats it as a literal string, not executable SQL
- Performance — the DB engine can cache the query plan

**`findAllAuthors()`** — used by `AdminView`:
```sql
SELECT ... FROM users WHERE role = 'AUTHOR' ORDER BY username
```
Returns only Author-role users, alphabetically sorted.

---

### [`BlogDao.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/dao/BlogDao.java) — Interface

```java
public interface BlogDao {
    List<Blog> findAllPaginated(int page, int pageSize);
    List<Blog> findByAuthorId(int authorId);
    int        countAll();
}
```

**Three methods serve three different needs:**

| Method | Used By | Purpose |
|---|---|---|
| `findAllPaginated` | `GuestView` | Get one page of all blogs |
| `findByAuthorId` | `AuthorView` | Get only the logged-in author's blogs |
| `countAll` | `GuestView` | Calculate total pages for pagination |

---

### [`BlogDaoImpl.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/dao/BlogDaoImpl.java) — SQLite Implementation

**What:** Contains SQL that joins `blogs` with `users` and implements pagination.

**The JOIN query (used in both `findAllPaginated` and `findByAuthorId`):**
```sql
SELECT b.id, b.title, b.content, b.author_id,
       u.username AS author_name, b.created_at
FROM blogs b
JOIN users u ON b.author_id = u.id
WHERE b.author_id = ?          -- only for findByAuthorId
ORDER BY b.created_at DESC
LIMIT ? OFFSET ?               -- only for findAllPaginated
```

**What the JOIN does:**
- The `blogs` table only has `author_id` (an integer)
- The `JOIN` reaches into the `users` table and pulls `username` alongside each blog row
- Result: one query returns both blog data and the author's name in one shot

**Pagination with `LIMIT` and `OFFSET`:**
```java
ps.setInt(1, pageSize);              // e.g. 5
ps.setInt(2, (page - 1) * pageSize); // page 1 → offset 0, page 2 → offset 5
```
- `LIMIT 5 OFFSET 0` → first 5 rows (page 1)
- `LIMIT 5 OFFSET 5` → next 5 rows (page 2)

---

## 7. Service Layer — Business Logic

### [`AuthService.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/service/AuthService.java)

**What:** Handles all authentication logic — registration and login.

**Why a separate service layer?**
- `App.java` handles UI. `UserDaoImpl` handles SQL. `AuthService` handles the *logic* between them.
- This is the principle of **Separation of Concerns** — each layer has one job

#### `register(username, password, role)`
```java
// 1. Check duplicate username
if (userDao.findByUsername(username).isPresent()) return Optional.empty();

// 2. Hash the password
String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));

// 3. Persist to DB
User created = userDao.save(username, hash, role);
```

- Work factor `12` in `BCrypt.gensalt(12)` — higher = slower hash = harder to brute-force
- Returns `Optional.empty()` if the username is taken — the caller handles the message

#### `login(username, password)`
```java
Optional<User> found = userDao.findByUsername(username);
if (found.isEmpty()) return Optional.empty();

if (BCrypt.checkpw(password, user.getPasswordHash())) {
    return Optional.of(user);
}
```

**Why `BCrypt.checkpw()` instead of re-hashing and comparing?**
- BCrypt includes a random **salt** embedded in the hash — the same password produces a different hash each time
- `checkpw()` extracts the salt from the stored hash, re-hashes the input with that same salt, and compares — this is the only correct way to verify BCrypt passwords
- It is also **timing-safe** — takes the same time whether the password is right or wrong, preventing timing attacks

---

## 8. UI Layer — Strategy Pattern Views

This layer is responsible purely for **displaying information** to the console. It uses two design patterns together.

---

### [`View.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/ui/View.java) — Strategy Interface

```java
public interface View {
    void render(User user);
}
```

**Pattern: Strategy**
- Defines a common contract: every view must implement `render(User user)`
- `App.java` holds a reference of type `View` — it never knows which concrete class is behind it
- This is exactly the **Strategy Pattern**: the algorithm (what to display) is encapsulated and swappable

---

### [`AdminView.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/ui/AdminView.java)

**Triggered when:** User logs in with `ADMIN` role.

**What it shows:** A formatted table of all users with `AUTHOR` role.

```
╔══════════════════════════════════════════════════╗
║  ADMIN DASHBOARD  —  Welcome, admin             ║
╚══════════════════════════════════════════════════╝

  ID     Username              Role
  ────────────────────────────────────────
  2      alice                 AUTHOR
  3      bob                   AUTHOR

  Total authors: 2
```

**Data source:** `UserDao.findAllAuthors()` → `SELECT ... WHERE role = 'AUTHOR'`

---

### [`AuthorView.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/ui/AuthorView.java)

**Triggered when:** User logs in with `AUTHOR` role.

**What it shows:** Only the blogs that belong to the logged-in author.

```
╔══════════════════════════════════════════════════╗
║  AUTHOR DASHBOARD  —  Welcome, alice            ║
╚══════════════════════════════════════════════════╝
  Showing: Your blogs only

  ┌─ [1] SQLite with JDBC
  │  Author   : alice
  │  Published: 2024-03-05 14:00:00
  │  SQLite is a lightweight, serverless database engine...
  └──────────────────────────────────────────────────

  Total: 3 blog(s)
```

**Data source:** `BlogDao.findByAuthorId(user.getId())` → `WHERE author_id = ?`

**Special method — `static printBlogs()`:**
This method is `static` and package-visible so that `GuestView` can reuse the same blog card formatting without code duplication.

---

### [`GuestView.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/ui/GuestView.java)

**Triggered when:** User logs in with `GUEST` role.

**What it shows:** All blogs from all authors, paginated at 5 per page, with keyboard navigation.

```
╔══════════════════════════════════════════════════╗
║  GUEST DASHBOARD  —  Welcome, charlie           ║
╠══════════════════════════════════════════════════╣
║  All Blogs  —  Page  1 of 1   (5 total)          ║
╚══════════════════════════════════════════════════╝

  ┌─ [1] Understanding Foreign Keys
  │  Author   : bob
  ...

  Navigation  [N]ext  [P]rev  [Q]uit  →
```

**Pagination logic:**
```java
int totalBlogs = blogDao.countAll();
int totalPages = (int) Math.ceil((double) totalBlogs / PAGE_SIZE);
```
- `countAll()` → `SELECT COUNT(*) FROM blogs`
- `Math.ceil(5.0 / 5)` = 1 page; `Math.ceil(6.0 / 5)` = 2 pages
- Navigation input loop: `N` increments page, `P` decrements, `Q` exits

---

### [`ViewFactory.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/ui/ViewFactory.java)

**Pattern: Factory Method**

```java
public static View createView(Role role) {
    return switch (role) {
        case ADMIN  -> new AdminView();
        case AUTHOR -> new AuthorView();
        case GUEST  -> new GuestView();
    };
}
```

**What:** A single factory method that takes a `Role` and returns the matching `View` implementation.

**Why Factory Method?**
- `App.java` doesn't need to know `AdminView`, `AuthorView`, or `GuestView` exist
- Adding a new role = add a new `View` class + one new `case` here. Nothing else changes.
- The decision of **which object to create** is centralized in one place

**The combined pattern flow:**
```
Role.AUTHOR  →  ViewFactory.createView()  →  new AuthorView()  →  view.render(user)
              (Factory Method)              (object created)      (Strategy executed)
```

---

### [`App.java`](file:///Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project/src/main/java/com/blogapp/App.java) — Main Entry Point

**What:** Orchestrates the entire application flow.

**Startup sequence:**

```
1. printBanner()           → welcome message
2. ConnectionTest.run()    → SELECT 2+2, verify = 4
3. DatabaseSeeder.seed()   → create tables + insert sample data (if empty)
4. Main menu loop:
      [1] Login   → handleLogin()
      [2] Register→ handleRegister()
      [3] Exit    → close DB connection, return
```

**`handleLogin()` method — complete flow:**
```java
Optional<User> result = authService.login(username, password);
// ↑ BCrypt check happens here

User user = result.get();
View view = ViewFactory.createView(user.getRole());  // Factory picks the view
view.render(user);                                    // Strategy renders it
```

**`handleRegister()` method:**
- Validates username is not blank
- Validates password is at least 4 characters
- Lets user pick role: Author (`1`) or Guest (`2`) — Admin cannot be self-registered
- Calls `authService.register()` which hashes and saves

---

## 9. Design Patterns — Complete Summary

### Pattern 1: Singleton — `DatabaseConnection`

```
Problem: Opening a new DB connection for every query is expensive.
Solution: One shared connection, created once, reused always.

First call:  getInstance() → instance is null → new DatabaseConnection() → returns it
Later calls: getInstance() → instance exists → return same instance
```

### Pattern 2: DAO (Repository) — `UserDao`, `BlogDao`

```
Problem: Business logic (AuthService, Views) shouldn't know SQL.
Solution: Interfaces define what data is needed; Impl classes handle how.

AuthService  →  UserDao (interface)  →  UserDaoImpl (SQL)  →  SQLite
GuestView    →  BlogDao (interface)  →  BlogDaoImpl (SQL)  →  SQLite
```

### Pattern 3: Factory Method — `ViewFactory`

```
Problem: App shouldn't be coupled to specific view classes.
Solution: A factory method takes a Role and returns the right View.

Role.ADMIN  → AdminView
Role.AUTHOR → AuthorView     all behind the View interface
Role.GUEST  → GuestView
```

### Pattern 4: Strategy — `View` interface

```
Problem: Each role needs different display logic, but caller shouldn't care which.
Solution: View interface defines the contract. Each class is a swappable algorithm.

App holds: View view = ViewFactory.createView(role);
App calls:  view.render(user);   ← same call, different behaviour per class
```

---

## 10. Data Flow — End-to-End Examples

### Flow A: `alice` logs in

```
App: "1" (Login)
  → authService.login("alice", "alice123")
      → userDao.findByUsername("alice")
          → SQL: SELECT * FROM users WHERE username = 'alice'
          → returns User{id=2, role=AUTHOR, hash="$2a$12$..."}
      → BCrypt.checkpw("alice123", hash) → true
      → returns Optional.of(user)
  → ViewFactory.createView(Role.AUTHOR) → new AuthorView()
  → authorView.render(user)
      → blogDao.findByAuthorId(2)
          → SQL: SELECT b.*, u.username FROM blogs b
                 JOIN users u ON b.author_id = u.id
                 WHERE b.author_id = 2
                 ORDER BY created_at DESC
          → returns 3 Blog objects
      → printBlogs(blogs) → displays 3 blog cards
```

### Flow B: `charlie` (Guest) browses page 2

```
App: "1" (Login) → login("charlie", "charlie123") → Role.GUEST
  → ViewFactory → new GuestView()
  → guestView.render(user)
      → blogDao.countAll() → SELECT COUNT(*) → 5
      → totalPages = ceil(5/5) = 1
      → blogDao.findAllPaginated(1, 5)
          → SQL: SELECT ... LIMIT 5 OFFSET 0
          → 5 blogs returned
      → printBlogs(blogs) → displays 5 blog cards
      → user presses "N" → currentPage = 2
      → "Already on the last page" (page 2 > totalPages 1)
```

### Flow C: New user registers

```
App: "2" (Register)
  → username: "hamim", password: "pass123", role: "1" (AUTHOR)
  → authService.register("hamim", "pass123", "AUTHOR")
      → userDao.findByUsername("hamim") → empty (username free)
      → BCrypt.hashpw("pass123", gensalt(12)) → "$2a$12$xyz..."
      → userDao.save("hamim", "$2a$12$xyz...", "AUTHOR")
          → SQL: INSERT INTO users (username, password_hash, role)
                 VALUES ('hamim', '$2a$12$xyz...', 'AUTHOR')
          → returns User{id=6, ...}
      → returns Optional.of(user)
  → "[✓] Account created! You can now log in."
```

---

## 11. Database Schema Diagram

```
┌──────────────────────────────────┐
│             users                │
├──────────┬───────────────────────┤
│ id       │ INTEGER PK AUTOINCR   │
│ username │ TEXT NOT NULL UNIQUE   │
│ password_hash │ TEXT NOT NULL    │
│ role     │ TEXT CHECK(ADMIN/     │
│          │      AUTHOR/GUEST)    │
└──────────┴───────────────────────┘
                  │  (1)
                  │
                  │ author_id FK
                  │  (many)
┌──────────────────────────────────┐
│             blogs                │
├──────────┬───────────────────────┤
│ id       │ INTEGER PK AUTOINCR   │
│ title    │ TEXT NOT NULL         │
│ content  │ TEXT NOT NULL         │
│ author_id│ INTEGER NOT NULL FK   │ ──── references users.id
│ created_at│ TEXT DEFAULT now()   │
└──────────┴───────────────────────┘
```

**Relationship:** One user (Author) can have many blogs. Each blog belongs to exactly one user.
**Foreign Key Enforcement:** `PRAGMA foreign_keys = ON` in `DatabaseConnection` activates this.
**Cascade Delete:** `ON DELETE CASCADE` — deleting a user removes all their blogs automatically.

---

## 12. How to Run

```bash
# Step 1 — Navigate to project
cd /Users/Inz_mac/Software-Engineering-Course/4th-Semester/Design-Pattern/Lab/Maven-project

# Step 2 — Compile
mvn compile

# Step 3 — Run interactively
mvn exec:java

# Step 4 (optional) — Package as standalone fat JAR
mvn package
java -jar target/blog-app-jar-with-dependencies.jar
```

**Seeded test accounts:**

| Username | Password | What you see after login |
|---|---|---|
| `admin` | `admin123` | Table of all authors |
| `alice` | `alice123` | Her 3 blogs |
| `bob` | `bob123` | His 2 blogs |
| `charlie` | `charlie123` | All 5 blogs, paginated (N/P/Q to navigate) |
