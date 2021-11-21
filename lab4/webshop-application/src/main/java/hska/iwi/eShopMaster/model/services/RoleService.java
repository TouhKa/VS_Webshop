package hska.iwi.eShopMaster.model.services;

import hska.iwi.eShopMaster.model.data.objects.Role;

/**
 * interface for the role microservice
 */
public interface RoleService {

    Role getRoleByLevel(int level);

}
