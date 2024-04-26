import React from 'react'
import './Home.css';
import logo from './tictactoe-logo.png';

function Home(){
    
    return (
        <div className='container'>
            <h1 className="title1">Welcome to Tic-Tac-Toe!</h1>
            <img src= {logo}  alt="Tic-Tac-Toe"/>
            <div className="twoButtons">
                <div className="firstButton">
                    <a href="/About">How to play Tic-Tac-Toe?</a>
                </div>
                <div className="secondButton">
                    <a href="/Players">Start a game</a>
                </div>
            </div>
        </div>

    )
}

export default Home;