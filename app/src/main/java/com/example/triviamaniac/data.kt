package com.example.triviamaniac

class Triv (
    val question: String,
    val optionCorrect: String,
    val optionIn1: String,
    val optionIn2: String,
    val optionIn3: String
        ){

}

object values {
    val data = listOf<Triv>(
        Triv("What year was the game Overwatch revealed?", "2014", "2015", "2011", "2008"),
        Triv(
            "What's Harry Potter's dad's name?",
            "James Potter",
            "Joey Potter",
            "Frank Potter",
            "Hairy Potter Sr."
        ),
        Triv("Which Species is a mountain Chicken?", "Frog", "Chicken", "Horse", "Fly"),
        Triv(
            "The Alps are a mountain range on which continent?",
            "Europe",
            "North America",
            "Asia",
            "Africa"
        ),
        Triv("What is 2+2?", "4", "6", "7", "22")
        , Triv("What Greek letter is used to signify summation?","Sigma","Delta","Alpha","Omega")
        ,Triv("Which studio made Cowboy Bebop?","Sunrise","Bones","Madhouse","Pierriot")
        ,Triv("What disease crippled President Franklin D. Roosevelt and led him to help the nation find a cure? ",
            "Polio","Cancer","Meningitis","HIV")
        ,Triv("In June 2017, Saudi Arabia and Egypt broke off ties with which country over its supposed support for terrorism?",
        "Qatar","United States of America","Bahrain","Russia")
        ,Triv("Which of these is a type of monster found in Minecraft?","Skeleton","Werewolf","Vampire","Minotaur")
    )

}