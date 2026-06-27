package com.ecommerce.yep.util;


import lombok.Getter;

@Getter
public enum SystemMessage {

    EMAIL_ALREADY_EXISTS(198, "Bu email artıq istifadə olunub!"),
    USER_NOT_FOUND(199, "İstifadəçi tapılmadı!"),
    ORDER_NOT_FOUND(199, "Sifaris tapılmadı!"),

    SUCCES_REGISTER(200, "Uğurla qeydiyyatdan keçdiniz!"),
    WRONG_PASSWORD(201,"Sifre yanlisdir   "),
    SUCCES_LOGIN(202, "Uğurla giris etdiniz!"),
    CATEGORY_ALREADY_EXISTS(203, "Bu KATEGORITA artıq istifadə olunub!"),
    CATEGORY_NOT_EXISTS(204, "Bu KATEGORITA movcud deyil!"),
    CATEGORY_SUCCES_DELETE(205, "Kategoriya ugurla silindi   "),
    CATEGORY_SUCCES_UPDATE(206, "Kategoriya ugurla guncellendi   "),
    CATEGORY_SUCCES_CREATED(207, "Kategoriya ugurla YARADILDI   "),
    PRODUCT_NOT_FOUND(209,"product not found!"),
    CART_NOT_FOUND(300,"Cart not found!"),
    SUCCESS(200, "Operation successful"),
    FAILED(404, "Operation failed"),


    VALIDATION_ERROR(400, "Daxil edilən məlumatlarda səhv var"),
    INTERNAL_SERVER_ERROR(500, "Sistemdə gözlənilməz xəta baş verdi");






    private int code;
    private String message;

    SystemMessage(int code, String message){
        this.code = code;
        this.message = message;
    }



}
