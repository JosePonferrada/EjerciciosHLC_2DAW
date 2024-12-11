/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author diurno
 */
public class Vuelo {
    
    private String code;
    private String origin;
    private String destination;
    private double price;

    public Vuelo(String code, String origin, String destination, double price) {
        this.code = code;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }
    
    public void aumentaPrecio() {
        
        this.price *= 1.10;
        
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
