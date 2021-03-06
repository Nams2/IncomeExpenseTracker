import React, { Component } from "react";


class Location extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      items: []
    };
  }

  componentDidMount() {
    fetch("http://localhost:8080/invoices/1000")
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            items: result
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }

  render() {
    const { error, isLoaded, items } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <ul>
          {items.map(item => (
            <li key={item.invoiceId}>
                    <ul>Invoice ID : {item.invoiceId}</ul>
                    <ul>Client name : {item.invoiceClientName}</ul>
                    <ul>Creation Date : {item.invoiceCreationDate}</ul>
                    <ul>Invoice Unique ID : {item.invoiceUniqueNo}</ul>
                    <ul>Invoice Creation Date : {item.invoiceCreationDate}</ul>
                    <ul>Amount : {item.invoiceAmount}</ul>
                    <ul>Status : {item.invoiceStatus}</ul>
            </li>
          ))}
        </ul>
      );
    }
  }
}


export default Location;