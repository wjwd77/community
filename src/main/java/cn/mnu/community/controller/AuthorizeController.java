package cn.mnu.community.controller;

import cn.mnu.community.dto.AccessTokenDTO;
import cn.mnu.community.dto.GithubUser;
import cn.mnu.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("ec3219d21f6fbe113b89");
        accessTokenDTO.setClient_secret("7e01d7d802973fcdda3f92336a65ef220955a736");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToke = githubProvider.getAccessToke(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToke);
        System.out.println(user.getName());
        return "index";
    }
}
