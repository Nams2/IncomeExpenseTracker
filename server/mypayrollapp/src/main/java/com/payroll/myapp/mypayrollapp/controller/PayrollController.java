package com.payroll.myapp.mypayrollapp.controller;

import com.payroll.myapp.mypayrollapp.model.AccountSummary;
import com.payroll.myapp.mypayrollapp.model.Invoice;
import com.payroll.myapp.mypayrollapp.model.Transactions;
import com.payroll.myapp.mypayrollapp.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nmehta on 2/8/19.
 */

@CrossOrigin
@RestController
public class PayrollController {

    @Autowired
    PayrollService service;


    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllInvoiceForUser(@PathVariable("id") Long id) {
        List<Invoice> invoices = service.getAllInvoices(id);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllTransactionsForUser(@PathVariable("id") Long id) {
        List<Transactions> transactions = service.getAllTransactions(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @RequestMapping(value = "/invoiceSummary/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getInvoiceSummaryForUser(@PathVariable("id") Long id) {
        List<Invoice> invoices = service.getInvoicesSummary(id);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @RequestMapping(value = "/transactionsSummary/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getTransactionSummaryForUser(@PathVariable("id") Long id) {
        List<Transactions> transactions = service.getTransactionsSummary(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices/", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> createInvoice(@RequestBody Invoice invoice) {
        service.createInvoice(invoice);
        return new ResponseEntity<>("Invoice is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
        service.updateInvoiceDetails(invoice);
        return new ResponseEntity<>("Invoice is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/accountSummary/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAccountSummary(@PathVariable("id") Long id) {
        AccountSummary accountSummary = service.getAccountSummary(id);
        return new ResponseEntity<>(accountSummary, HttpStatus.OK);
    }

/*

    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }


    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        if(!productRepo.containsKey(id))
            throw new ProductNotfoundException();
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
    }

*/
}
