package com.lh.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.IPersonService;
import com.lh.entity.Person;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;


/**
 * 自定义Realm
 * Created by laiHom on 2019/8/21.
 */
public class UserRealm extends AuthorizingRealm {


    @Reference
    private IPersonService personService;


    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证逻辑");

        //shiro判断逻辑，判断用户名和密码
        //1、判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        Person person = personService.getUserName(token.getUsername());

        if(person == null){
            //用户不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }

//        // 当验证都通过后，把用户信息放在session里
//        Session session2 = SecurityUtils.getSubject().getSession();
//        session2.setAttribute("personName", person.getUserName());
//        session2.setAttribute("person", person);
        return new SimpleAuthenticationInfo(person,person.getUserPassword(), "");
    }
}
