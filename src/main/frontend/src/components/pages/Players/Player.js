import React from 'react';
import './Player.css'

    function Player() {
        return (
            <div className='container'>
                <h1>Enter Players names!</h1>
                <div className="player-names">

                    <label className="player1">
                        Player 1 (X):
                        <input
                            type="text"
                            //               onChange={(e) => setPlayer1Name(e.target.value)}
                        />
                    </label>

                    <label>
                        Player 2 (O):
                        <input
                            type="text"
                            //               onChange={(e) => setPlayer2Name(e.target.value)}
                        />
                    </label>


                </div>

                <div className="buttons">
                    <div className="startGameButton">
                        <a href="/GameOnePC">
                            <button>Start a game</button>
                        </a>
                    </div>

                    <div className="homeButton">
                        <a href="/">
                            <button>Home</button>
                        </a>
                    </div>
                </div>

            </div>


        )

    }

export default Player;