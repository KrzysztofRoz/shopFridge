# shopFridge
Program to create shopping list and list of products in your fridge.

ShopFridge class - main class, which also have some logic for test reasons and console interface. 
Product - base object to store information about quantity and real life product name
FridgeProduct - inherits product and extend it by adding min and max parameters to control quantity of products in our fridge
ShoppingList - base class which have list of products, have methods to find specific product via name and methods to manage products
Fridge - class which have list of fridge products, have methods to manage fridge products
ShoppingListEditor - class which contains methods to reduce excess products, and add missing one to the shopping list basing on fridge data
ListCreator - class contains logic to create a shopping list and fridge
EditView - class which contains methods to edit list, fridge and content (change name,dalate, change name and quantity of specyfic product on list etc.)
