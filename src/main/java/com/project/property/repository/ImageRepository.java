package com.project.property.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.property.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
