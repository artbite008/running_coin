package club.runningcoin.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UserGroup.
 */
@Entity
@Table(name = "UserGroup")
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UserGroupId")
    private Long id;

    @Column(name = "UserOpenid")
    private String userOpenid;

    @Column(name = "GroupId")
    private Integer groupId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public UserGroup userOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
        return this;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public UserGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
        UserGroup userGroup = (UserGroup) o;
        if (userGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserGroup{" +
            "id=" + getId() +
            ", userOpenid=" + getUserOpenid() +
            ", groupId=" + getGroupId() +
            "}";
    }
}
