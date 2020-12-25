
package com.mayikt.job.config;

/**
*@title: MybatisPlusConfig
*@description； 项目
*@author taotao
*@date 2020/12/25 20:22
*/

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *@author tom
 *Date  2020/6/16 0016 22:44
 *分页插件组合
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.taotao.mybatisplus1.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor=new PaginationInterceptor();
        //设置请求的页面大于最大页后操作,true调回到首页,false 继续请求
        paginationInterceptor.setOverflow(false);
        //设置最大页限制数量。默认为500条,-1 不限制
        paginationInterceptor.setLimit(10);
        //开启count d join 优化只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return  paginationInterceptor;
    }


    /**
     * 整合乐观锁插件 改写我们的sql语句 加上版本号码
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}