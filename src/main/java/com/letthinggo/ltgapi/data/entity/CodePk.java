package com.letthinggo.ltgapi.data.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class CodePk implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="groupCode")
    private GroupCode groupCode;

    @Column(name="CODE")
    private String code;

}
