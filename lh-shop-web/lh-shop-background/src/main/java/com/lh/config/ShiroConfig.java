package com.lh.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by laiHom on 2019/8/21.
 */
@Configuration
public class ShiroConfig {

    /**
     *创建ShiroFilterFactoryBean
     *
     * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**添加shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的过滤器：
         *        anon:无需认证（登陆）可以访问
         *        authc:必须认证才可以访问
         *        user:如果使用rememberMe的功能可以直接访问
         *        perms:该资源必须得到资源权限才可以访问
         *        role:该资源必须得到角色权限才可以访问
        */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();

        // 静态资源匿名访问
        filterMap.put("/static/**", "anon");
        //img
        filterMap.put("/image/**", "anon");
        //css
        filterMap.put("/css/**", "anon");
        //js
        filterMap.put("/js/**", "anon");
        // 登录匿名访问
        filterMap.put("/login", "anon");
        //登录界面
        filterMap.put("/index", "anon");
        //自定义加载权限资源关系

        //还没登陆时拦截后调整回登陆页面
        shiroFilterFactoryBean.setLoginUrl("/index");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterMap.put("/logout", "logout");

        filterMap.put("/*","authc");


        System.out.println("Youshenmchulai!!!!!!!!!!!!");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public EhCacheManager getCache(){
        return new EhCacheManager();
    }
    /**
     *创建DefaultWebSecurityManager
     *
     * */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        securityManager.setCacheManager(getCache());
        return securityManager;
    }
    /**
     *创建Realm
     * */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return  new UserRealm();
    }


    // shiro标签与thymeleaf标签结合,必须从写此方法不然html页面那里shiro标签不起效
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
