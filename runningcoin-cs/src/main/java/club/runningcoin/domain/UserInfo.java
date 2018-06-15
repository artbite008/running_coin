package club.runningcoin.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UserInfo.
 */
@Entity
@Table(name = "User_Info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UserId")
    private Long id;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "Status")
    private String statusField;

    @Column(name = "Role")
    private String role;

    @Column(name = "Coins")
    private Double coins;

    @Column(name = "Icon")
    private String icon;

    @Column(name = "TotalDistance")
    private Float totalDistance;

    @Column(name = "MetaData")
    private String metaData;

    @Column(name = "OpenId")
    private String openId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfo userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusField() {
        return statusField;
    }

    public UserInfo statusField(String statusField) {
        this.statusField = statusField;
        return this;
    }

    public void setStatusField(String statusField) {
        this.statusField = statusField;
    }

    public String getRole() {
        return role;
    }

    public UserInfo role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getCoins() {
        return coins;
    }

    public UserInfo coins(Double coins) {
        this.coins = coins;
        return this;
    }

    public void setCoins(Double coins) {
        this.coins = coins;
    }

    public String getIcon() {
        return icon;
    }

    public UserInfo icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Float getTotalDistance() {
        return totalDistance;
    }

    public UserInfo totalDistance(Float totalDistance) {
        this.totalDistance = totalDistance;
        return this;
    }

    public void setTotalDistance(Float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getMetaData() {
        return metaData;
    }

    public UserInfo metaData(String metaData) {
        this.metaData = metaData;
        return this;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getOpenId() {
        return openId;
    }

    public UserInfo openId(String openId) {
        this.openId = openId;
        return this;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
        UserInfo userInfo = (UserInfo) o;
        if (userInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", statusField='" + getStatusField() + "'" +
            ", role='" + getRole() + "'" +
            ", coins=" + getCoins() +
            ", icon='" + getIcon() + "'" +
            ", totalDistance=" + getTotalDistance() +
            ", metaData='" + getMetaData() + "'" +
            ", openId='" + getOpenId() + "'" +
            "}";
    }
}
