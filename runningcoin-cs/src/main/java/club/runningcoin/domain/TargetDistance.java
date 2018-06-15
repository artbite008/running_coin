package club.runningcoin.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A TargetDistance.
 */
@Entity
@Table(name = "Target_Distance")
public class TargetDistance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name ="TargetDistanceId")
    private Long id;

    @Column(name = "UserGroupId")
    private Integer userGroupId;

    @Column(name = "CreationTime")
    private ZonedDateTime creationTime;

    @Column(name = "TargetDistance")
    private Float targetDistance;

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

    public TargetDistance userGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
        return this;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public TargetDistance creationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Float getTargetDistance() {
        return targetDistance;
    }

    public TargetDistance targetDistance(Float targetDistance) {
        this.targetDistance = targetDistance;
        return this;
    }

    public void setTargetDistance(Float targetDistance) {
        this.targetDistance = targetDistance;
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
        TargetDistance targetDistance = (TargetDistance) o;
        if (targetDistance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), targetDistance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TargetDistance{" +
            "id=" + getId() +
            ", userGroupId=" + getUserGroupId() +
            ", creationTime='" + getCreationTime() + "'" +
            ", targetDistance=" + getTargetDistance() +
            "}";
    }
}
