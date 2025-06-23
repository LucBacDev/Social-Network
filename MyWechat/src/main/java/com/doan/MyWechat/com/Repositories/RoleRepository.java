package com.doan.MyWechat.com.Repositories;

import com.doan.MyWechat.com.Entities.Role;

public interface RoleRepository extends BaseRepository<Role, Integer>{
	Role findRoleByUserId(int UserId);
}
