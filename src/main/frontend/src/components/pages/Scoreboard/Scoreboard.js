import React, { useEffect, useState } from 'react';
import axios from 'axios'; // Assuming you're using axios for HTTP requests
import './Scoreboard.css';

const Scoreboard = () => {
    const [players, setPlayers] = useState([]);

    useEffect(() => {

        axios.get('http://localhost:8080/api/scoreboard')
            .then(response => {
                setPlayers(response.data.scoreboard);
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
                    <tr key={index}>
                        <td>{player.ranking}</td>
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