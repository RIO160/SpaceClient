SpaceClient
SpaceClient is a Java-based desktop gaming and social platform built with Java Swing that provides an all-in-one entertainment hub with multiple classic games and real-time chat functionality. The application features a space-themed user interface with custom styling and graphics.

Key Features
Authentication System:

User registration and login functionality
Secure password management with show/hide password option
Account validation and session management
Game Collection: The platform includes four built-in games accessible from a central menu:

Tic Tac Toe - Classic two-player strategy game
Snake Game - Arcade-style snake collection game
Ping Pong - Two-player paddle game
Space Chat - Real-time chat system with server-client architecture
Technical Highlights:

Built entirely in Java using Swing for GUI components
Custom-themed interface with space aesthetics (dark blue color scheme)
Client-server architecture for the chat system
Event-driven programming with ActionListener implementations
Modular package structure organized by feature (ChatsSys, PingPong, TicTacToe, snakeGame)
Architecture
The project follows a modular design with separate packages for each game and system component:

Main entry point through LoginPage.java
Menu system for game selection
Authentication module for user management
Game modules with independent implementations
Assets folder containing custom graphics (Client.png, SpaceRock.png)
This project serves as an excellent demonstration of Java GUI development, event handling, and socket programming for multiplayer/chat features.
