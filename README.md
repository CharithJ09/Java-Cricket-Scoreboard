Java Cricket Scoreboard
This project simulates a cricket match scoreboard in Java. It allows users to manage and track the progress of a cricket match, including team details, player stats, runs, wickets, overs, and required run rates. Users can input match events (such as runs, wickets, and extras) in real time, and the system updates the scoreboard accordingly.

Features
Team Setup: Users can input the team names, number of players per team, and the number of batsmen for each team.
Match Configuration: The match duration can be set with the number of overs and balls per over.
First Innings: The first team bats, while the second team bowls. The user inputs the events for each ball (runs, wickets, wides, no-balls, etc.), and the system keeps track of the score.
Second Innings: The second team tries to chase the target set by the first team. The scoreboard calculates and displays the Required Run Rate (RRR) for the chasing team.
Real-Time Scoreboard: The scoreboard dynamically updates to show the current score, batsman details, bowler performance, runs scored, wickets fallen, overs bowled, and the required run rate.
Match Summary: At the end of the match, a summary of the match result is displayed, including the final scores and the winner.
How It Works
Team Setup:

The user is prompted to enter the names of both teams, the number of players per team (up to 11), and the number of batsmen.
Player names are then added for each team.
Match Configuration:

The user sets the over limit for the match and the number of balls per over (up to 6).
The batting and bowling teams are selected based on user input.

First Innings:
The first team bats, and the second team bowls. The user is prompted to enter the decision for each ball, such as:
Runs (1, 2, 3, 4, 6)
Dot Balls
Wicket (W)
Extras (Wide, No Ball)
The system updates the scoreboard dynamically after each ball, showing the runs scored, wickets fallen, and overs completed.

Second Innings:
The second team bats, aiming to chase the target set by the first team.
The system continuously updates the scoreboard, calculating the current run rate (CRR) and the required run rate (RRR) for the chasing team.
The second innings continues until the chasing team scores the required runs, or all wickets are taken.

Match Conclusion:
A final summary is displayed, showing the total runs scored by both teams, the result, and the match details.

