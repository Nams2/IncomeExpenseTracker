import React, { Component } from "react";

export function createInvoicePost(event) {
    //const data = new FormData(event.target);
    console.log(event);
    return fetch('http://localhost:8080/invoices/', {
        method: 'POST',
        body: JSON.stringify(event),
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
    }).then(res => {
        return res;
    }).catch(err => err);
}


function validate(invoiceClientName, invoiceUniqueNo, invoiceCreationDate, invoiceAmount) {
  // we are going to store errors for all fields
  // in a signle array
  const errors = [];

  if (invoiceClientName.length === 0) {
    errors.push("Client Name in invoice can't be empty");
  }

  if (invoiceUniqueNo.length === 0) {
    errors.push("Invoice unique Number can't be empty");
  }

  if (invoiceCreationDate.length != 10) {
    errors.push("Invoice creation date should be of format dd/MM/yyyy");
  }

  if (invoiceAmount.length == 0) {
    errors.push("Inoice Amount can't be empty");
  }

  return errors;
}

function closePop() {
    document.getElementById("btn_create_invoice_close").click();
}


class Popup extends React.Component {

  constructor() {
    super();
    this.state = {
      userId: "",
      invoiceClientName: "",
      invoiceUniqueNo: "",
      invoiceCreationDate: "",
      invoiceAmount: "",
      invoiceStatus: "",

      errors: []
    };

    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleClick() {
    this.setState({
      showPopup: !this.state.showPopup
    });
  }



  handleSubmit(e) {
    //e.preventDefault();

    const { invoiceClientName, invoiceUniqueNo, invoiceCreationDate, invoiceAmount } = this.state;

    const errors = validate(invoiceClientName, invoiceUniqueNo, invoiceCreationDate, invoiceAmount);
    if (errors.length > 0) {
      this.setState({ errors });
      return;
    }

    // submit the data...
    // calling the post api
    createInvoicePost(this.state);
    this.state.callback();
    closePop();
  }

    render() {
    const { errors } = this.state;
    return (
      <div className='popup'>
        <div className='popup_inner'>
          <h1>{this.props.text}</h1>

         <form onSubmit={this.handleSubmit}>
          {errors.map(error => (
          <p key={error}>Error: {error}</p>
          ))}

          <label htmlFor="userId"> User Id : </label>
        <input value={this.state.userId}
          onChange={evt => this.setState({ userId: evt.target.value })}
          type="text"
          placeholder="userId" id="userId"/>   
          <br/>

          <label htmlFor="invoiceClientName"> Invoice Client name : </label>
        <input value={this.state.invoiceClientName}
          onChange={evt => this.setState({ invoiceClientName: evt.target.value })}
          type="text"
          placeholder="invoiceClientName" id="invoiceClientName"/> 
          <br/>

          <label htmlFor="invoiceUniqueNo">Invoice Unique No.: </label>
        <input value={this.state.invoiceUniqueNo}
          onChange={evt => this.setState({ invoiceUniqueNo: evt.target.value })}
          type="text"
          placeholder="invoiceUniqueNo" id="invoiceUniqueNo"/>
          <br/>

          <label htmlFor="invoiceCreationDate">Invoice Creation date (dd/MM/yyyy) : </label>
        <input value={this.state.invoiceCreationDate}
          onChange={evt => this.setState({ invoiceCreationDate: evt.target.value })}
          type="text"
          placeholder="invoiceCreationDate" id="invoiceCreationDate"/>
          <br/>

          <label htmlFor="invoiceAmount">Invoice Amount : </label>
        <input value={this.state.invoiceAmount}
          onChange={evt => this.setState({ invoiceAmount: evt.target.value })}
          type="text"
          placeholder="invoiceAmount" id="invoiceAmount"/>
          <br/>


        <center><button id="create_invoice" type="submit">Create New Invoice</button>
        <button id="btn_create_invoice_close" type="button" onClick={this.props.closePopup}>Close</button></center>
      </form>

        
        </div>
      </div>
    );
  }
}

class Invoice extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showPopup: false,
      error: null,
      isLoaded: false,
      items: []
    };
  }

  togglePopup() {
      this.setState({
      showPopup: !this.state.showPopup
      });
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

    componentWillReceiveProps = (props) => {
        if(props.hash !== this.props.hash){
            console.log('route reload');
        }
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
            <center>
                <button className="button button2" type="button" onClick={this.togglePopup.bind(this)}>Create New Invoice</button>
            </center>

            {this.state.showPopup ? <Popup text='Create New Invoice' closePopup={this.togglePopup.bind(this)}/>: null}

            <div><h2>Invoice Details</h2><br />
                {items.map(item => (
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

 
export default Invoice;
