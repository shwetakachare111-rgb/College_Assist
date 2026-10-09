
# 📌 1. PROJECT OVERVIEW

CampusAssist is a simple Android application designed to help college students report campus problems, track their complaints, read college notices, and access an emergency dialer option from one place.

The project is developed as a Semester-V Mobile Application Development project using Android Studio, Java, XML, and SQLite. It demonstrates common Android development concepts such as activities, intents, user-interface widgets, local database operations, notifications, and Text-to-Speech.

💡 **Project Note:** This is an academic/demo application. Login credentials and college notices may be hardcoded sample data. The app is not connected to an official college server unless additional backend integration is implemented.

---

# ❗ 2. PROBLEM STATEMENT

Students may encounter issues such as broken classroom equipment, electrical faults, water leakage, or maintenance problems. In a traditional process, reporting and following up on these issues may require visiting an office or contacting staff directly.

CampusAssist provides a simple digital interface where students can submit a complaint and review its status. It also displays sample college notices and provides quick access to the phone dialer for emergencies.

---

# 🎯 3. PROJECT OBJECTIVES

The main objectives of CampusAssist are:

🔹 Provide a simple interface for reporting campus problems.

🔹 Collect complaint details such as title, category, location, description, priority, and date.

🔹 Store complaint records locally using SQLite.

🔹 Display saved complaints and support status updates and deletion.

🔹 Display college notices in a readable list.

🔹 Read a selected notice aloud using Android Text-to-Speech.

🔹 Provide an emergency shortcut that can open the phone dialer.

🔹 Demonstrate key concepts from the Mobile Application Development syllabus.

---

# ✨ 4. MAIN FEATURES

## 👋 4.1 Welcome Screen

The Welcome Screen introduces the CampusAssist application and provides a **Get Started** button to continue to the login screen.

## 🔑 4.2 Login Screen

The Login Screen accepts an email address and password. The current demo may use predefined credentials in the Java code. This is not production-grade authentication.

**Demo Credentials (only if still configured in LoginActivity.java):**

🔹 Email: [student@gmail.com](mailto:student@gmail.com)

🔹 Password: 1234

## 📊 4.3 Dashboard

The Dashboard provides shortcuts to the main features of the application:

🔹 Report a Problem

🔹 My Complaints

🔹 College Notices

🔹 Emergency

🔹 Test Reminder

## 📝 4.4 Report a Problem

This feature allows students to submit complaints about problems on the college campus.

Students can enter the following details:

🔹 **Problem Title:** A short name for the issue.

🔹 **Category:** Selected from a Spinner.

🔹 **Location:** The place where the issue occurred on campus.

🔹 **Description:** Details about the problem.

🔹 **Priority:** Low, Medium, or High.

🔹 **Date:** Selected using a date picker.

The form validates required fields before saving. Submitted complaints are stored in the local SQLite database.

## 📋 4.5 My Complaints

The My Complaints feature retrieves saved complaints from SQLite.

It displays complaint details such as title, category, location, description, priority, date, and status, depending on the fields stored.

The feature uses:

🔹 **Cursor:** To read database query results.

🔹 **ListView:** To display complaint records.

🔹 **ArrayAdapter:** To connect complaint data to the ListView.

🔹 **AlertDialog:** To allow users to update complaint status or confirm deletion.

Tapping a complaint can open a dialog to update its status. Long-pressing a complaint can show a confirmation dialog before deletion.

## 📢 4.6 College Notices

The College Notices feature displays a list of sample college announcements.

🔹 Uses a ListView and ArrayAdapter to display notices.

🔹 Allows users to select a notice.

🔹 Uses Android Text-to-Speech to read a selected notice aloud.

The notices are sample data unless the app has been extended to fetch them from a server or database.

## 🚨 4.7 Emergency

The Emergency feature opens the phone dialer using an implicit intent.

If ACTION_DIAL is used, the user can review the number and choose whether to call. The app does not place the call automatically.

## 🔔 4.8 Reminder Notification

The Dashboard includes a test reminder option.

A BroadcastReceiver can handle the reminder event and display a notification. Recent Android versions may require notification permission. The exact behavior depends on the implementation.

---

# 🛠️ 5. TECHNOLOGIES USED

**Android Studio:** IDE used to develop and run the application.

**Java:** Used for application logic and event handling.

**XML:** Used for screen layouts and user-interface design.

**SQLite:** Used for local storage of complaint records.

**DatabaseHelper / SQLiteOpenHelper:** Used for database creation and CRUD operations.

**Cursor:** Used to read records returned from SQLite queries.

**ListView:** Used to display complaints and notices.

**ArrayAdapter:** Used to connect list data to a ListView.

**Intents:** Used for navigation between activities and opening the phone dialer.

**AlertDialog:** Used to confirm actions and select complaint status.

**Toast:** Used to display short feedback messages.

**Text-to-Speech:** Used to read a selected notice aloud.

**BroadcastReceiver:** Used to handle reminder broadcasts.

**Android Notifications:** Used to display reminder messages.

---

# 🔄 6. APPLICATION FLOW

The application follows this flow:

Welcome Screen
↓
Login Screen
↓
Dashboard
↓
Main Features:

🔹 Report a Problem → SQLite Database

🔹 My Complaints → Read, Update, and Delete Complaints

🔹 College Notices → Text-to-Speech

🔹 Emergency → Open Phone Dialer

🔹 Test Reminder → Display Notification

---

# 📂 7. SUGGESTED PROJECT STRUCTURE

The exact package and filenames may vary. A typical Android Studio project structure is:

CampusAssist/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── your-package-name/
│           │       ├── MainActivity.java
│           │       ├── LoginActivity.java
│           │       ├── DashboardActivity.java
│           │       ├── ReportProblemActivity.java
│           │       ├── MyComplaintsActivity.java
│           │       ├── NoticesActivity.java
│           │       ├── DatabaseHelper.java
│           │       └── ReminderReceiver.java
│           ├── res/
│           │   └── layout/
│           │       ├── activity_main.xml
│           │       ├── activity_login.xml
│           │       ├── activity_dashboard.xml
│           │       ├── activity_report_problem.xml
│           │       ├── activity_my_complaints.xml
│           │       ├── activity_notices.xml
│           │       └── list_item_clear.xml
│           └── AndroidManifest.xml
└── README.md

Your project may contain additional resource files and classes.

---

# 🗄️ 8. DATABASE AND CRUD OPERATIONS

CampusAssist uses SQLite for local complaint storage.

**CRUD** stands for the four basic database operations:

🔹 **Create:** Save a newly submitted complaint.

🔹 **Read:** Retrieve and display saved complaints.

🔹 **Update:** Change a complaint's status, such as Pending, In Progress, or Resolved.

🔹 **Delete:** Remove a complaint after user confirmation.

The DatabaseHelper class handles database operations. MyComplaintsActivity uses a Cursor to access query results and populate the list.

The actual table and column names are defined in DatabaseHelper.java. Common complaint fields include title, category, location, description, priority, date, and status.

---

# 🎨 9. USER INTERFACE AND READABILITY

XML layouts separate the user interface from Java logic.

The custom list_item_clear.xml layout is used by the complaints and notices adapters to display list content with larger, bold, dark text.

The layout ID and adapter configuration must match the Java code.

A simple and readable interface helps students navigate the application and access its features easily.

---

# 🚀 10. HOW TO SET UP AND RUN THE PROJECT

Follow these steps to run CampusAssist:

1. Install Android Studio on your computer.
2. Open Android Studio and choose **Open**.
3. Select the CampusAssist project folder containing the Gradle project files.
4. Wait for Gradle sync to finish.
5. Confirm that the Android SDK required by the project is installed.
6. Select an Android emulator through Device Manager, or connect an Android phone with USB debugging enabled.
7. Press **Run** (the green play button).
8. Follow the app flow from Welcome → Login → Dashboard.

**Troubleshooting:** If the project does not build, check Gradle sync messages, package names, manifest declarations, and whether all referenced XML layout files exist.

---

# 🧪 11. HOW TO TEST THE APP

Follow these steps to test the main features:

1. Launch CampusAssist.
2. Tap **Get Started**.
3. Sign in using the demo credentials configured in LoginActivity.java.
4. Open **Report a Problem**.
5. Enter a title, category, location, description, priority, and date.
6. Submit the complaint and check for the success message.
7. Open **My Complaints** and verify that the complaint appears.
8. Tap a complaint and test changing its status.
9. Long-press a complaint and test the delete confirmation.
10. Open **College Notices** and tap a notice to test Text-to-Speech.
11. Open **Emergency** and verify that the phone dialer opens as expected.
12. Use **Test Reminder** and verify that the notification appears. Grant notification permission if Android requests it.

---

# 🔐 12. PERMISSIONS AND ANDROID VERSION NOTES

Depending on the implementation and Android version, the project may need notification permission such as POST_NOTIFICATIONS.

If the emergency feature uses ACTION_DIAL, it normally opens the dialer without directly placing a call. If a different intent is used, required permissions and behavior may differ.

Text-to-Speech availability and notification behavior can vary by device and Android version. Test these features on the target emulator or phone.

---

# ⚠️ 13. LIMITATIONS

The current application may have the following limitations:

🔹 Login may use hardcoded demo credentials and is not secure authentication.

🔹 Notices may be hardcoded sample content rather than live college announcements.

🔹 Complaint records are stored locally on the device and are not automatically shared with college staff.

🔹 There may be no online synchronization, user registration, or role-based access.

🔹 Emergency contact details must be configured and verified for the intended college.

🔹 Notification scheduling and delivery depend on the implementation and Android version.

---

# 🌱 14. FUTURE ENHANCEMENTS

The application can be improved by adding the following features:

🚀 Secure login and individual student accounts.

🏢 A college administration panel for reviewing complaints.

🔔 Complaint status notifications.

📷 Image attachments for reported problems.

☁️ Online backup and synchronization.

🔍 Search and filtering of complaints.

📢 Real-time notices from the college.

📊 Complaint categories and analytics for administrators.

🚨 A verified campus emergency contact directory.

These improvements could make CampusAssist more useful for students and college administration.

---

# 📚 15. LEARNING OUTCOMES

This project demonstrates the following Android development concepts:

🎓 Creating Android screens using XML.

🎓 Handling user actions with Java.

🎓 Navigating between activities with intents.

🎓 Using common Android widgets.

🎓 Performing SQLite CRUD operations.

🎓 Reading database results with a Cursor.

🎓 Displaying data with ListView and ArrayAdapter.

🎓 Showing Toast messages and AlertDialogs.

🎓 Implementing Text-to-Speech.

🎓 Working with BroadcastReceiver and notifications.

🎓 Managing Android manifest declarations and runtime permissions.

Through this project, students gain practical experience in Android application development, user-interface design, and local database management.

---

# ✅ 16. CONCLUSION

CampusAssist is a beginner-friendly Android application that demonstrates how mobile technology can support common campus services.

It provides a single interface for submitting and tracking complaints, viewing notices, using Text-to-Speech, and accessing an emergency dialer shortcut.

The project helps students understand important Android development concepts, including activities, intents, XML layouts, Java programming, SQLite database operations, notifications, and user-interface components.

CampusAssist is suitable for demonstrating core Android development concepts in a Semester-V Mobile Application Development course.


