# Paint Program

A Java-based paint program that allows users to create, edit, and manage drawings with various functionalities. This project demonstrates core Java concepts, including file handling, GUI development, and the implementation of the undo feature.

---

## Features

### 1. **Drawing Shapes**
   - Users can draw various shapes like lines, rectangles, and circles.
   - Shapes can be customized with different colors and sizes.
   - Choose between filled or unfilled shapes.

### 2. **Freehand Drawing**
   - Allows users to create freehand strokes for more flexibility.

### 3. **Dotted Lines**
   - Enables users to draw lines with a dotted pattern.

### 4. **Color Palette**
   - Provides a color palette to choose custom colors for shapes and lines.

### 5. **Save Functionality**
   - Allows users to save their drawings to a file.
   - Supports the `.txt` format for saving shape data.

### 6. **Undo Functionality**
   - Removes the most recently added shape from the drawing.
   - Provides a way to revert accidental actions.

### 7. **Clear Canvas**
   - Clears all shapes from the drawing area, providing a fresh canvas.

---

## How It Works

1. **User Interface**:
   - A simple and intuitive GUI built using Java Swing.
   - Buttons for each functionality (e.g., Save, Open, Undo, Clear).

2. **File Handling**:
   - Save files using standard Java I/O operations.
   - File format preserves shape properties like type, position, size, and color.

3. **Undo Implementation**:
   - Uses an `ArrayList` to store the shapes.
   - The `undo` operation removes the last shape added to the list.

4. **Dynamic Rendering**:
   - The canvas updates dynamically as shapes are added, removed, or loaded from a file.

---

## Getting Started

### Prerequisites
   - Java Development Kit (JDK) 8 or higher
   - An IDE like IntelliJ IDEA, Eclipse, or NetBeans

### Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/ArsanyMounir/Java-Paint-Program
   ```
2. Open the project in your preferred IDE.
3. Build and run the `Main` class to start the application.

---

## Demonstration

A video showcasing the functionalities of the paint program:

https://github.com/user-attachments/assets/7d516cfd-a5aa-4bf3-8fec-ec3a271737f0



