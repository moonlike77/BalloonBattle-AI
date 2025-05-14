# 🎈 BalloonBattle-AI

An AI bot developed for a competitive server-based game designed for the **Advanced Programming Course (2015)**. The game involves two teams—**Blue** and **Red**—each with two roles: **boys** who inflate balloons and **girls** who pop them.

## 🧠 Game Overview

Each team has:
- **Boys**: Inflate balloons on the game board. Over-inflating beyond a defined threshold causes the balloon to pop, awarding negative points to the team.
- **Girls**: Pop balloons to gain points. Larger balloons yield higher points.

The goal is to maximize your team's score through strategic inflation and popping within a fixed number of turns. The team with the highest score at the end of the game wins.

## 🏆 Achievements

- 🥉 Ranked **3rd out of 10 teams** in the 2015 course competition.

## 📁 Repository Structure

```
mybot/
├── src/                    # Source code
│   ├── Game/               # Game model and mechanics
│   ├── client/             # Core client logic
│   │   ├── Team/           # Team AI implementation
│   │   └── util/           # Utility classes
├── build/                  # Compiled classes
├── dist/                   # Generated JAR file
├── nbproject/              # NetBeans project configuration
├── build.xml               # Ant build script
└── manifest.mf             # Manifest file for JAR packaging
```

## 🔧 Setup & Running

### Requirements
- Java JDK 8+
- Apache Ant (for building)
- NetBeans (optional, project files included)

### Build
```bash
cd mybot
ant
```

### Run
To run this client against the provided server:
```bash
java -jar dist/mybot.jar
```
> Note: The server `.jar` file must be running and accessible.

## 💡 Team AI Strategy

The AI logic is implemented in [`Team_AI.java`](src/client/Team/Team_AI.java). Our strategy focused on:
- Smart balloon inflation just below the threshold to prevent penalties.
- Targeting large balloons for popping to maximize points.
- Efficient communication between team members for coordination.

## 📜 License

This project is developed as part of an academic assignment and is provided for educational and demonstration purposes.

## ✍️ Author

Developed by [Your Name or Team Name] as part of the **Advanced Programming Course - 2015**.

---

🎉 Feel free to fork and experiment with different AI strategies to dominate the balloon battlefield!
