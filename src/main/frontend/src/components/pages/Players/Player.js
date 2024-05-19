import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Player.css';

function Player() {
    const [player1Name, setPlayer1Name] = useState('');
    const [player2Name, setPlayer2Name] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleStartGame = async (e) => {
        e.preventDefault();
        if (player1Name.trim() === '' || player2Name.trim() === '') {
            setError('Both player names must be entered!');
//            e.preventDefault();
        } else if (player1Name.trim() === player2Name.trim()) {
            setError('Players must have different names!');
//            e.preventDefault();
        } else {
            setError('');
            navigate('/GameOnePC', { state: { player1Name, player2Name } });

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
            <div className="buttons-container">
                <div className="homePageButton">
                    <a href="/">
                        <button>Home</button>
                    </a>
                </div>
                <div className="startGameButton">
                    <a href="/GameOnePC" onClick={handleStartGame}>
                        <button>Start game</button>
                    </a>
                </div>

            </div>
        </div>
    );
}

export default Player;
