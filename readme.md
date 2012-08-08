# SimpleMail v2.0  

### Contents  
1. SimpleMail help
2. Changes from v1.0  
3. New features in v2.x  
4. Potential features/to-do  

***

## 1. SimpleMail help

SimpleMail v2.x has one catch-all command thus far: `/mail`  

- `/mail help [topic]`  
    Access the in-game SimpleMail help.

- `/mail send <target> [priority:#] <message>`  
    Send a message. Replace `[priority:#]` with the desired priority.  
    (Example: `/mail send x3chaos priority:3 Fix my server for me!`)

- `/mail get`  
    Get and read all of your unread messages.

_(Note: items inside_ `<these>` _are required, whereas items inside_ `[these]` _are optional.)_    

## 2. Changes from v1.0

#### Taming the 1.3.1 monster update

Minecraft 1.3.1 consisted of a few bug fixes, some launcher updates, some behind-the-scenes stability upgrades, a ton of single-player updates including optional toggleable LAN hosting, and a bunch of additions to multiplayer functionality. At first, it might not seem like that big of a deal, until you realize that _the Bukkit team had to update everything as well_. They did (and are still doing) a damn fine job, and their work makes the plugin devs' work easier. 

SimpleMail v2.x is fully compatible and tested with 1.3.1.

_(Note: at the time of this writing, the current CraftBukkit version is CB 1.3.1-R1.0. By the time that the final version of this write-up is published, CraftBukkit will probably have been updated at least once. Such is life.)_

#### Credit where credit is due

While writing horribly underdone one-class Bukkit plugins, I got a lot of help and pointers from [l3eta][1], and the code for v2.x definitely shows it. In fact, the entirety of the ``org.l3eta`` package is comprised of (adapted) code gotten from l3eta.

#### Documentation

v1.0 had almost zero documentation built-in, which probably helped contribute to the clutter in the -ahem- two classes. With v2.x, documentation is built-in like it should be.

#### Total code rewrite  

This topic has two parts: one for those with no experience in programming or coding, and one for those who do.

###### The "how do I writes codes :(" part  

In the v1.0 releases, SimpleMail used a system that was so simple that it was complicated, if that makes sense. For me, from the perspective of the main developer, it was impossible to read and keep up with my own code. So I released what I had and trashed everything else, and after a month of taking time off with JavaScript, I got back to work and cranked out a releasable SimpleMail v2.0.

###### The "I'm definitely a better programmer than x3chaos" part  

Firstly, you probably are. :P

SimpleMail v1.0 used only two classes - the main class extending `org.bukkit.plugin.java.JavaPlugin` and the implementation of the `CommandExecutor` interface. While I'm a big believer in consolidating several extra classes into one bigger class, the main class was turning into [a mass of kludges and spaghetti code][2] and was almost impossible for me to keep track of, and the `CommandExecutor` wasn't holding up much better. So, I trashed the whole thing after releasing 1.0 via BukkitDev and the Bukkit forums and fumed for about a month (that's a lie, of course; four hours of it consisted of fuming, immediately followed by sleep and later picking up JavaScript and spending a month writing various Turntable bots). And after about a week of sitting down and fiddling with code, we have SimpleMail v2.0.

Admittedly, the v2.x release only slightly fixes the readability issue. The addition of separate classes for separate processes and the `Message` class only makes it a _little_ easier for me to keep up with my own code, but it makes it worlds easier to debug.  

Oh, and that reminds me: there will be more original classes introduced as time progresses. As of now, the only utilized class is `org.x3.mail.util.Message`, but the addition of permissions support and other fun features will undoubtedly be followed by the addition of more objects. This coding stuff is fun!

***

## 3. New features in v2.x

#### Message priority and sorting

SimpleMail v2.x allows a message to be given a priority using the optional [priority:int] argument. SimpleMail will then take this given priority and sort the recipient's inbox by priority.  

The 5 usable message priorities are as follows:

- `Lowest`: 0
- `Low`: 1
- `Normal`: 2 (default)
- `High`: 3
- `Highest`: 4

If an invalid priority is entered or one is not specified, the priority will default to `Normal`.

I plan to integrate a sixth message priority option (`Report`), which will be only be available through a planned "report" function. More info on that later.

#### `Message` objects

To the average Bukkit player, this might not mean much, and really it's not that big of a deal as far as innovation.  
Essentially, instead of only saving the text of the message like in v1.0, SimpleMail now saves the sender, recipient, text, priority, and any errors that the player made have made in entering the command (like using `priority:HIGH` instead of `priority:3`). This makes the entire system more flexible and easier to work with, especially from the developer's point of view.

#### Command parser

I've been wanting to try my hand at writing a command parser, and this is definitely a great time for it. For right now, the parser really only handles the message's priority.  

Currently, whenever a message is sent, a new parser is created for that message, which is then used to create the `Message` object.

***

## 4. Potential features/to-do

Most of these are features that I plan on putting in once I'm comfortable with the plugin as its basic functionality stands. Essentially, I want to make sure that the action sending and receiving messages is comfortable before I make it fancy.

#### Colors

Chat colors will eventually be a must. My first thought is the brute-force regex approach - running the message through a regular expression filter to replace codes with colors - but I'm going to keep looking into a better, more streamlined way of doing this (unless Bukkit will do it for me, in which case I won't bother).

#### Pagination

Think the vanilla `/help` command. I'm going to look through Bukkit's source and find out how they do the pagination for `/help`, and try to emulate it.

#### Mailboxes

I eventually want to create a system consisting of several mailboxes (inbox, trash, starred, etc.) and incorporate it into `/mail get`. This isn't hard, but it will involve some minor rewriting.  

#### Permissions

A previous copy of v2.0 (now trashed, of course) used the basic built-in Bukkit permission system to protect admins and limit who could send messages to whom. That will be reinstated, of course, but like I wrote above, it's being put off until I'm comfortable with how the plugin works.

***

######SimpleMail v2.x &copy; 2012 x3chaos (Shawn Lutch). Last README update at 11:57 on 8/8/12.

[1]: http://github.com/l3eta
[2]: http://www.xkcd.com/844