package com.example.forum.service.cache;

import com.example.forum.bo.User;

/**
 * @author genghaoran
 */
public interface IUserCacheService {
    User addUser(int id);

    boolean deleteUser(int id);

    int countUser();

    void addUserCount();

    void removeUserCount();

    boolean getUserById(int userId);
}
