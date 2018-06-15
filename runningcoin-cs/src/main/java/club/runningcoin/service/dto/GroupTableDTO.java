package club.runningcoin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GroupTable entity.
 */
public class GroupTableDTO implements Serializable {

    private Long id;

    private String groupName;

    private String metaData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GroupTableDTO groupTableDTO = (GroupTableDTO) o;
        if (groupTableDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groupTableDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroupTableDTO{" +
            "id=" + getId() +
            ", groupName='" + getGroupName() + "'" +
            ", metaData='" + getMetaData() + "'" +
            "}";
    }
}
