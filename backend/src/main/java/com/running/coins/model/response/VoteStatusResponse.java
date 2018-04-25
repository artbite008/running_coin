package com.running.coins.model.response;

import com.running.coins.model.VoteRecord;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteStatusResponse {
    private int voteStatus;
    private VoteRecord voteRecord;
}
