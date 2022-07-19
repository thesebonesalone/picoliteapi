package com.picolite.fakedb;

import com.picolite.models.Article;

import java.util.ArrayList;
import java.util.List;

public class FakeDB {

    public static List<Article> allArticles()
    {
        List<Article> articles = new ArrayList<>();
        articles.add(article1());
        return articles;
    }

    public static Article fetchArticle(Long id)
    {
        //uses int in fetch with fakeDB, will use long with full DB
        return allArticles().get((int)(id-1));
    }

    public static Article article1()
    {
        Article a = new Article();
        a.setId(1L);
        a.setTitle("How The Game Loop Works");
        a.setContent("<h2> Setting Up the Game Loop </h2>\n" +
                "\n" +
                "One of the most important pieces of a video game engine is the main game loop. This is a function that excecutes at continuous, predefined intervals. In the case of Pico-8 the game loop is executed once every 33.33 milliseconds, or 30 times per second.\n" +
                "\n" +
                "Pico-8 has a few built in functions that facilitate this behavior. They are\n" +
                "\n" +
                "*/*\n" +
                "\tfunction _init()\n" +
                "\n" +
                "\tfunction _update()\n" +
                "\n" +
                "\tfunction _draw()\n" +
                "*/*\n" +
                "\n" +
                "Each works as follows.\n" +
                "\n" +
                "_init() handles setting up the game loop. Here we will define variables that will be worked with in the loop. The main variable to define here is the entity list.\n" +
                "\n" +
                "_update() handles the logic operations for each of our game objects. If a button is pressed or an action happens, this is where it is calculated.\n" +
                "\n" +
                "_draw() is self explanatory. This is where all draw operations occur.\n" +
                "\n" +
                "Before we interact with any of these, we need to start defining our object structure using the principles or Object Oriented Programming.\n" +
                "\n" +
                "<h2> Defining the Base Object </h2>\n" +
                "\n" +
                "Before we jump into programming we need to talk about how Lua works. One of Lua’s core concepts is that of the table. A Lua table is similar to Java’s Hashmap, Javascript’s Object, or Python’s Dictionary. What this means is a table can contain many named keys that can hold arbitrary values that are easily accessed by the programmer. For instance.\n" +
                "\n" +
                "*/*\n" +
                "\tbasic_table = {\n" +
                "\tkey = 15,\n" +
                "\tkey2 = 25}\n" +
                "*/*\n" +
                "\n" +
                "We now have a table called “basic_table” that contains two keys. One called “key” that is equal to 15, and one called “key2” that is equal to 25. We can now access these values like so.\n" +
                "\n" +
                "*/*\n" +
                "\tbasic_table.key\n" +
                "*/*\n" +
                "\n" +
                "This will return 15. We can also assign new values to the the keys like so.\n" +
                "\n" +
                "*/*\n" +
                "\tbasic_table.key = 10\n" +
                "*/*\n" +
                "\n" +
                "Now, the “key” in “basic_table” is equal to 10 as opposed to 15. Most importantly we can pass functions as values. For instance\n" +
                "\n" +
                "*/*\n" +
                "\tbasic_table.key2 = function()\n" +
                "\t\tbasic_table.key = basic_table.key + 10\n" +
                "\tend\n" +
                "*/*\n" +
                "\n" +
                "Now every time we call basic_table.key2 we increment basic_table.key by 10. You will notice we assigned this function to an already defined key. Lua is not a strongly typed language, one variable could be a string, a number, a function or a table and there is nothing inherently stopping you from changing them. This can be both a blessing and a curse and it is generally recommended that all variables remain their original type. If you do not follow that plan you may experience unexpected behavior from your game.\n" +
                "\n" +
                "Now that we understand the basics of Lua tables, let’s talk about how we can apply this knowledge to Object Oriented Programming, hereby referred to as OOP. One of the four pillars of OOP is “Inheritance.” Objects can descend from other objects and inherit their behavior and structure. We call this the “Parent Child” relationship. Right now, we are going to define our parent object.\n" +
                "\n" +
                "*/*\n" +
                "\tfunction basic_object(x,y)\n" +
                "\t\treturn {\n" +
                "\t\t\tx = x, y =y,\n" +
                "\t\t\thsp = 0, vsp = 0,\n" +
                "\t\t\tsprite = 1,\n" +
                "\t\t\tupdate = function(this)\n" +
                "\n" +
                "\n" +
                "\t\t\tend,\n" +
                "\t\t\tdraw = function(this)\n" +
                "\t\t\t\tspr(this.sprite, this.x, this.y)\n" +
                "\n" +
                "\t\t\tend\n" +
                "\t\t}\n" +
                "\n" +
                "\tend\n" +
                "*/*\n" +
                "\n" +
                "Let’s break this down piece by piece. We have a function that returns a Lua table. The function takes two arguments, x and y. Further some additional information is placed in that table. Sprite determines which picture will be drawn during the draw phase. Update is a function that defines the behavior our object will have during the update phase, and the same happens respectively with the draw function. The upshot of this is that, if we store the return value of this function to a variable we can easily call its update and draw functions as needed. That object also contains the x and y position of the object relative to the screen. Finally it contains a couple interesting variables (hsp and vsp) that define how many pixels the object will move each frame.\n" +
                "\n" +
                "You will notice the update function does nothing at the moment. This is by design, we don’t want our basic object to hold any behavior, we want the child objects to define that behavior.\n" +
                "\n" +
                "** A note on organization. In other languages this is analogous to defining a Class. In Java, classes are normally written one per file to increase readability. Make use of the tab function in Pico-8 to help you stay organized and prevent yourself from constant, obnoxious scrolling! **\n" +
                "\n" +
                "Let’s go ahead and define our Player object.\n" +
                "\n" +
                "*/*\n" +
                "\tfunction player(x,y)\n" +
                "\t\tp = basic_object(x,y)\n" +
                "\t\tp.update = function(this)\n" +
                "\t\t\tthis.hsp = 0\n" +
                "\t\t\tthis.vsp = 0\n" +
                "\t\t\tif (btn(R)) this.hsp += 1\n" +
                "\t\t\tif (btn(L)) this.hsp -= 1\n" +
                "\t\t\tif (btn(U)) this.vsp -= 1\n" +
                "\t\t\tif (btn(D)) this.vsp += 1\n" +
                "\n" +
                "\t\t\tthis.x += this.hsp\n" +
                "\t\t\tthis.y += this.vsp\n" +
                "\t\tend\n" +
                "\n" +
                "\t\treturn p\n" +
                "\tend\n" +
                "*/*\n" +
                "\n" +
                "Ok, this one takes some explaining. In other languages specifically built around OOP a class would directly inherit from another class. Lua is not built this way and direct inheritance requires some finagling. Firstly, we are not inheriting directly, instead we are overwriting. We keep anything we want from the original table and change anything we don’t. In this case all we are doing is overriding the update function to give our player some behavior. We then return the changed object just like the basic_object above.\n" +
                "\n" +
                "Let’s focus on the variable “this”\n" +
                "\n" +
                "This is a representation of the instance of object we have created. We saw it in both of the above examples. Essentially each object needs a reference to itself to operate on.\n" +
                "\n" +
                "Now let’s return to the Game Loop.\n" +
                "\n" +
                "<h2> The Finalized Game Loop </h2>\n" +
                "\n" +
                "\n" +
                "*/*\n" +
                "\n" +
                "\tfunction _init()\n" +
                "\t\tgame_list = {}\n" +
                "\t\tadd(game_list, player(32,32))\n" +
                "\tend\n" +
                "\n" +
                "\tfunction _update()\n" +
                "\t\tfor obj in all(game_list) do\n" +
                "\t\t\tobj:update()\n" +
                "\t\tend\n" +
                "\tend\n" +
                "\n" +
                "\tfunction _draw()\n" +
                "\t\tfor obj in all(game_list) do\n" +
                "\t\t\tobj:draw()\n" +
                "\t\tend\n" +
                "\tend\n" +
                "\n" +
                "*/*\n" +
                "\n" +
                "Behold, the fruits of you labor. Let’s break it all down.\n" +
                "\n" +
                "In _init() we create an empty table called game_list. In this table we will store all of our game objects. We then add an instance of player to the game_list.\n" +
                "\n" +
                "In _update() we iterate over the entire game_list and execute each object’s update function. It’s worth touching on the syntax here. Obj:update() is the same as calling obj.update(obj), the way I typed it out is just “Syntactic Sugar” aka a preference for how your code looks. Syntactic Sugar is actually quite important in Pico-8 as the number of tokens taken up by different syntax could, eventually, become important!\n" +
                "\n" +
                "We should also touch on the “for loop” above. A for loop is a way of saying “for as long as this is true, do something.” In this case for as long as there are objects left in the game_list, continue to iterate over them. You can also define for loops to work based on a range of numbers. The cousin to this is the “while” loop which we will eventually cover.\n" +
                "\n" +
                "Finally we take a look at the _draw() function. As in the update function above, we iterate over the game_list and execute the draw function of each object.\n" +
                "\n" +
                "Viola! Go ahead and run your game, it isn’t much, but you’ve laid the foundations for essentially any game you can think of!\n" +
                "\n" +
                "In the next lesson we will go over collision detection and hitboxes while also learning more about the fundamentals of OOP. Happy coding!");
        a.setGameName("zelda");

        return a;
    }
}
