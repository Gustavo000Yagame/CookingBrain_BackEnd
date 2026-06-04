package org.example.cookingbrain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
    public class Avaliacao {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idAvaliacao;

        private Integer nota;
        private String comentario;

        @ManyToOne
        @JoinColumn(name = "Prato_idPrato")
        private Prato prato;

        public Avaliacao() {}
    }

