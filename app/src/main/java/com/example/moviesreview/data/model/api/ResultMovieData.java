package com.example.moviesreview.data.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultMovieData {


    @SerializedName("page")
    private Integer page;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("results")
    private List<Movie_Api> results;

    public ResultMovieData(Integer page, Integer totalResults, Integer totalPages, List<Movie_Api> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public ResultMovieData setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public ResultMovieData setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public ResultMovieData setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public List<Movie_Api> getResults() {
        return results;
    }

    public ResultMovieData setResults(List<Movie_Api> results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        return "PopularMovieData{" +
                "page=" + page +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                ", results=" + results +
                '}';
    }

}
