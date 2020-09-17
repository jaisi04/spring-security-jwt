package com.posts.springsecurityjwt;

import com.posts.springsecurityjwt.models.AuthenticationRequest;
import com.posts.springsecurityjwt.models.AuthenticationResponse;
import com.posts.springsecurityjwt.models.Posts;
import com.posts.springsecurityjwt.models.UserDetailsResponse;
import com.posts.springsecurityjwt.services.FetchUserPosts;
import com.posts.springsecurityjwt.services.MyUserDetailsService;
import com.posts.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin(origins = "*")
public class GetListOfPostsWebApiController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private FetchUserPosts fetchUserPosts;

    @GetMapping(value = "/posts")
    public ResponseEntity<UserDetailsResponse> getPosts(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "100") Integer pageSize, @RequestParam(defaultValue = "") String title) {
        return new ResponseEntity<UserDetailsResponse>(fetchUserPosts.fetchPosts(pageNo, pageSize, title), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}

