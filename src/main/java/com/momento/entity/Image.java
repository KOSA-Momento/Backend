package com.momento.entity;

import com.momento.entity.id.ProductContentId;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter @Setter
public class Image {

    @EmbeddedId
    private ProductContentId productContentId;

}
