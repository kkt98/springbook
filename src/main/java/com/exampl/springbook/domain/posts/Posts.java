package com.exampl.springbook.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

    @Getter
    @NoArgsConstructor
    @Table
    @Entity
    public class Posts extends BaseTimeEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 500, nullable = true)
        private String title;

        @Column(columnDefinition = "TEXT", nullable = true)
        private String content;

        private String author;

        @Builder
        private Posts(String title, String content, String author){
            this.title = title;
            this.content = content;
            this.author = author;
        }

        public void update(String title, String content){
            this.title = title;
            this.content = content;
        }


    }
