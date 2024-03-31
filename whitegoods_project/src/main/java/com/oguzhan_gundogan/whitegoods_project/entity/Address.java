package com.oguzhan_gundogan.whitegoods_project.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

//TODO: Burada country, city, district gibi field'ları başka bir api'dan
// restTemplate ile çekebiliriz.
// ? direkt javascript ile yapabilir miyiz? spring boot'dan işlem yapmadan
@Embeddable
// Çünkü Address class'ını Customer Entity'sinde kullanıyoruz.
// Entity olduğu için gerekli. Düz modelde gerek yok.
@Getter
@Setter
public class Address {

    private String country;

    private String city;

    private String district;

    private String postCode;

    private String addressLine;
}