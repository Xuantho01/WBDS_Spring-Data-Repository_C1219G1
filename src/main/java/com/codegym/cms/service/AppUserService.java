package com.codegym.cms.service;

import com.codegym.cms.model.AppUser;

public interface AppUserService {
    AppUser getUserByUsername(String username);
}
