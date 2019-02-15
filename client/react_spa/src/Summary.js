import React, { Component } from "react";
 
class Summary extends Component {

  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      invoiceItems: [],
      transactionsItems: [],
      accountItems: []
    };
  }

  componentDidMount() {
    // Fetch transactionSummary
    fetch('http://localhost:8080/transactionsSummary/1000')
        .then(res => res.json())
        .then(
            result => {
              this.setState({
                isLoaded: true,
                transactionsItems: result
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

    // Get Invoice Summary
    fetch('http://localhost:8080/invoiceSummary/1000')
        .then(res => res.json())
        .then(
            result => {
              this.setState({
                isLoaded: true,
                invoiceItems: result
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

    // Get account summary
    fetch('http://localhost:8080/accountSummary/1000')
        .then(res => res.json())
        .then(
            result => {
              this.setState({
                isLoaded: true,
                accountItems: result
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
    const { error, isLoaded, invoiceItems, transactionsItems, accountItems } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
          <div>

            <div><h2>Total Amount</h2><br />
                  <div class="table">
                    <div class="tr">
                      <div class="td">Total Amount : <font style={{color: accountItems.displayColor}} ><b>$ {accountItems.totalAmount}</b></font></div>
                      <div class="td">Threshold amount Set : $ {accountItems.threshold}</div>
                    </div>
                  </div>
            </div>

            <br /><br />

            <div><h2>Transaction Summary</h2><br />
              {transactionsItems.map(item => (
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

            <br /><br />

            <div><h2>Invoice Summary</h2><br />
              {invoiceItems.map(item => (
                <div className="rTable" key={item.invoiceId}>
                  <div className="rTableRow">
                    <div className="rTableHead">Invoice ID : {item.invoiceId}</div>
                    <div className="rTableHead">Client name : {item.invoiceClientName}</div>
                    <div className="rTableHead">Creation Date : {item.invoiceCreationDate}</div>
                    <div className="rTableHead">Invoice Unique ID : {item.invoiceUniqueNo}</div>
                    <div className="rTableHead">Invoice Creation Date : {item.invoiceCreationDate}</div>
                    <div className="rTableHead">Amount : {item.invoiceAmount}</div>
                    <div className="rTableHead">Status : {item.invoiceStatus}</div>
                    <br />
                  </div>
                </div>
            ))}
            </div>
          </div>

      );
    }
  }
}
 
export default Summary;