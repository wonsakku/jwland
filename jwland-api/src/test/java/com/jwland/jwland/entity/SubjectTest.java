package com.jwland.jwland.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SubjectTest {

    @Test
    void change_Data_Test(){

        // given
        final Subject target = Subject.toInsertEntity("name", "Y");
        target.assignId(1L);
        final Subject updating = Subject.toUpdateEntity(1L, "updNm", "N");

        // when
        target.changeData(updating);

        // then
        assertThat(target.getName()).isEqualTo("updNm");
        assertThat(target.getUseYn()).isEqualTo("N");
    }

    @Test
    @DisplayName("subjectId 가 서로 일치하지 않은 경우 예외처리")
    void change_Data_Exception_When_Id_Different(){
        final Subject target = Subject.toInsertEntity("name", "Y");
        target.assignId(1L);
        final Subject updating = Subject.toUpdateEntity(2L, "updNm", "N");

        assertThatThrownBy(() -> {
            target.changeData(updating);
        }).isInstanceOf(IllegalArgumentException.class);
    }


}


