package com.lxiaocode.role.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxiaocode.role.entity.Role;
import com.lxiaocode.role.entity.User;
import com.lxiaocode.role.entity.UserRole;
import com.lxiaocode.role.mapper.RoleMapper;
import com.lxiaocode.role.mapper.UserMapper;
import com.lxiaocode.role.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lixiaofeng
 * @date 2020/9/10 上午8:57
 * @blog http://www.lxiaocode.com/
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查找用户
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("username", username);
        User user = userMapper.selectOne(userWrapper);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        // 查找角色
        QueryWrapper<UserRole> userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.eq("user_id", user.getId());
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleWrapper);
        Set roles = new HashSet();
        QueryWrapper<Role> roleWrapper = new QueryWrapper<>();
        for (UserRole ur : userRoles){
            // 设置角色
            roleWrapper.eq("id", ur.getRoleId());
            Role role = roleMapper.selectOne(roleWrapper);
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        user.setAuthorities(roles);
        return user;
    }
}
