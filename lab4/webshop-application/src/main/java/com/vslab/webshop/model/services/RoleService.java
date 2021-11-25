package com.vslab.webshop.model.services;

import com.vslab.webshop.model.data.objects.Role;

/**
 * interface for the role microservice
 */
public interface RoleService {

    Role getRoleByLevel(int level);

}
