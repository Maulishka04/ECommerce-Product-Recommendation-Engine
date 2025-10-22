<!-- prettier-ignore -->
# 🛍️ E-Commerce Recommendation Engine (ECommerceRecoEngine)

[![Java](https://img.shields.io/badge/Java-11%2B-blue?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.x-orange?logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/build-manual-lightgrey.svg)](#)

Modern, modular, and extensible Java-based recommendation engine showcasing collaborative filtering, content-based filtering, and hybrid approaches — designed for education and small-scale demos.

---

## 🚀 Project Overview

ECommerceRecoEngine is a Java/Maven application that demonstrates how to build a recommendation system for an e-commerce platform. It focuses on algorithmic clarity and data-structure-driven design: product catalogs use trees and tries, social relationships use graphs, and recommendation ranking uses priority queues.

Use cases:
- Personalized product suggestions
- Algorithm A/B comparisons
- Educational demos for data structures and recommendation strategies

Architecture (high-level):

- Data ingestion (CSV / DB seed) → Preprocessing & feature extraction
- Data structures: BST / Trie / Graph / HashTable for fast lookups
- Algorithm layer: Collaborative Filtering & Content-Based Filtering
- Orchestration: Multithreaded RecommendationService producing top-N ranked results
- Output: Console UI demo (can be swapped for web/UI later)

---

## ✨ Features

- Personalized Recommendations: Collaborative and content-based strategies
- Hybrid Engine: Combine signals from multiple algorithms
- Custom Data Structures: BST, Trie, LinkedList, HashTable, Graph, PriorityQueue
- Sorting & Ranking: MergeSort + Max-Heap for top-N
- Multithreaded Execution: Concurrent strategy runs with timeouts
- JDBC Integration: H2 in-memory fallback with DAO examples
- A/B Demonstration: Compare algorithm outputs (top-1/top-N)

---

## 🧰 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 11+ |
| Build | Maven |
| DB (demo) | H2 (in-memory) |
| Logging | SLF4J (slf4j-simple) |
| Testing | JUnit (optional) |

Third-party libs are minimal on purpose — the emphasis is on core Java and algorithms.

---

## 🧭 How It Works (Flow)

1. Data ingestion: products and user purchase histories are either loaded from sample CSV/DAOs or from a DB (H2 demo).
2. Preprocessing: basic profile extraction (category preferences, average price) and graph construction for social ties.
3. Recommendation generation:
   - Collaborative Filtering: find similar users via purchase overlap and social proximity (BFS/DFS), recommend their products.
   - Content-Based Filtering: score products by category match and price proximity.
   - Hybrid: merge CF and CB outputs with a combined ranking.
4. Ranking: use a Max-PriorityQueue (max-heap) and MergeSort utilities to rank and produce top-N.
5. Output: recommendations are printed by the console UI; the system also provides hooks for DB persistence and extensions.

---

## 📁 Project Structure

```
ECommerceRecoEngine/
├─ pom.xml
├─ README.md
└─ src/
   └─ main/java/com/ecommerce/
      ├─ model/        # Product, User, Rating, PurchaseHistory
      ├─ data/         # Custom data structures: BST, Trie, Graph, LinkedList, HashTable, PriorityQueue
      ├─ algo/         # RecommendationStrategy, CollaborativeFiltering, ContentBasedFiltering, Hybrid
      ├─ service/      # RecommendationService (executor, orchestration)
      ├─ db/           # DBConnection, DAOs (ProductDAO, UserDAO) with H2 fallback
      ├─ ui/           # Console UI demo
      └─ utils/        # SortUtils (MergeSort), KMeans stub, helpers
```

Each package is intentionally small and focused to promote readability and extensibility.

---

## ⚙️ Installation & Run (Local)

Prerequisites:
- Java 11+ (JDK)
- Maven (3.x)

Clone the repo and build:

```powershell
git clone <your-repo-url> ECommerceRecoEngine
cd "ECommerceRecoEngine"
mvn clean package
```

Run the demo (after a successful build):

```powershell
# Run from IDE or
mvn exec:java -Dexec.mainClass="com.ecommerce.Main"

# Or run the compiled classes directly (ensure dependencies on classpath)
java -cp target/classes;"%USERPROFILE%\.m2\repository\org\slf4j\slf4j-simple\1.7.36\slf4j-simple-1.7.36.jar" com.ecommerce.Main
```

Notes:
- The app ships with sample products and users in `ProductDAO.sampleProducts()` and `UserDAO.sampleUsers(...)` for quick demos.
- If you prefer DB-backed runs, add SQL seed files and initialize an H2 or external DB, then update `DBConnection` credentials.

---

## 🧩 Usage & Extensibility

- Swap algorithms: implement `RecommendationStrategy` and register with the `RecommendationService`.
- Add features: add weightings, decay, time-aware signals, collaborative matrix factorization, or integrate ML models.
- Replace UI: build a REST API or GUI (Spring Boot + Thymeleaf / React) and reuse the service layer.
- Database: implement schema and DAOs for real persistence; sample DAO methods provide starting points.

Tips for contributors:
- Keep algorithms deterministic and testable.
- Add unit tests under `src/test/java` for each algorithm and data structure (happy path + edge cases).

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch: `git checkout -b feat/my-feature`
3. Commit changes with clear messages
4. Open a pull request describing the changes and motivation

Guidelines:
- Add tests for new features.
- Keep changes modular and well-documented.
- Use meaningful commit messages.

---

## 📜 License

This project is licensed under the MIT License — see the `LICENSE` file for details.

---

## 🙏 Acknowledgments & Author

Made with ❤️ for learning and demonstration purposes.

- Author: Project repository owner
- Inspired by academic recommendation systems and standard e-commerce patterns.

If you'd like, I can also:
- Add a sample SQL seed file (`resources/database.sql`) and wire `DBConnection` to load it automatically.
- Add JUnit tests for key algorithms.
- Provide a small Spring Boot wrapper to expose recommendations as HTTP endpoints.

---

Thank you for exploring ECommerceRecoEngine — if you'd like further enhancements, tell me which area you'd like to expand next.

