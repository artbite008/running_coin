package club.runningcoin.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A RunningRecord.
 */
@Entity
@Table(name = "Running_Record")
public class RunningRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RuningRecordId")
    private Long id;

    @Column(name = "UserGroupId")
    private Integer userGroupId;

    @Column(name = "Distance")
    private Float distance;

    @Column(name = "CreationTime")
    private ZonedDateTime creationTime;

    @Column(name = "LastVotedTime")
    private ZonedDateTime lastVotedTime;

    @Column(name = "Status")
    private Integer statusField;

    @Column(name = "Score")
    private Integer score;

    @Column(name = "SettledTime")
    private ZonedDateTime settledTime;

    @Column(name = "EarnedCoins")
    private Double earnedCoins;

    @Column(name = "Comments")
    private String comments;

    @Column(name = "Evidence")
    private String evidence;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public RunningRecord userGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
        return this;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Float getDistance() {
        return distance;
    }

    public RunningRecord distance(Float distance) {
        this.distance = distance;
        return this;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public RunningRecord creationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public ZonedDateTime getLastVotedTime() {
        return lastVotedTime;
    }

    public RunningRecord lastVotedTime(ZonedDateTime lastVotedTime) {
        this.lastVotedTime = lastVotedTime;
        return this;
    }

    public void setLastVotedTime(ZonedDateTime lastVotedTime) {
        this.lastVotedTime = lastVotedTime;
    }

    public Integer getStatusField() {
        return statusField;
    }

    public RunningRecord statusField(Integer statusField) {
        this.statusField = statusField;
        return this;
    }

    public void setStatusField(Integer statusField) {
        this.statusField = statusField;
    }

    public Integer getScore() {
        return score;
    }

    public RunningRecord score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public ZonedDateTime getSettledTime() {
        return settledTime;
    }

    public RunningRecord settledTime(ZonedDateTime settledTime) {
        this.settledTime = settledTime;
        return this;
    }

    public void setSettledTime(ZonedDateTime settledTime) {
        this.settledTime = settledTime;
    }

    public Double getEarnedCoins() {
        return earnedCoins;
    }

    public RunningRecord earnedCoins(Double earnedCoins) {
        this.earnedCoins = earnedCoins;
        return this;
    }

    public void setEarnedCoins(Double earnedCoins) {
        this.earnedCoins = earnedCoins;
    }

    public String getComments() {
        return comments;
    }

    public RunningRecord comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEvidence() {
        return evidence;
    }

    public RunningRecord evidence(String evidence) {
        this.evidence = evidence;
        return this;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
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
        RunningRecord runningRecord = (RunningRecord) o;
        if (runningRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), runningRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RunningRecord{" +
            "id=" + getId() +
            ", userGroupId=" + getUserGroupId() +
            ", distance=" + getDistance() +
            ", creationTime='" + getCreationTime() + "'" +
            ", lastVotedTime='" + getLastVotedTime() + "'" +
            ", statusField=" + getStatusField() +
            ", score=" + getScore() +
            ", settledTime='" + getSettledTime() + "'" +
            ", earnedCoins=" + getEarnedCoins() +
            ", comments='" + getComments() + "'" +
            ", evidence='" + getEvidence() + "'" +
            "}";
    }
}
