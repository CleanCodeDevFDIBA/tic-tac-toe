import React, { useState } from 'react';
import './Player.css';

function Player() {
    const [player1Name, setPlayer1Name] = useState('');
    const [player2Name, setPlayer2Name] = useState('');
    const [error, setError] = useState('');

    const handleStartGame = (e) => {
        if (player1Name.trim() === '' || player2Name.trim() === '') {
            setError('Both player names must be entered!');
            e.preventDefault();
        } else if (player1Name.trim() === player2Name.trim()) {
            setError('Players must have different names!');
            e.preventDefault();
        } else {
            setError('');

        }
    };

    return (
        <div className='container'>
            <h1>Enter Player names!</h1>
            <div className="player-names">
                <label className="player1">
                    Player 1 (X):
                    <input
                        type="text"
                        value={player1Name}
                        onChange={(e) => setPlayer1Name(e.target.value)}
                    />
                </label>
                <label>
                    Player 2 (O):
                    <input
                        type="text"
                        value={player2Name}
                        onChange={(e) => setPlayer2Name(e.target.value)}
                    />
                </label>
            </div>
            {error && <div className="error-message">{error}</div>}
            <div className="buttons">
                <div className="startGameButton">
                    <a href="/GameOnePC" onClick={handleStartGame}>
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
    );
}

export default Player;
