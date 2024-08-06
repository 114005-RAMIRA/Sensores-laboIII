package ar.edu.utn.frc.tup.lc.iii.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shoe {

    private Long id;
    private String model;
    private String description;
    private Integer size;
    private Boolean gender; //true men, false women
    private String color;
    private Double price;
    private Brand brand;
}
