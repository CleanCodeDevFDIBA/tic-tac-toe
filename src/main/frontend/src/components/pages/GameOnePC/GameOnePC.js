import React from 'react'
import  './GameOnePC.css'
//import circle_icon from './Assets/circle-image.png'
//import cross_icon from './Assets/cross-image.png'

//let data = []
function GameOnePc(){
    return(
        <div className= 'container'>
            <h1 className= "title">Tic-Tac-Toe Game</h1>
            <div className="board">
                <div className="row1">
                    <div className="boxes"></div>
                    <div className="boxes"></div>
                    <div className="boxes"></div>
                </div>
                <div className="row2">
                    <div className="boxes"></div>
                    <div className="boxes"></div>
                    <div className="boxes"></div>
                </div>
                <div className="row3">
                    <div className="boxes"></div>
                    <div className="boxes"></div>
                    <div className="boxes"></div>
                </div>
            </div>
            <button className="reset">Reset</button>
        </div>
    )
}

export default GameOnePc;