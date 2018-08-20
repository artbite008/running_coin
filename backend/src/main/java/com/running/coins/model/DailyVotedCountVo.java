package com.running.coins.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: xianggu@ebay.com
 * @date : 2018 8/20/18
 * @desc : runningclub
 */
@Data
public class DailyVotedCountVo {
    Integer  votedCount;
    Date     votedDate;
    String   userName;
}