import React from 'react'
import './Home.css';
//import logo from './logo1';

function Home(){
    
    return (
        <div className='container'>
            <h1 className="title1">Welcome to Tic-Tac-Toe!</h1>
          
            <div className="twoButtons">
                <div className="firstButton">
                    <a href="/About">How to play Tic-Tac-Toe?</a>
                </div>
                <div className="secondButton">
                    <a href="/GameOnePc">Start a game</a>
                </div>
            </div>
        </div>

    )
}

export default Home;