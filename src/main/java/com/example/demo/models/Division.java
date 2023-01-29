package com.example.demo.models;
import javax.persistence.*;

@Entity
@Table(name = "tb_m_division")

public class Division {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column (name = "name", nullable = false)
    private String Name;

    public Integer getId(){
        return Id;
    }

    public void setId(Integer Id){
        this.Id = Id;
    }

    public String getName(){
        return Name;
    }
    
    public void setName(String Name){
        this.Name = Name;
    }
    
}