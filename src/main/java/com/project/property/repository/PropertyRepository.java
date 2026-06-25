package com.project.property.repository;

import com.project.property.entity.Property;
import com.project.property.enums.Furnish;
import com.project.property.enums.Gender;
import com.project.property.enums.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p WHERE " +
           "(:city IS NULL OR p.address.city = :city) AND " +
           "(:area IS NULL OR p.address.area = :area) AND " +
           "(:type IS NULL OR p.type = :type) AND " +
           "(:gender IS NULL OR p.gender = :gender) AND " +
           "(:furnish IS NULL OR p.furnishing = :furnish) AND " +
           "(:minPrice IS NULL OR p.rent >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.rent <= :maxPrice) " +
           "ORDER BY p.createdAt DESC")
    List<Property> filterProperties(
        @Param("city") String city,
        @Param("area") String area,
        @Param("type") PropertyType type,
        @Param("gender") Gender gender,
        @Param("furnish") Furnish furnish,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice
    );

    @Query("SELECT p FROM Property p WHERE " +
           "(:city IS NULL OR p.address.city = :city) AND " +
           "(:area IS NULL OR p.address.area = :area) AND " +
           "(:type IS NULL OR p.type = :type) AND " +
           "(:gender IS NULL OR p.gender = :gender) AND " +
           "(:furnish IS NULL OR p.furnishing = :furnish) AND " +
           "(:minPrice IS NULL OR p.rent >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.rent <= :maxPrice) " +
           "ORDER BY p.createdAt DESC")
    Page<Property> filterPropertiesPaged(
        @Param("city") String city,
        @Param("area") String area,
        @Param("type") PropertyType type,
        @Param("gender") Gender gender,
        @Param("furnish") Furnish furnish,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice,
        Pageable pageable
    );
    List<Property> findByOwnerPhone(String phone);
    
}
