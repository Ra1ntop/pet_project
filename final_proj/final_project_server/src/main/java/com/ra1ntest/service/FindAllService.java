package com.ra1ntest.service;

import com.ra1ntest.persistance.entity.BaseEntity;

import java.util.List;

public interface FindAllService<BE extends BaseEntity> {
    List<BE> findAll();
}
