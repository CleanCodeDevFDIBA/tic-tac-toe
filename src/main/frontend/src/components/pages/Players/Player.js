import './Player.css'
import React, { useState } from 'react';
import axios from 'axios';

function Player() {
    const [player1Name, setPlayer1Name] = useState('');
    const [player2Name, setPlayer2Name] = useState('');

    const checkIfNamesAreFilled = () => {

        if (player1Name.trim() !== '' && player2Name.trim() !== '' ){
            console.log('Player 1:', player1Name);
            console.log('Player 2:', player2Name);
            return true;
        }
        return false;
    };

    const createGame = (username1, username2) => {
      try {
        let gameDataJSON = {
            player1: username1,
            player2 : username2
        }

       axios.post('http://localhost:8080/api/createGame', gameDataJSON)
       .then(response => {
           if (response.status >= 200 && response.status < 300) {
             // The request was successful
             console.log('Response data:', response.data);
           } else {
             // The request was not successful
             console.error('Request failed with status:', response.status);
           }
         });
      } catch (error) {
        console.error('Error creating game:', error);
      }
    };

    const initialiseGame = () => {
        let areUsernamesFilled = checkIfNamesAreFilled();
        if (areUsernamesFilled) {
            createGame(player1Name, player2Name);
            window.location.href = 'http://localhost:3000/GameOnePC';
        }

      };

    return (
        <div className='container'>
            <h1>Enter Players names!</h1>
            <div className="player-names">
                <label className="player1">
                    Player 1 (X):
                    <input
                        type="text"
                        required
                        value={player1Name}
                        onChange={(e) => setPlayer1Name(e.target.value)}
                    />
                </label>

                <label>
                    Player 2 (O):
                    <input
                        type="text"
                        required
                        value={player2Name}
                        onChange={(e) => setPlayer2Name(e.target.value)}
                    />
                </label>
            </div>

            <div className="buttons">
                <div className="startGameButton">
                    <button onClick={initialiseGame}>Start the game</button>
                </div>
                <div className="homeButton">
                    <a href="/">Home</a>
                </div>
            </div>
        </div>
    );
}

export default Player;
