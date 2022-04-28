# hcdd340final
Hello! You have found our final project for HCDD 340. This is a project where we build an app to store your recipies and grocery list all in one place. [Here](https://docs.google.com/presentation/d/16vVkRzxtoKs59somRgJMUaT8Yre56ttmBDhsnWImqKA/edit?usp=sharing) is a presentation for more background.

## Installation
In order to run this project you need to install [android studio](https://developer.android.com/studio/install). You can then download and open the code there and run it in an emulator. [This](https://developer.android.com/training/basics/firstapp/running-app) is a great tutorial to figure out how to run an emulator.

## Documentation
### Pages
1. Main Page
2. Grocery List
3. Add Grocery Item
4. Saved Recipes
5. Add New Recipe

All of these pages also support landscape viewing

### Overall structure 
 - Modules and Files
   * Each xml page is associated with a specific class
     1. Main Page (MainActivity)
         - Handle click methods are used to direct the user to the page they requested
     2. Grocery List (ShowGroceryActivity)
         - handleAddButtonClick is used to direct the user to the add grocery item page. It launches an intent
         - When the user returns the onActivityResult will run and change what is needs to based on the outcome
         - If an item was saved, populateGroceryList will add the saved item to the list
     3. Add Grocery Item (AddGroceryActivity)
         - If an item was saved, handleSave will take the tect and add it to shared preferences
         - If the event was cancelled it returns and nothing is saved
     4. Saved Recipes (ShowRecipeACtivity)
         - handleAddButtonClick is used to direct the user to the add recipe page. It launches an intent
         - When the user returns the onActivityResult will run and change what is needs to based on the outcome
         - If an item was saved, populateRecipeList will add the saved recipe to the list
     5. Add New Recipe (AddRecipeActivity)
         - If an item was saved, handleSave will take the tect and add it to shared preferences
         - If the event was cancelled it returns and nothing is saved

 - Major Functions
    * Our application has two functions, creating a grocery list and saving recipes. To create a gorcery list you can add an item and the app will list it on that given page. The same thing happens with the recipe pages as well.

### Extra Features 
 - Landscape Mode
    * For accessability reasons we generated landscape views of all of the screens.
 - Data Storage
    * Our app uses ashared preferences in order to store the information on gorceries and recipes.
 - Five Screens
    * In order to have the functionality for both the grocery list and the saved recipes we needed the associated add screens. This required us to design and build five screens as opposed to three.

## Contributors
- Developer
  * Hannah Kern: @hvk5388
- Designer
  * Neil Porterfield: @nzp126

