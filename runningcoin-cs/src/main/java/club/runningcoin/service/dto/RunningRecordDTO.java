package club.runningcoin.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RunningRecord entity.
 */
public class RunningRecordDTO implements Serializable {

    private Long id;

    private Integer userGroupId;

    private Float distance;

    private ZonedDateTime creationTime;

    private ZonedDateTime lastVotedTime;

    private Integer statusField;

    private Integer score;

    private ZonedDateTime settledTime;

    private Double earnedCoins;

    private String comments;

    private String evidence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public ZonedDateTime getLastVotedTime() {
        return lastVotedTime;
    }

    public void setLastVotedTime(ZonedDateTime lastVotedTime) {
        this.lastVotedTime = lastVotedTime;
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

    public ZonedDateTime getSettledTime() {
        return settledTime;
    }

    public void setSettledTime(ZonedDateTime settledTime) {
        this.settledTime = settledTime;
    }

    public Double getEarnedCoins() {
        return earnedCoins;
    }

    public void setEarnedCoins(Double earnedCoins) {
        this.earnedCoins = earnedCoins;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RunningRecordDTO runningRecordDTO = (RunningRecordDTO) o;
        if (runningRecordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), runningRecordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RunningRecordDTO{" +
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
