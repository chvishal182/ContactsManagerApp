# ContactsManager Android App

ContactsManager is a simple yet robust Android application designed for managing personal contacts. 
It allows users to view, add, and delete contacts seamlessly, built with modern Android development practices and Jetpack components.

## Features

*   **View Contacts:** Displays a list of all saved contacts.
*   **Add New Contact:** Easily add new contacts with name and email information.
*   **Delete Contact:** Intuitive swipe-to-delete functionality for removing contacts.
*   **Undo Delete:** A Snackbar provides an option to undo an accidental deletion.
*   **Efficient List Handling:** Smooth scrolling and list updates using `RecyclerView` with `ListAdapter` and `DiffUtil`.
*   **Local Persistence:** Contacts are stored locally on the device using Room database.
*   **Modern UI:** Clean interface with edge-to-edge display.

## Screenshots

![Main Screen](https://github.com/user-attachments/assets/ba445881-a955-4501-b75e-76bf727c2ed7)
![Add Contact Screen](<img width="377" alt="image" src="https://github.com/user-attachments/assets/82db4d7f-b742-45ed-8c30-cd543742cc93" />)
![Delete Contact](<img width="379" alt="image" src="https://github.com/user-attachments/assets/d64b4caf-3576-4078-ad77-8613867150a7" />)
![Undo Delete](<img width="407" alt="image" src="https://github.com/user-attachments/assets/88a9a7e3-176b-4c1e-b2c8-64c98ec34558" />)

## Tech Stack & Architecture

*   **Language:** Java
*   **Architecture:** MVVM (Model-View-ViewModel)
    *   **View:** Activities (`MainActivity`, `AddNewContactActivity`) responsible for UI and user interaction.
    *   **ViewModel:** `ContactsViewModal` (manages UI-related data in a lifecycle-aware manner, interacts with Repository).
    *   **Model:**
        *   `Repository`: Single source of truth for data, abstracts data sources.
        *   `Room Persistence Library`: For local database storage (`Contacts` Entity, `ContactDAO`).
*   **Jetpack Components:**
    *   **ViewModel:** For UI data management.
    *   **LiveData:** For observable data and reactive UI updates.
    *   **Room:** For robust local database storage.
    *   **Data Binding:** To bind UI components in layouts to data sources declaratively.
    *   **Navigation (Implicit):** Using Intents for navigating between Activities.
*   **UI Components:**
    *   `RecyclerView` with `ListAdapter` and `DiffUtil`: For efficient and animated list display.
    *   `ItemTouchHelper`: For swipe-to-delete functionality.
    *   `Snackbar`: For providing feedback and actions like "UNDO".
    *   `FloatingActionButton (FAB)`: For initiating the add contact action.
*   **Threading:** `ExecutorService` for background database operations to keep the UI responsive.

## Project Structure

The project follows a standard Android project structure with a focus on modularity:
  com.chelv.contactsmanager
  ├── adapter # RecyclerView Adapters (ContactListAdapter_ v2) 
  ├── clickHandlers # UI event handlers (MainActivityClickHandlers,  AddNewContactClickHandler) 
  ├── ContactDatabase.java # Room Database setup 
  ├── dao # Data Access Objects for Room (ContactDAO) 
  ├── entity # Room Database Entities (Contacts) 
  ├── repository # Repository class for data abstraction 
  ├── viewmodel # ViewModels (ContactsViewModal)                           
  ├── MainActivity.java 
  └── AddNewContactActivity. java


## Learning & Best Practices Implemented

This project serves as a practical example of implementing modern Android development techniques, including:

*   **MVVM Architecture:** For separation of concerns and testability.
*   **Jetpack Components:** Leveraging ViewModel, LiveData, Room, and Data Binding.
*   **Efficient RecyclerView Usage:** Using `ListAdapter` and `DiffUtil`.
*   **Background Threading:** For database operations to prevent ANR (Application Not Responding) errors.
*   **User Experience:** Features like swipe-to-delete with visual feedback and an undo option.
*   **Clean Code:** Using dedicated click handlers and clear class responsibilities.
