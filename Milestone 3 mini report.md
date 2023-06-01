# Milestone3 by Group-Alpha

Created by: Jamila Oubenali
Created time: May 21, 2023 7:23 PM
Last edited by: Jamila Oubenali
Last edited time: May 30, 2023 10:36 PM

# 

## Changes made

- **Moving methods that had graphic return types to the view section of the project**. Namely;
    - Item.toGraphic(): was previously in the Item class. We had to make it a static method since it changed classes.
    - Tile.toGraphic(): was previously in the Tile class. Made it static.
- **Added a new field for Item objects:** Heading. This was needed in order to compute what heading we should render the baba’s with. This included the following changes:
    - Adapted all the current methods using Items to add the new field
    - Added a heading declaration in Tile.fromChar(). Convention: By default, all items have a Heading.SOUTH heading
- methods in GameController were made public so they can be used in the GameState class
- New classes were created:
    - Interface Stage which represents the different stages the program can be in
    - its subclasses (can I say that?): GameStage and MenuStage. In this way we can use methods to distinguish them when needed with the controllers
- Methods GameController.hasLost() and GameController.hasWon() now take ArrayList<ArrayList<Tile>> as parameters. This facilitates calling the method in the move methods in the GameState

### Designing the appstate

What information we need in the appstate:

- We need to know whether a game is won or lost
- We need to know if we are in a menu or in a game
- We need to know in what level we are in

so maybe

Main menu:

Level choice menu:

in game:

in game menu: (?)

gameState contains: gameMap, boolean gameWon, boolean gameLost. identification (which level) 

if menu is on: overlay and show menu, keys effect changes

if game is on: keys act in a certain way

appstate:

gameState

Stage —> enum: IN_GAME_MENU, IN_GAME, LEVEL_SELECT

if(appstate.gameState.gameWon == true) {

say something

Stage → LEVEL_SELECT

}

if(appstate.gameState.gameLost == true) {

say something

Stage → this level again

}

### Feedback

Instead of having lots of if statements, put the code inside what was an enum (now, one class per item in the enum) 

Calling

Stage.render() through polymorphic

graphs into view

Something we couldve done (not enough time but good for the future) Class with a field for a light graphic, dark graphic etc 

### Feedback from 26.05.23

- We don-t need the Stage typing/subtyping.
- It is good to have the menu that Zhiyao designed so it gives a preview of the rules of the game
- Question: How should we design a select menu?
- items that have special attributes —> subtype of item, we cant subtype record because they are implicitely final. We can make the common aspect into an interface and make two subtypes, one level select item and one regular item