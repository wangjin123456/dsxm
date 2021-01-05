package com.mayikt.api.member.service.entitydo;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MeiteUnionLogin implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String unionName;

    private String unionPublicId;

    private String unionBeanId;

    private String appId;

    private String appKey;

    private String redirectUri;

    private String requestAddress;

    private Integer isAvailability;


}
