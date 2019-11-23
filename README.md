# nci-hdcomp-pbl

## Group Members

- Pablo Targa
- Shane Gibney
- Donald Hickey

Requirement:
Develop an application that would allow the user to play a lottery game. The game should generate 6 random secret lottery numbers between 1 and 40. Once the numbers have been chosen, the user should be permitted to enter up to three lines of 6 numbers each. These numbers should then be compared to the secret lottery numbers one line at a time. 

A lottery line is successful according to the criteria shown in Table 1:
Table 1 Lottery rules
Numbers matched on a line Winnings
- 3 numbers €125
- 4 numbers €300
- 5 numbers €1,500
- 6 numbers Won the Lottery

The application should allow the user to play the lottery game as many times as they want.

At the end of a game, the game should display the amount of numbers guessed correctly on each line played. Once a game has finished, the application asks the player if he/she would like to play another game. At the end of all the games, display a history of all games played. The history shows, for each game, the number of lines played, and the number of lines won, and the total winnings. 

Also, at the end of all the games, display a summary with the total number of games played and the average of winnings across all the games. All the history elements of the game should be coded using arrays.
For example, the history at the end of a game could be: 
  - if the secret lottery numbers are 2, 4, 6, 7, 9, 12 
  - and the user entered:
  - 1, 2, 3, 4, 5, 6 on their first line, 
  - and 2, 4, 6, 8, 9, 13 on their second line 
  - and 10, 20, 21, 22, 23, 24 on their third line, 
  - the program should output to the user: 
  - You guessed 3 numbers on line 1, 4 numbers on line 2 and 0 numbers on line 3.

Application Development:
To solve this problem, you shall form a group of 3 people.

Project Submission:
- The project will be submitted to Moodle on week 12. Please check Moodle for deadline.
- You need to submit only once per project team.

The project submission should include a project report outlining very clearly who has coded which part of the code. 

The report should include a description of the: 
- input, 
- main processing and 
- output (IPO), and 
- the class diagram for your application. 

Any decisions you take in designing and implementing your project should be specified in the report. 

The report should also include a section on manual testing, (describing testing scenarious and corresponding input data), that you performed to show that the game is working according to the specification (here you could include screenshots of the tests performed).

- A separate submission of a short summary that outlines the tasks contributed to the project by each team member is required to be submitted by each team member.
- The code should be commented to explain what the code is doing. The code should follow good programming practice e.g. naming conventions, indentations.
- A test will take place following the submission of your group project. This test will include questions based on the topic of the project and submitted application. Please check Moodle for the exact date of the in-class test.

Breakdown of marks
Table 2 presents the breakdown of marks for the Project

Table 2 Breakdown of marks
- Application = 12%
- Project Report = 1%
- In-class Test = 7%

## Main Objective

The lottery game flows as follows:

- Do you want to play?
- Enter numbers..
- Display 1st line of numbers entered
- Are you happy with these numbers? Y/N (If "No" go back and re-enter)
- Do you want to enter another line of numbers? Y/N (If "No" then..jump a step)
- Enter numbers..
- Display 2nd line of numbers entered
- Are you happy with these numbers? Y/N (If "No" go back and re-enter)
- Do you want to enter another line of numbers? Y/N 
- Enter numbers..
- Display 3rd line of numbers entered
- Are you happy with these numbers? Y/N (If "No" go back and re-enter)
- Close-off further input/play with "No-more-bets!" statement displayed)
- Now let's play!
- Display 6 numbers
- 34 23 01 21 14 09
- Well done! / Hard cheese!
- Display the one, two or three lines of numbers with winning numbers in different colour/highlighted (red) or bracketed
- (34) 06 (09) 37 (23) 10
- 40 22 12 08 (23) 17
- (34) 08 07 15 21 32
- You guessed 3 numbers on line 1, 1 number on line 2 and 1 number on line 3.
- If this was a real arcade or lottery game you would have had to pay to play and you would have won €125 for entering three winning numbers. 
- Do you want to play? Y/N
- You played the lottery 1 time (alternatively N times) with 3 lines entered 
- Your average winning per game was €125
- The size of the lottery began as €600,000 and it is now €599,875 (€600,000 - winnings)

## Main Classes

- Game
- Line
- Deck
- History

ERD
- Game 
- - has one Deck
- - has three Lines
- History
- - has many Games

Game:
Game
  -Deck secret
  -Line[3] lines
  -boolean locked
  ----------------------
  +add(Line l):boolean
  +noMoreBets():void
    This will lock up the game to not accept more lines, even if slots available

Line
  -int[6] nums
  -int matches
  -int matchesAmount
  ----------------------
  +add(int i): boolean
    check if i is in range
    check if i is not included on the line twice
  +isFull(): boolean
    used on do{...}while(!line.isFull())
  +compare(Line other)

Deck extends Line
  constructor(int positions, int min, int max)
    generate random numbers until isFull()

  -Game[] list
  ----------------------
  +store(Game g):void
  -expand():void

GamePrinter
  constructor(Game g)
  
LinePrinter
  constructor(Line l)

History

Memory that stores an array of games, it exposes a public function to store the game. 
This function may expand the games array into 10 more positions.

Outline Project Plan (as of 23/11/2019)

- Task Allocation
- Donald Hickey - Game
- Shane Gibney - Line
- Pablo Targa - Deck & History

Review outline plan on Monday 25/11/2019



