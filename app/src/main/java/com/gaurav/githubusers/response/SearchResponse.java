package com.gaurav.githubusers.response;

import java.util.List;

public class SearchResponse
{
    private String incomplete_results;

    private List<UserResponse> items;

    private String total_count;

    public String getIncomplete_results ()
    {
        return incomplete_results;
    }

    public void setIncomplete_results (String incomplete_results)
    {
        this.incomplete_results = incomplete_results;
    }

    public List<UserResponse> getItems ()
    {
        return items;
    }

    public void setItems (List<UserResponse> items)
    {
        this.items = items;
    }

    public String getTotal_count ()
    {
        return total_count;
    }

    public void setTotal_count (String total_count)
    {
        this.total_count = total_count;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [incomplete_results = "+incomplete_results+", items = "+items+", total_count = "+total_count+"]";
    }
}