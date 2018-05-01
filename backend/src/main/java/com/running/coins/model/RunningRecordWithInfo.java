package com.running.coins.model;

import lombok.Data;

/**
 * RunningRecordWithfinalscore
 *
 * @author guxiang
 * @date 2018/4/27
 */
@Data
public class RunningRecordWithInfo extends RunningRecord {

    private Integer finalScore;

    private String username;

    private Integer userId;
}
