import React, { useEffect, useState } from 'react';
import axios from 'axios'; // Assuming you're using axios for HTTP requests
import './Scoreboard.css';

const Scoreboard = () => {
    const [players, setPlayers] = useState([]);

    useEffect(() => {
        // Fetch the scoreboard data from the API
        axios.get('/api/scoreboard')
            .then(response => {
                setPlayers(response.data);
            })
            .catch(error => {
                console.error('Error fetching the scoreboard data', error);
            });
    }, []);

    return (
        <div className="scoreboard-page">
            <h2 className="scoreboard-title">Scoreboard</h2>
            <table>
                <thead>
                <tr>
                    <th>Rank</th>
                    <th>Player name</th>
                    <th> Number of wins</th>
                </tr>
                </thead>
                <tbody>
                {players.map((player, index) => (
                    <tr key={player.id}>
                        <td>{index + 1}</td>
                        <td>{player.name}</td>
                        <td>{player.wins}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            <div className="homeButton">
                <a href="/">
                    <button>Home</button>
                </a>

            </div>
        </div>
    );
};

export default Scoreboard;