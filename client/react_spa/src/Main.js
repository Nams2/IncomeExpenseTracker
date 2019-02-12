import React, { Component } from "react";
import {
  Route,
  NavLink,
  HashRouter
} from "react-router-dom";
import Transactions from "./Transactions";
import Invoice from "./Invoice";
import Summary from "./Summary";


class Main extends Component {
  render() {
    return (
      <HashRouter>
        <div>
          <h1><center>Income And Expense Tracker</center></h1>
          <ul className="header">
            <li><NavLink exact to="/">Account Summary</NavLink></li>
            <li><NavLink to="/transactions">Transactions Details</NavLink></li>
            <li><NavLink to="/invoice">Invoice Details</NavLink></li>
          </ul>
          <div className="content">
            <Route exact path="/" component={Summary}/>
            <Route path="/transactions" component={Transactions}/>
            <Route path="/invoice" component={Invoice}/>
          </div>
        </div>
      </HashRouter>
    );
  }
}
 
export default Main;