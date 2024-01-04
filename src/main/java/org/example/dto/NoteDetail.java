package org.example.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "NOTE_DETAIL")
@NamedQuery(name="NoteDetail.findAll" ,query = "Select nd from NoteDetail nd")
@Data
public class NoteDetail {

    @Id
    @SequenceGenerator(name="NOTE_DETAIL_ID_GENERATOR",sequenceName = "SEQ_NOTE_DETAIL",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO ,generator = "NOTE_DETAIL_ID_GENERATOR")
    @Column(name="NOTE_DETAIL_ID",unique = true ,nullable = false)
    private Integer noteDetailId;

    @Column(name="NOTE")
    private String note;

    @Column(name="USERNAME")
    private String username;
    @Column(name="PASSWORD")
    private String password;
}
