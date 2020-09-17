package com.posts.springsecurityjwt.services;

import com.posts.springsecurityjwt.models.UserDetailsResponse;
import com.posts.springsecurityjwt.repo.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchUserPosts {

    @Autowired
    private UserManagementRepository userManagementRepository;

    public UserDetailsResponse fetchPosts(Integer pageNo, Integer pageSize, String title) {
        UserDetailsResponse userDetailsResponse=  new UserDetailsResponse();
        userDetailsResponse.setPost(userManagementRepository.getAllPosts(pageNo, pageSize, title));


        if (title.isEmpty()){
            userDetailsResponse.setTotalNoPages(userManagementRepository.getSizeOfList(pageSize));
            userDetailsResponse.setCurrentPage(pageNo);
        }
        return userDetailsResponse;
    }
}
