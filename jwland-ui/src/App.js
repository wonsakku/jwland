import logo from './logo.svg';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

import Header from "./layout/Header";
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { useState, useEffect } from 'react';
import routes from "./routes";
import * as jwt from "./jwt";

function App() {


  return (
    <Router>
      <Header />

      <div className="container mt-3">
        <Switch>
          {routes.map(route => {
            return <Route key={route.path} exact path={route.path} component={route.component} />
          })}

        </Switch>
      </div>

    </Router>
  )
}

export default App;
