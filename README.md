# java_labirentoyunu
Maze Game Project Report

1. Project Summary:
The purpose of this project is to develop a game that allows reaching the finish point with the least number of moves by navigating paths without walls on the maze.

2. Project Details:
Data Structures:
Array structures were used for step count and bonuses. The reference type of these data structures allows for more effective management of the project.

Maze Shuffling:
The maze shuffling process is carried out using the Random Shuffling method. This ensures that the maze is created differently each time.

Step Count and Move Count:
Two separate arrays are used to track the step count and move count. The move count increases only when moving with the 'w', 'a', 's', 'd' keys and updates the map every five moves. The step count increases when moving with the 'w', 'a', 's', 'd' keys and is also used in other processes.

Character Position:
A character represented by '?' has been added to the maze. This character helps users understand their position more easily.

User Input:
Letters taken from the user are accepted and processed as uppercase.

3. Results and Future Steps:
The project includes the basic features of the maze game and offers an interactive gaming experience to the user.

4. Project Outputs:
The map changes when the user makes the fifth move:
When the user encounters a wall and does not have a wall removal bonus:
If the user has a wall removal bonus:

When the user uses the T bonus:

When the user encounters a mine and has a mine disarming bonus:
If the user does not have a mine disarming bonus:

When the user uses the move reduction bonus:

5. Project Improvements and Fixed Issues:
Some important points that were considered and fixed during the project development phase are listed below:

Maze Boundaries:
Controls have been added to address the issue of going outside the maze. The player cannot go outside the maze boundaries, preserving the game's logic.

Move Reduction Bonus:
The issue of the move count not falling below zero during the application of the move reduction bonus has been resolved. Now, the move count will not fall to negative values, preserving the game's logic.

Maze Shuffling Sequence:
During the maze shuffling, mines and similar items are checked first, followed by the maze shuffling. This arrangement ensures that the current situation is checked first, and necessary actions are taken.

Mine and Bonus Position:
During the maze shuffling, mines and bonuses are prevented from appearing at the character's position.
