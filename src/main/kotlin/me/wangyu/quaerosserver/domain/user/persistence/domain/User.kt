package me.wangyu.quaerosserver.domain.user.persistence.domain

import me.wangyu.quaerosserver.domain.user.persistence.domain.value.Password
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Embedded
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Table("tbl_user")
class User (
    @Id
    @Column("user_id")
    var id: String? = null,
    @Column("email")
    val email: String,
    @Embedded.Empty
    val password: Password
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.emptyList()
    }

    override fun getPassword(): String {
        return password.value
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}