# 📝 Word Counter — Java Swing Application

A lightweight, clean, and beginner‑friendly **Java Swing** application that counts:
- **Total words**
- **Characters (with spaces)**
- **Characters (without spaces)**
- **Number of lines**
- **Average word length**

This project is written in professional, modular Java following best practices and Maven structure—clean enough for academic submissions, GitHub portfolio, or teaching materials.

---

## 📌 Features

- ✔️ Real‑time text analysis (auto‑updates while typing)
- ✔️ Clean and modern Swing UI
- ✔️ Word / character / line / average length calculation
- ✔️ "Insert Sample" button for quick testing
- ✔️ Clipboard copy for statistics summary
- ✔️ Fully unit‑tested core logic (`TextAnalyzer`)
- ✔️ Runnable JAR packaged via Maven

---

## 📁 Project Structure

```
word-counter/
├── pom.xml
├── LICENSE
├── README.md
├── .gitignore
├── .editorconfig
└── src/
    ├── main/java/com/mobinyousefi/wordcounter/
    │   ├── WordCounterApp.java
    │   ├── WordCounterFrame.java
    │   ├── TextAnalyzer.java
    │   └── TextStatistics.java
    └── test/java/com/mobinyousefi/wordcounter/
        └── TextAnalyzerTest.java
```

---

## 🚀 How to Run

### **1. Clone the repository**
```
git clone https://github.com/mobinyousefi-cs/word-counter.git
cd word-counter
```

### **2. Build the project (Maven)**
```
mvn clean package
```

### **3. Run the JAR file**
```
java -jar target/word-counter-0.1.0-SNAPSHOT.jar
```

The Swing GUI will open automatically.

---

## 🧠 Technical Overview

### **WordCounterApp.java**
Initializes the GUI using the Event Dispatch Thread (best practice).

### **WordCounterFrame.java**
Implements the Swing interface:
- JTextArea for input
- Toolbar with actions (Clear, Insert Sample, Copy Stats)
- Live statistics panel

### **TextAnalyzer.java**
Pure logic class performing:
- Whitespace‑based tokenization
- Line counting (`\R` regex support)
- Character counting (with & without whitespace)
- Average word length computation

### **TextStatistics.java**
Immutable Java record holding all computed values.

---

## 🧪 Unit Tests

All logic is validated with **JUnit 5**.
Run tests using:
```
mvn test
```

---

## 📦 Packaging

The Maven configuration builds a runnable JAR with:
- Class‑Path included
- Main class set to `WordCounterApp`

---

## 📝 License

This project is released under the **MIT License**, allowing free use, modification, and distribution.

---

## 👨‍💻 Author
**Mobin Yousefi**  
GitHub: [github.com/mobinyousefi-cs](https://github.com/mobinyousefi-cs)

---

## ⭐ Contributing
Pull requests are welcome! For major changes, please open an issue first.

---

## ❤️ Acknowledgements
Built as part of a series of educational Java projects to strengthen GUI development and software engineering fundamentals.

---

