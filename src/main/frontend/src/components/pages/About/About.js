import React from 'react'
import  './About.css'
function About(){
    return(
        <div>
            <h1>How to play Tic-Tac-Toe?</h1>
            <div className= "frame">
            <p><strong>1.</strong> The game is played on a grid that's 3 squares by 3 squares.</p>
                <p><strong>2.</strong> You are X, your friend is O. Players take turns
                    putting their marks in empty squares.</p>
                <p><strong>3.</strong> The first player to get 3 of her marks in a row (up, down, across, or diagonally)
                    is the winner.</p>
                <p><strong>4.</strong> When all 9 squares are full, the game is over. If no player has 3 marks in a row,
                    the game ends in a draw.</p>
            </div>
            <div className="firstButtonInAbout">
                <a href="/">Home</a>
            </div>
        </div>
    )
}
export  default About;