package com.antra.videomanager.domain.entity;

import com.antra.videomanager.domain.entity.base.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "UserRole")
public class UserRole extends CommonEntity{

    @Id
    @GeneratedValue(generator = "user_role_UUID")
    @GenericGenerator(name = "user_role_UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UserRoleId")
    private String userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;

    @Column(name = "RoleId", insertable = false, updatable = false)
    private Integer roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RoleId")
    private Role_L role_l;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role_L getRole_l() {
        return role_l;
    }

    public void setRole_l(Role_L role_l) {
        this.role_l = role_l;
    }

//    public Integer getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(Integer roleId) {
//        this.roleId = roleId;
//    }
}
