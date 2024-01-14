# Minesweeper Game developed as part of the Hyperskill Java Backend Developer track: https://hyperskill.org/projects/77?track=12

A Minesweeper game implemented in Java with a console-based interface, showcasing the use of fundamental data structures.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Data Structures](#data-structures)
- [How to Play](#how-to-play)

## Introduction

This Minesweeper game, implemented in Java, provides an engaging console-based experience. It not only delivers the classic Minesweeper gameplay but also serves as a practical example of using essential data structures in programming.

## Features

- Dynamic game board with customizable dimensions.
- Random mine placement utilizing the `HashSet` data structure.
- Efficient neighbor exploration using 2D arrays.
- Flag system for marking potential mine locations.
- Recursive cell revealing for efficient gameplay.
- Game-over and victory conditions.

## Data Structures

- **HashSet:** Used for storing mine positions, ensuring unique placement during randomization.
- **2D Array:** Represents the game board, simplifying cell manipulation and exploration.
- **StringBuilder:** Facilitates the construction of the game board display for efficient string handling.

## How to Play
Launch the game using the provided instructions.
Set the number of mines on the field.
Use commands to either set/unset mine marks or claim a cell as free.
Avoid uncovering cells with mines to prevent game loss.
Successfully flag all mines or uncover all safe cells to win.
