package club.runningcoin.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TargetDistance entity.
 */
public class TargetDistanceDTO implements Serializable {

    private Long id;

    private Integer userGroupId;

    private ZonedDateTime creationTime;

    private Float targetDistance;

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

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Float getTargetDistance() {
        return targetDistance;
    }

    public void setTargetDistance(Float targetDistance) {
        this.targetDistance = targetDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TargetDistanceDTO targetDistanceDTO = (TargetDistanceDTO) o;
        if (targetDistanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), targetDistanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TargetDistanceDTO{" +
            "id=" + getId() +
            ", userGroupId=" + getUserGroupId() +
            ", creationTime='" + getCreationTime() + "'" +
            ", targetDistance=" + getTargetDistance() +
            "}";
    }
}
