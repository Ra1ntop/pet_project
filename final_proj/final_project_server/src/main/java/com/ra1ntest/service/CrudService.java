package com.ra1ntest.service;

import com.ra1ntest.persistance.entity.BaseEntity;

public interface CrudService<BE extends BaseEntity> {
    void create(BE be);
    BE findById(Long id);

}
