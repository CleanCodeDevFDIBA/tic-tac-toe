import React from "react";
import "./App.css";

import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import Home from "./components/pages/Home/Home";
import About from "./components/pages/About/About";
import GameOnePc from "./components/pages/GameOnePC/GameOnePC";
//import SignUp from "./pages/signup";
//import Contact from "./pages/contact";

function App() {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<Home />} />
                <Route path="/About" element={<About />} />
                <Route path="/GameOnePC" element={<GameOnePc />} />
            </Routes>
        </Router>

    );
}


export default App;