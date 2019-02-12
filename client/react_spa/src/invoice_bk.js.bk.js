import React, { Component } from "react";
 
/*
export function createBlogPost(data) {
    return fetch('http://localhost:8080/invoices/', {
        method: 'POST',
        body: JSON.stringify(data)
    }).then(res => {
        return res;
    }).catch(err => err);

}
*/

function validate(clientName, invoice_unique_id, invoice_creation_date, amount) {
  // we are going to store errors for all fields
  // in a signle array
  const errors = [];

  if (clientName.length === 0) {
    errors.push("Client Name in invoice can't be empty");
  }

  if (invoice_unique_id.length === 0) {
    errors.push("Invoice unique Number can't be empty");
  }

  if (invoice_creation_date.length != 8) {
    errors.push("Invoice creation date should be of format dd/MM/yyyy");
  }

  if (amount.length == 6) {
    errors.push("Amount can't be empty");
  }

  return errors;
}


class Popup extends React.Component {

  constructor() {
    super();
    this.state = {
      clientName: "",
      invoice_unique_id: "",
      invoice_creation_date: "",
      amount: "",
      status: "",

      errors: []
    };

    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(e) {
    e.preventDefault();

    const { clientName, invoice_unique_id, invoice_creation_date, amount } = this.state;

    const errors = validate(clientName, invoice_unique_id, invoice_creation_date, amount);
    if (errors.length > 0) {
      this.setState({ errors });
      return;
    }

    // submit the data...
    // calling the post api
    //createBlogPost(e);
    //this.props.router.push('/').bind(this);

  }

  render() {
    const { errors } = this.state;
    return (
      <div className='popup'>
        <div className='popup_inner'>
          <h1>{this.props.text}</h1>
          <div>
            <input type = "text" value = {this.props.myDataProp} 
               onChange = {this.props.updateStateProp} />
            <h3>{this.props.myDataProp}</h3>
         </div>

         <form onSubmit={this.handleSubmit}>
          {errors.map(error => (
          <p key={error}>Error: {error}</p>
          ))}

         <label for="clientName">Last Name : </label>
        <input value={this.state.clientName}
          onChange={evt => this.setState({ clientName: evt.target.value })}
          type="text"
          placeholder="clientName" id="clientName"/> 
          <br/>

          <label for="invoice_unique_id">Invoice Unique No.: </label>
        <input value={this.state.invoice_unique_id}
          onChange={evt => this.setState({ invoice_unique_id: evt.target.value })}
          type="text"
          placeholder="invoice_unique_id" id="invoice_unique_id"/>
          <br/>

          <label for="invoice_creation_date">Invoice Creation date (dd/MM/yyyy) : </label>
        <input value={this.state.invoice_creation_date}
          onChange={evt => this.setState({ invoice_creation_date: evt.target.value })}
          type="text"
          placeholder="invoice_creation_date" id="invoice_creation_date"/>
          <br/>

          <label for="amount">Invoice Amount : </label>
        <input value={this.state.amount}
          onChange={evt => this.setState({ amount: evt.target.value })}
          type="text"
          placeholder="amount" id="amount"/>
          <br/>


        <button type="submit">Submit</button>
        <button onClick={this.props.closePopup}>Cancel</button>
        <button onClick={this.props.closePopup}>Create</button>
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

  handleClick() {
    this.setState(state => ({
      isToggleOn: !state.isToggleOn
    }));
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
        <div>
            <button color="primary" onClick={this.togglePopup.bind(this)}>Create New Invoice</button>

            {this.state.showPopup ? <Popup text='Close Me' closePopup={this.togglePopup.bind(this)}/>: null}

          <ul>{items.map(item => (
              <li key={item.invoiceId}>
                <ul>Invoice ID : {item.invoiceId}</ul>
                <ul>Client name : {item.invoiceClientName}</ul>
                <ul>Creation Date : {item.invoiceCreationDate}</ul>
                <ul>Invoice Unique ID : {item.invoiceUniqueNo}</ul>
                <ul>Invoice Creation Date : {item.invoiceCreationDate}</ul>
                <ul>Amount : {item.invoiceAmount}</ul>
                <ul>Status : {item.invoiceStatus}</ul><br/>
            </li>
          ))}
          </ul>
        </div> 
      );
    }
  }
}

 
export default Invoice;
