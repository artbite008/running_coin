package com.running.coins.model.transition;

import lombok.Data;

/**
 * MailBean
 *
 * @author guxiang
 * @date 2018/4/29
 */
@Data
public class MailBean {

    private String username;
    private float distance;
    private String creationTime;
    private Integer score;
    private String status;
    private Double earnedCoins;
}
