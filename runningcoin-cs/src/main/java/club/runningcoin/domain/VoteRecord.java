package club.runningcoin.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A VoteRecord.
 */
@Entity
@Table(name = "Vote_Record")
public class VoteRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VoteRecordId")
    private Long id;

    @Column(name = "VoteUserGroupId")
    private Integer voteUserGroupId;

    @Column(name = "RuningRecordId")
    private Integer runingRecordId;

    @Column(name = "VotedTime")
    private ZonedDateTime votedTime;

    @Column(name = "Status")
    private Integer statusField;

    @Column(name = "Score")
    private Integer score;

    @Column(name = "Comments")
    private String comments;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVoteUserGroupId() {
        return voteUserGroupId;
    }

    public VoteRecord voteUserGroupId(Integer voteUserGroupId) {
        this.voteUserGroupId = voteUserGroupId;
        return this;
    }

    public void setVoteUserGroupId(Integer voteUserGroupId) {
        this.voteUserGroupId = voteUserGroupId;
    }

    public Integer getRuningRecordId() {
        return runingRecordId;
    }

    public VoteRecord runingRecordId(Integer runingRecordId) {
        this.runingRecordId = runingRecordId;
        return this;
    }

    public void setRuningRecordId(Integer runingRecordId) {
        this.runingRecordId = runingRecordId;
    }



    public VoteRecord votedTime(ZonedDateTime votedTime) {
        this.votedTime = votedTime;
        return this;
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

    public VoteRecord statusField(Integer statusField) {
        this.statusField = statusField;
        return this;
    }

    public void setStatusField(Integer statusField) {
        this.statusField = statusField;
    }

    public Integer getScore() {
        return score;
    }

    public VoteRecord score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComments() {
        return comments;
    }

    public VoteRecord comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VoteRecord voteRecord = (VoteRecord) o;
        if (voteRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), voteRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VoteRecord{" +
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
