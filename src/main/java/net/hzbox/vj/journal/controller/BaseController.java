package net.hzbox.vj.journal.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseController {


    private static final String COMPANY_ID = "x-auth-companyid";
    private static final String ROLES = "x-auth-roles";
    private static final String USER_ID = "x-auth-userid";
    private static final String USER_NAME = "x-auth-username";

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取公司ID
     *
     * @return 公司id
     */
    public Integer getCompanyId() {
        String str = request.getHeader(COMPANY_ID);
        if (StringUtils.isNotBlank(str)) {
            return Integer.parseInt(str.trim());
        }
        return null;
    }

    /**
     * 获取用户ID
     *
     * @return 用户id
     */
    public Integer getUserId() {
        String str = request.getHeader(USER_ID);
        if (StringUtils.isNotBlank(str)) {
            return Integer.parseInt(str.trim());
        }
        return null;
    }

    /**
     * 获取登陆用户名
     *
     * @return 登录名
     */
    String getUserName() {
        return request.getHeader(USER_NAME);
    }

    /**
     * 获取角色信息
     * @return 角色信息
     */
    public List<String> getRoleList() {
        String str = request.getHeader(ROLES);
        if (org.apache.commons.lang.StringUtils.isNotBlank(str)) {
            String[] strArr = str.split(",");
            return Arrays.asList(strArr);
        }
        return new ArrayList<>();
    }

}
