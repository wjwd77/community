package cn.mnu.community.controller;

import cn.mnu.community.dto.AccessTokenDTO;
import cn.mnu.community.dto.GithubUser;
import cn.mnu.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String ClientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(ClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToke = githubProvider.getAccessToke(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToke);
        if(user != null){
            //登录成功
            request.getSession().setAttribute("user",user);
            //重定向跳转
            return "redirect:/";
        }else{
            //登录失败
            //重定向跳转
            return "redirect:/";
        }
    }
}
