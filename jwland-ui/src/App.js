import logo from './logo.svg';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

import Header from "./layout/Header";
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { useState, useEffect } from 'react';
import routes from "./routes";
import * as jwt from "./jwt";

function App() {

  const [logined, setLogined] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    isLoginned();
  }, [logined]);

  const isLoginned = () => {
    setLogined(jwt.isLogined());
    setIsAdmin(jwt.isAdmin());
  }



  return (
    <Router>
      <Header isAdmin={isAdmin} logined={logined} />

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
