import React, { Component } from "react";

class Transactions extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      items: []
    };
  }

  componentDidMount() {
    fetch('http://localhost:8080/transactions/1000')
      .then(res => res.json())
      .then(
        result => {
          this.setState({
            isLoaded: true,
            items: result
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        error => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      );
  }

  render() {
    const { error, isLoaded, items } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <div>
          <div><h2>Transaction Details</h2><br />
            {items.map(item => (
                <div className="rTable" key={item.transactionId}>
                  <div className="rTableRow">
                    <div className="rTableHead">Transaction ID : {item.transactionId}</div>
                    <div className="rTableHead">Transaction unique No. : {item.transactionUniqueNo}</div>
                    <div className="rTableHead">Transaction Date : {item.transactionDate}</div>
                    <div className="rTableHead">Description : {item.transactionDesc}</div>
                    <div className="rTableHead">Amount : {item.transactionAmount}</div>
                  </div>
                  <br />
                </div>
            ))}
          </div>

        </div>
      );
    }
  }
}

export default Transactions;
