import React from 'react'
import  './About.css'
function About(){
    return(
        <div>
            <h1>How to play Tic-Tac-Toe?</h1>
            <div className= "frame">
                <p>1. The game is played on a grid that's 3 squares by 3 squares.</p>
                <p>2. You are X, your friend (or the computer in this case) is O. Players take turns putting their marks in empty squares.</p>
                <p>3. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.</p>
                <p>4. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.</p>
            </div>
        </div>
    )
}
export  default About;