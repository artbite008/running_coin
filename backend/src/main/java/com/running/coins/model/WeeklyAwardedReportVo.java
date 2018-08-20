package com.running.coins.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: xianggu@ebay.com
 * @date : 2018 8/20/18
 * @desc : runningclub
 */
@Data
public class WeeklyAwardedReportVo {
    Integer  earnCoin;
    Date     awardDate;
    String   userName;

}
