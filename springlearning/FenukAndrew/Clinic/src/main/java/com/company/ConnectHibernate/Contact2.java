package com.company.ConnectHibernate;

import javax.persistence.*;

    @Entity
    @Table(name = "contacts2")
    public class Contact2 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private String type;

        @Column
        private String data;

        public Contact2(String type, String data) {
            this.type = type;
            this.data = data;
        }

        public Contact2() {
        }

        public Long getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
