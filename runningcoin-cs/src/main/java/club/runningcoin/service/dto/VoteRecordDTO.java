package club.runningcoin.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the VoteRecord entity.
 */
public class VoteRecordDTO implements Serializable {

    private Long id;

    private Integer voteUserGroupId;

    private Integer runingRecordId;

    private ZonedDateTime votedTime;

    private Integer statusField;

    private Integer score;

    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVoteUserGroupId() {
        return voteUserGroupId;
    }

    public void setVoteUserGroupId(Integer voteUserGroupId) {
        this.voteUserGroupId = voteUserGroupId;
    }

    public Integer getRuningRecordId() {
        return runingRecordId;
    }

    public void setRuningRecordId(Integer runingRecordId) {
        this.runingRecordId = runingRecordId;
    }

    public ZonedDateTime getVotedTime() {
        return votedTime;
    }

    public void setVotedTime(ZonedDateTime votedTime) {
        this.votedTime = votedTime;
    }

    public Integer getStatusField() {
        return statusField;
    }

    public void setStatusField(Integer statusField) {
        this.statusField = statusField;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VoteRecordDTO voteRecordDTO = (VoteRecordDTO) o;
        if (voteRecordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), voteRecordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VoteRecordDTO{" +
            "id=" + getId() +
            ", voteUserGroupId=" + getVoteUserGroupId() +
            ", runingRecordId=" + getRuningRecordId() +
            ", votedTime=" + getVotedTime() +
            ", statusField=" + getStatusField() +
            ", score=" + getScore() +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
