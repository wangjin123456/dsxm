
package com.mayikt.api.member.service.utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



/**
*@title: ChannelUtils
*@description； 项目
*@author taotao
*@date 2020/12/14 10:36
*/  
@Component
public class ChannelUtils {
    @Value("${mayikt.login.token.channel}")
    private  String[] loginTokenChannel;
    public Boolean existChannel(String channel){
        for (int i = 0; i <loginTokenChannel.length ; i++) {
            System.out.println(loginTokenChannel[i]+"***");
            if(channel.toLowerCase().equals(loginTokenChannel[i])){
                return  true;
            }

        }
        return  false;
    }
}
