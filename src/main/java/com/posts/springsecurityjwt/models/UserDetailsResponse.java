package com.posts.springsecurityjwt.models;

import java.util.List;

public class UserDetailsResponse {


    public List<Posts> getPost() {
        return post;
    }

    public void setPost(List<Posts> post) {
        this.post = post;
    }

    private List<Posts> post;

    public int getTotalNoPages() {
        return totalNoPages;
    }

    public void setTotalNoPages(int totalNoPages) {
        this.totalNoPages = totalNoPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    private int totalNoPages;
    private int currentPage;
}
