package com.jwland.jwland.entity.status;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GradeTest {

    @Test
    void findBySchoolClassificationTest(){
        assertThat(Grade.findBySchoolClassification("ELEMENTARY")).containsExactly(Grade.ONE, Grade.TWO, Grade.THREE, Grade.FOUR, Grade.FIVE, Grade.SIX);
        assertThat(Grade.findBySchoolClassification("MIDDLE")).containsExactly(Grade.ONE, Grade.TWO, Grade.THREE);
        assertThat(Grade.findBySchoolClassification("HIGH")).containsExactly(Grade.ONE, Grade.TWO, Grade.THREE);
    }

}

