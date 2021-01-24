
package com.mayikt.api.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.member.api.service.MemberOpenIdTokenLogin;
import com.mayikt.api.member.api.service.MemberUnionLoginService;
import com.mayikt.api.member.service.entitydo.MeiteUnionLogin;
import com.mayikt.api.member.service.mapper.MeiteUnionLoginMapper;
import com.mayikt.api.member.service.strategy.UnionLoginStrategy;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.utils.SpringContextUtils;
import com.mayikt.utils.TokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Wrapper;

/**
 * @author taotao
 * @title: MemberUnionLoginServiceImpl
 * @description； 项目
 * @date 2021/1/4 10:31
 */
@RestController
public class MemberUnionLoginServiceImpl extends BaseApiService implements MemberUnionLoginService {
    @Autowired
    private MeiteUnionLoginMapper meiteUnionLoginMapper;
    @Autowired
    private TokenUtils tokenUtils;
@Autowired
private MemberOpenIdTokenLogin memberOpenIdTokenLogin;
    @Override
    public BaseResponse<String> unionLogin(String unionPublicId) {
        if (Strings.isBlank(unionPublicId)) {
            return setResultError("unionPublicId为空");
        }
        QueryWrapper<MeiteUnionLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("union_public_id", unionPublicId);
        queryWrapper.eq("is_availability", 1);
        MeiteUnionLogin meiteUnionLogin = meiteUnionLoginMapper.selectOne(queryWrapper);
        if (meiteUnionLogin == null) {
            return setResultError("该渠道可能已经关闭或者不存在");
        }

        String state = tokenUtils.createToken("member.unionLogin", "");
        String requestAddres = meiteUnionLogin.getRequestAddress() + "&state=" + state;
        JSONObject dataObjects = new JSONObject();
        dataObjects.put("requestAddres", requestAddres);
        return setResultSuccess(dataObjects);

    }

    @Override
    public BaseResponse<JSONObject> unionLoginCallback(String unionPublicId) {

        if (Strings.isBlank(unionPublicId)) {
            return setResultError("unionPublicId为空");
        }
        QueryWrapper<MeiteUnionLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("union_public_id", unionPublicId);
        queryWrapper.eq("is_availability", 1);
        MeiteUnionLogin meiteUnionLogin = meiteUnionLoginMapper.selectOne(queryWrapper);
        if (meiteUnionLogin == null) {
            return setResultError("该渠道可能已经关闭或者不存在");
        }

        String unionBeanId = meiteUnionLogin.getUnionBeanId();
        if (Strings.isBlank(unionBeanId)) {
            return setResultError("系统参数错误");
        }
        //从spring容器中根据beanid查找我们的策略类
        UnionLoginStrategy unionLoginStrategy = SpringContextUtils.getBean(unionBeanId, UnionLoginStrategy.class);
       if(unionLoginStrategy==null){
           return  setResultError("沒有查詢到該策略");
       }

        HttpServletRequest request = ((ServletRequestAttributes)
                (RequestContextHolder.currentRequestAttributes())).getRequest();

        String openId = unionLoginStrategy.unionLoginCallback(request, meiteUnionLogin);
        if (Strings.isBlank(openId)) {
            return setResultError("系统错误");
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("openId",openId);
        jsonObject.put("unionPublicId",unionPublicId);
        String opentoken = tokenUtils.createToken("mayikt.unionLogin.qq.", jsonObject.toJSONString());
        JSONObject dataToken=new JSONObject();
        dataToken.put("openToken",opentoken);

        return memberOpenIdTokenLogin.openIdLoginToken(opentoken);
    }
}
