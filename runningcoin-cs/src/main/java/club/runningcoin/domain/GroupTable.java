package club.runningcoin.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GroupTable.
 */
@Entity
@Table(name = "Group")
public class GroupTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name ="GroupId")
    private Long id;

    @Column(name = "GroupName")
    private String groupName;

    @Column(name = "MetaData")
    private String metaData;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public GroupTable groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMetaData() {
        return metaData;
    }

    public GroupTable metaData(String metaData) {
        this.metaData = metaData;
        return this;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
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
        GroupTable groupTable = (GroupTable) o;
        if (groupTable.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groupTable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroupTable{" +
            "id=" + getId() +
            ", groupName='" + getGroupName() + "'" +
            ", metaData='" + getMetaData() + "'" +
            "}";
    }
}
