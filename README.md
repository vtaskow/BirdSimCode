# BirdSimCode - Java and Object-Oriented Software Engineering course assignment
BirdSim is a simulation board game that represents the flying and feeding behaviour of birds. A BirdSim Board is a rectangular grid of ”squares,” and a Piece is an object that can be placed on one of those squares and contains Birds or Grains. The current design of BirdSim enables the configuration of three different boards, each highlighting a unique bird flying and feeding behaviour.
## Contents
The initial design of BirdSim used inheritance to model most of its behaviour. This implementation resolves this issue by using the Strategy Design Pattern. The project consists of 3 branches.
```
1. Master branch contains the improved design using the aforementioned design pattern.
2. life_and_death branch contains additional flying behaviours of the birds.
3. gui_choice branch contains the code from life_and_death branch and an additional helped window implemented with Swing that facilitates the user to choose a board type and a flying behaviour of the birds.
```
## How to run it
Currently, only the GUI_choice branch contains an executable .jar file. The other branches can be run by cloning the repository and launching the Play.java file.
