package com.payroll.myapp.mypayrollapp.dao;

import com.payroll.myapp.mypayrollapp.model.Invoice;
import com.payroll.myapp.mypayrollapp.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmehta on 2/8/19.
 */

@Repository
public class PayrollDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_TRANSACTIONS_FOR_USER =
            "select * from TRANSACTIONS where user_id = ? order by transaction_date desc";

    /* list of transactions from the user’s bank account.  */
    public List <Transactions> getAllTransactionForUser(long userId) {
        final List<Transactions> allTransactionsPerUser = new ArrayList<>();
        try {
            jdbcTemplate.query(
                    GET_ALL_TRANSACTIONS_FOR_USER,
                    new PreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement ps) throws SQLException {
                            int cnt = 1;
                            ps.setLong(cnt++, userId);
                        }
                    },
                    new RowCallbackHandler() {
                        @Override
                        public void processRow(ResultSet rs) throws SQLException {
                            Transactions t = new Transactions();
                            t.setUserId(userId);
                            t.setTransactionId(rs.getLong("transaction_id"));
                            t.setTransactionUniqueNo(rs.getString("transaction_uniqueNo"));
                            t.setTransactionDesc(rs.getString("transaction_desc"));
                            t.setTransactionDate(rs.getTimestamp("transaction_date"));
                            t.setTransactionAmount(rs.getDouble("transaction_amount"));
                            allTransactionsPerUser.add(t);
                        }
                    });
        } catch (Exception e) {

        }
        return allTransactionsPerUser;
    }

    private static final String GET_ALL_INVOICE_FOR_USER =
            "select * from INVOICE where user_id = ? order by invoice_creation_date desc";

    /* list of invoices from the user’s bank account.  */
    public List <Invoice> getAllInvoicesForUser(long userId) {
        final List<Invoice> allInvoicesPerUser = new ArrayList<>();
        try {
            jdbcTemplate.query(
                    GET_ALL_INVOICE_FOR_USER,
                    new PreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement ps) throws SQLException {
                            int cnt = 1;
                            ps.setLong(cnt++, userId);
                        }
                    },
                    new RowCallbackHandler() {
                        @Override
                        public void processRow(ResultSet rs) throws SQLException {
                            Invoice i = new Invoice();
                            i.setUserId(userId);
                            i.setInvoiceId(rs.getLong("invoice_id"));
                            i.setInvoiceUniqueNo(rs.getString("invoice_uniqueNo"));
                            i.setInvoiceClientName(rs.getString("invoice_client_name"));
                            i.setInvoiceCreationDate(rs.getTimestamp("invoice_creation_date"));
                            i.setInvoiceAmount(rs.getDouble("invoice_amount"));
                            i.setInvoiceStatus(rs.getString("invoice_status"));
                            allInvoicesPerUser.add(i);
                        }
                    });
        } catch (Exception e) {

        }
        return allInvoicesPerUser;
    }


    private static final String CREATE_INVOICE =
            "insert into invoice (invoice_id, user_id, invoice_client_name, invoice_uniqueNo, " +
                    "invoice_amount, invoice_creation_date) "
                    + " values(?,?,?,?,?,?) ";

    /* Create a new invoice */
    public int createInvoice(Invoice i) {
        int result =
                jdbcTemplate
                        .update(
                                CREATE_INVOICE,
                                new PreparedStatementSetter() {

                                    @Override
                                    public void setValues(PreparedStatement ps) throws SQLException {
                                        int cnt = 1;
                                        ps.setLong(cnt++, i.getInvoiceId());
                                        ps.setLong(cnt++, i.getUserId());
                                        ps.setString(cnt++, i.getInvoiceClientName());
                                        ps.setString(cnt++, i.getInvoiceUniqueNo());
                                        ps.setDouble(cnt++, i.getInvoiceAmount());
                                        ps.setTimestamp(cnt++, i.getInvoiceCreationDate());
                                    }
                                });
        return result;
    }


    private static final String UPDATE_INVOICE =
            "update INVOICE set invoice_client_name = ?, invoice_amount = ?, invoice_uniqueNo = ?," +
                    " invoice_creation_date = ? where user_id = ? and invoice_id = ? ";


    /* Update a new invoice */
    public int updateInvoiceForUser(Invoice i) {
        int result =
                jdbcTemplate
                        .update(
                                UPDATE_INVOICE,
                                new PreparedStatementSetter() {

                                    @Override
                                    public void setValues(PreparedStatement ps) throws SQLException {
                                        int cnt = 1;
                                        ps.setString(cnt++, i.getInvoiceClientName());
                                        ps.setDouble(cnt++, i.getInvoiceAmount());
                                        ps.setString(cnt++, i.getInvoiceUniqueNo());
                                        ps.setTimestamp(cnt++, i.getInvoiceCreationDate());
                                        ps.setLong(cnt++, i.getUserId());
                                        ps.setLong(cnt++, i.getInvoiceId());
                                    }
                                });
        return result;
    }


    private static final String GET_INVOICE_STATUS =
            "select count(1) as PAID from INVOICE i, TRANSACTIONS t " +
            " where i.user_id=t.user_id " +
            " and t.transaction_uniqueNo=i.invoice_uniqueNo " +
            " and t.transaction_date > i.invoice_creation_date and invoice_id = ?";


    /* Calculate the status of Invoice */
    public boolean getStatusForInvoice(long invoiceId) {
        final boolean[] status = {false};
        jdbcTemplate
                .query(
                        GET_INVOICE_STATUS,
                        new PreparedStatementSetter() {

                            @Override
                            public void setValues(PreparedStatement ps) throws SQLException {
                                int cnt = 1;
                                ps.setLong(cnt++, invoiceId);
                            }
                        },
                        new RowCallbackHandler() {

                            @Override
                            public void processRow(ResultSet rs) throws SQLException {
                                boolean paid = rs.getBoolean("PAID");
                                status[0] = paid;
                            }
                        });
        return status[0];
    }


    private static final String GET_ONE_MONTH_TRANSACTIONS_FOR_USER =
            "select * from TRANSACTIONS where user_id = ? " +
                    " and transaction_date > sysdate-30 order by transaction_date desc";

    /* list of transactions from the user’s bank account.  */
    public List <Transactions> getOneMonthTransactionForUser(long userId) {
        final List<Transactions> allTransactionsPerUser = new ArrayList<>();
        try {
            jdbcTemplate.query(
                    GET_ONE_MONTH_TRANSACTIONS_FOR_USER,
                    new PreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement ps) throws SQLException {
                            int cnt = 1;
                            ps.setLong(cnt++, userId);
                        }
                    },
                    new RowCallbackHandler() {
                        @Override
                        public void processRow(ResultSet rs) throws SQLException {
                            Transactions t = new Transactions();
                            t.setUserId(userId);
                            t.setTransactionId(rs.getLong("transaction_id"));
                            t.setTransactionUniqueNo(rs.getString("transaction_uniqueNo"));
                            t.setTransactionDesc(rs.getString("transaction_desc"));
                            t.setTransactionDate(rs.getTimestamp("transaction_date"));
                            t.setTransactionAmount(rs.getDouble("transaction_amount"));
                            allTransactionsPerUser.add(t);
                        }
                    });
        } catch (Exception e) {

        }
        return allTransactionsPerUser;
    }

    private static final String GET_ONE_MONTH_INVOICE_FOR_USER =
            "select * from INVOICE where user_id = ? " +
                    " and invoice_creation_date > sysdate-30 order by invoice_creation_date desc";

    /* list of invoices from the user’s bank account.  */
    public List <Invoice> getOneMonthInvoicesForUser(long userId) {
        final List<Invoice> allInvoicesPerUser = new ArrayList<>();
        try {
            jdbcTemplate.query(
                    GET_ONE_MONTH_INVOICE_FOR_USER,
                    new PreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement ps) throws SQLException {
                            int cnt = 1;
                            ps.setLong(cnt++, userId);
                        }
                    },
                    new RowCallbackHandler() {
                        @Override
                        public void processRow(ResultSet rs) throws SQLException {
                            Invoice i = new Invoice();
                            i.setUserId(userId);
                            i.setInvoiceId(rs.getLong("invoice_id"));
                            i.setInvoiceUniqueNo(rs.getString("invoice_uniqueNo"));
                            i.setInvoiceClientName(rs.getString("invoice_client_name"));
                            i.setInvoiceCreationDate(rs.getTimestamp("invoice_creation_date"));
                            i.setInvoiceAmount(rs.getDouble("invoice_amount"));
                            i.setInvoiceStatus(rs.getString("invoice_status"));
                            allInvoicesPerUser.add(i);
                        }
                    });
        } catch (Exception e) {

        }
        return allInvoicesPerUser;
    }


    private static final String GET_TOTAL_AMOUNT_FOR_USER =
            "select sum(transaction_amount) as total from TRANSACTIONS where user_id = ?";


    /* Calculate the total amount in bank for user */
    public double getTotalAmountForUser(long userId) {
        final double[] totalAmt = {0};
        jdbcTemplate
                .query(
                        GET_TOTAL_AMOUNT_FOR_USER,
                        new PreparedStatementSetter() {

                            @Override
                            public void setValues(PreparedStatement ps) throws SQLException {
                                int cnt = 1;
                                ps.setLong(cnt++, userId);
                            }
                        },
                        new RowCallbackHandler() {

                            @Override
                            public void processRow(ResultSet rs) throws SQLException {
                                double total = rs.getDouble("total");
                                totalAmt[0] = total;
                            }
                        });
        return totalAmt[0];
    }

    private static final String GET_THRESHOLD_FOR_USER =
            "select threshold from PAYROLL_ACCOUNT where user_id = ?";

    /* Get the threshold for a user */
    public double getThresholdForUser(long userId) {
        final double[] threshold = {0};
        jdbcTemplate
                .query(
                        GET_THRESHOLD_FOR_USER,
                        new PreparedStatementSetter() {

                            @Override
                            public void setValues(PreparedStatement ps) throws SQLException {
                                int cnt = 1;
                                ps.setLong(cnt++, userId);
                            }
                        },
                        new RowCallbackHandler() {

                            @Override
                            public void processRow(ResultSet rs) throws SQLException {
                                double t = rs.getDouble("threshold");
                                threshold[0] = t;
                            }
                        });
        return threshold[0];
    }


    private static final String UPDATE_INVOICE_STATUS =
            "update INVOICE set invoice_status = ? where invoice_id = ? ";

    /* Update a new invoice */
    public int updateInvoiceStatus(long invoiceId, String status) {
        int result =
                jdbcTemplate
                        .update(
                                UPDATE_INVOICE_STATUS,
                                new PreparedStatementSetter() {

                                    @Override
                                    public void setValues(PreparedStatement ps) throws SQLException {
                                        int cnt = 1;
                                        ps.setString(cnt++, status);
                                        ps.setLong(cnt++, invoiceId);
                                    }
                                });
        return result;
    }

    private static final String GET_INVOICE_ID_SEQ =
            "select invoice_id_seq.nextval as invoiceId from dual";

    /* list of invoices from the user’s bank account.  */
    public long getInvoiceIdFromSeq() {
        final long[] newInvoiceId = {0L};
        try {
            jdbcTemplate.query(
                    GET_INVOICE_ID_SEQ,
                    new RowCallbackHandler() {
                        @Override
                        public void processRow(ResultSet rs) throws SQLException {
                            newInvoiceId[0] = rs.getLong("invoiceId");
                        }
                    });
        } catch (Exception e) {

        }
        return newInvoiceId[0];
    }
}
