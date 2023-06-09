Group Project of Programming Fundamentals 2.

In this document you will find the following information;

0) A short explanation of how to run the game.
1) What this project is about (name, original concept) and why we chose to create this game.
2) Information about the libraries and plugins used.
3) The structure of the project and explanations for the most important mechanics.
4) Some of the challenges faced and what features we will be working on next.

To learn about the whole process and to see how the project evolved, please refer to the Milestone directory in the
repository. There you can find all of our meeting reports and notes.


## 0.0 Running the game

To run the game, go to src/app/game/Main and run the main() method.

## 1.0. Introduction

## 1.1. “Kubi”

This project is called "Kubi", which is a concatenation of the names "Kuma" and "Kibi". They will be the first playable
objects in the future local multiplayer.
However, so far it is more accurate to just call it a Baba is you clone, as we have not started drifting off of the
original game much.

-- The following section is also available in the Milestone 1 document --

## 1.2. “Baba is You”

We will now introduce the game we have chosen to replicate.
If you are already familiar with it, there might not be a real use of reading this section.
Then, we will talk about why we chose to do this and what our main expectations of it are.

You may have heard of, or played the quirky looking game “Baba is You”.
It is a fun, simplistic looking game based on boolean logic that has the player solving puzzles.
The player starts by playing as “Baba”, a cute small white character.


This is a 2D game where only basic “up, down, left, right” movements are possible.
The player has to push various elements on the map, some being objects, some being blocks of text, to manage to get into
a “win condition”. Blocks can be combined to create sentences
that will change the game’s rules, such as “Flag is Win”, which will make  touching (or *being*)  a flag a win condition.

While the game starts off quite easy, it quickly becomes a real challenge to solve puzzles, and can hardly be
done without carefully thinking through every step. In the beginning, we only have a few object types (wall, water,
rock, baba …) and limited connects (mainly “is”). More of them are unlocked going through the levels, including the
basic boolean “and”, “or”, which will sometimes create very non-intuitive situations. Overall, this is a great game
to introduce anyone of any age to logic, or to “workout” your brain on some difficult puzzles.

## 1.3. Why we chose this game

“Baba is You” is a great reminder that you don’t need unattainable resources and a lot of money to make a great game;
its graphics can be created using jtamaro, the overall game can be implemented in a simple and elegant fashion,
though the rules can quickly become a hell to sort out.

We found this game so creative and fun to play that we wanted to recreate it, maybe adding some of our own ideas into
it. However, this will only become real after we have managed to implement the basic aspects of it.

The boolean logic and rule handling felt like a real challenge, one we would have certainly underestimated without
the TA’s warnings. This is why we have decided to first start with a small set of objects and with only one connector
(”is”). Hopefully, we will be able to add new features and create more complicated puzzles in these following weeks.

-- End of overlapping of the section with document Milestone 1 --

## 2.0 Libraries and plugins used

## 2.1. “jtamaro”

The jtamaro library has been used to create this project. It provides an interactive framework (cf class jtamaro.en.IO)
that provides methods for input and output. These methods are impure and only have void as a return type. They however
have side effects such as printing a text, opening a window or creating a file.

## 2.2. Image converter plugin

To render the graphics, we are using a plugin created by our classmate, Xincong Tong. He bypassed the original Graphic
class.
// insert more explanations //


## 3.0. Project structure and core mechanics

## 3.1. Project structure

The project is divided between the model, the UI and the GUI.

The UI is located in the GameController class. The method onKeyPress() determines the change of behaviour of the program
depending on what key is pressed.

The GUI is located in the view package. The method that ties it up together is the GameView.render() method.

The model is found in the state package. The GameState contains all of the information that the UI and GUI need, but is
independent of them. It is important that the GameState hold a copy of the previous GameState to allow for undo operations.

## 3.2. Important mechanics

The trickiest part of the project was creating the move operations, how to generate the rules and how to apply them.

## 3.2.1 Rules

The rules are not stored anywhere, but generated when they are needed and thrown after their use is gone.
The rules can be represented as a directed graph, where vertices are Items and edges represent the relation "is".
There are three key methods to computing rules;

- Item.applyRules() which takes an Item and creates a new Item with the correct properties based on its Kind. Basically,
everytime a specific Item changes "identity" (i.e its name changes. For instance "Rock is Baba" turns every Item "Rock"
into an item "Baba"), all of the Items on the map that used to be of the past Kind must be transformed to new Objects.
That is what this method is used for.
- GameState.generateRules() which scans the gameMap after every move to see what rules are on it.
Rules are considered valid if we have a sentence of 3 consecutive "blocks" of items that form a valid rule.
The first element can be any text element that is NOT a description of a property (i.e win, push, stop etc...)
(and also it cannot be is), the second element is is, and the third element can be any text element.
Rules can be read from left to right or from top to bottom.
- GameState.applyRules() which links it all together; it calls the previous methods and also
sets certain items to the cancelled state (determined when there is a contradiction), makes texts that are
part of a valid rule light up and those who are not valid anymore dark.

## 3.2.2 Move operations

The move operation is divided into smaller chunks by the method "singleTileMove". This method checks whether
there is a boundary. If not, it proceeds with the evaluation.

## 3.2.3 Generating the map

To generate the map we are using the same technique that was used in lab9 where we map a character from a text file to
an Item of our game. In this way, we can write levels in txt files and quickly map them to their GameState equivalent.


## 4.0. Challenges encountered

## 4.1. Subtyping

The project's requirements asked for subtyping. Actually, we struggled to find a good place where to do that. We initially
thought we had to wait for the perfect place where it felt that what we wanted to do was impossible without it.
However, we have learnt that this is not what subtyping is about. Technically we could implement just about anything without
it. Changing the structure now in places where it feels logical to subtype cascades into lots of changes in our codes. Therefore,
we ended up implementing an interface in the graph package for textGraphics which all had similar methods that set them
to light or dark.
However, because these were called in a static context, we had to do some very naught things to end up having some sort of
a polymorphic call, which defeats the purpose of having them in the first place...

June 2 edit: We have created a new subtyping of Heading.


## 4.2. Figuring out which rules we wanted to implement

First, we started by playing lots of the original game to try and understand all possible weird combinations of rules and
situations there could be. We realized quite fast that this was not going to work. Whenever we felt that we had finally
gotten the rules down, one of us would send a 5 second clip that shattered our model. Therefore, we decided that the
best option was to set our own rules for the game (which are obviously very similar to the original Baba is you's rules).


## 4.3. Size of the window

As of now, we are still having issues to fix the size of the game window. As it is parametrized we can change it manually depending on the
display size but ths is clearly suboptimal and must be fixed.



## 4.4. What's next?

After we are done cleaning the code and fixing the display size, we will be creating a local multiplayer by adding a new keyboard listener
and you1 you2 items. On the graphical side, we will be drawing our own graphics, adopting a similar aesthetic to the background
image in game.
