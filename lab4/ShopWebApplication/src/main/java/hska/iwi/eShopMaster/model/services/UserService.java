package hska.iwi.eShopMaster.model.services;

import hska.iwi.eShopMaster.model.data.objects.User;

/**
 * interface for the user microservice
 */
public interface UserService {
    
    void addUser(User user);
    
    User[] getAllUsers();
    
}
