import React from 'react'
import './Home.css';
import logo from './tictactoe-logo.png';

function Home(){
    
    return (
        <div className='container'>
            <h1 className="title1">Welcome to Tic-Tac-Toe!</h1>
            <img src= {logo}  alt="Tic-Tac-Toe"/>
            <div className="home-buttons">

                <div className="firstButton">
                    <a href="/About">
                        <button>How to play Tic-Tac-Toe?</button>
                    </a>
                </div>

                <div className="secondRowButtons">
                    <div className="secondButton">
                        <a href="/Players">
                            <button>Start a game</button>
                        </a>
                    </div>
                    <div className="scoreButton">
                        <a href="/Scoreboard">
                            <button>Scoreboard</button>
                        </a>
                    </div>

                </div>

            </div>
        </div>

    )
}

export default Home;