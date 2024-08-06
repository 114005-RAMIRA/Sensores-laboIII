package ar.edu.utn.frc.tup.lc.iii.entities;

import ar.edu.utn.frc.tup.lc.iii.models.Brand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Shoes")
public class ShoeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="model", nullable = false)
    private String model;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="size", nullable = false)
    private Integer size;

    @Column(name="gender", nullable = false)
    private Boolean gender; //true men, false women

    @Column(name="color", nullable = false)
    private String color;

    @Column(name="price", nullable = false)
    private Double price;

    //union con la marca
    @ManyToOne
    @JoinColumn(name="brand_id")
    private BrandEntity brand;


}
