package com.riskcare.bigdata.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author chatsas
 *
 * @param <X>
 */
public class ListDTO<X>{

    private int totalCount;
    private List<X> records;
    private boolean success;
    private String errorMsg;

    /**
     * default constructor
     */
    public ListDTO() {
        // empty constructor
    }

    /**
     * Construct a list DTO for a successful call
     * 
     * This constructor sets status = true, message = "" to indicate success.
     * 
     * @param totalCount
     *            Total number of records in the result set (across all pages if
     *            pagination is used)
     * @param records
     *            The records to display (for this page only if pagination is
     *            used)
     */
    public ListDTO(int totalCount, List<X> records) {       
        this.success = Boolean.TRUE;
        this.errorMsg = "";
        this.totalCount = totalCount;
        this.records = records;
    }

    /**
     * Construct a list DTO with no pay-load, only status information
     * 
     * The totalCount and records fields are set to zero and the empty list
     * respectively.
     * 
     * @param status
     *            The status of the call
     * @param message
     *            The error message to return to the caller
     */
    public ListDTO(final boolean status, final String message) {
    	this.success = status;
        this.errorMsg = message;        
        this.totalCount = 0;
        this.records = Collections.emptyList();
    }

    /**
     * Create a list DTO to express a failure
     * 
     * This newly created list DTO has status = false to indicate failure. The
     * totalCount and records fields are set to zero and null respectively.
     * 
     * @param message
     *            The error message to return to the caller
     * @return A list DTO that expresses a failed call
     */
    public static <X> ListDTO<X> failure(String message) {
        return new ListDTO<X>(false, message, 0, new ArrayList<X>(0));
    }

    /**
     * 
     * @param status
     * @param message
     * @param totalCount
     * @param records
     */
    public ListDTO(final boolean status, final String message, final int totalCount, final List<X> records) {
    	this.success = status;
        this.errorMsg = message;       
        this.totalCount = totalCount;
        this.records = records;
    }

    /**
     * 
     * @return total records count
     */
    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 
     * @return map of records
     */
    public List<X> getRecords() {
        return Collections.unmodifiableList(this.records);
    }

    public void setRecords(final List<X> records) {
        this.records = records;
    }

    public boolean isSuccess() {
        return success;
    }

    /**
     * 
     * @return error message
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
