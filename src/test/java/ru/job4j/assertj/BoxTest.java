package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
    }

    @Test
    void isThisNothing() {
        Box box = new Box(15, 10);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void isThisEdgeNothing() {
        Box box = new Box(8, -1);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void numberOfVerticesInTetrahedron() {
        Box box = new Box(4, 5);
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
    }

    @Test
    void numberOfVerticesInSphere() {
        Box box = new Box(0, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
    }

    @Test
    void numberOfVerticesInCube() {
        Box box = new Box(8, 7);
        assertThat(box.getNumberOfVertices()).isEqualTo(8);
    }

    @Test
    void notNumberOfVertices() {
        Box box = new Box(10, 7);
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
    }

    @Test
    void notExist() {
        Box box = new Box(5, 5);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void existTetrahedron() {
        Box box = new Box(4, 5);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void existSphere() {
        Box box = new Box(0, 5);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void areaOfTheSphere() {
        Box box = new Box(8, 1);
        assertThat(box.getArea()).isEqualTo(6.0, withPrecision(1.0d));
    }

    @Test
    void areaOfTheTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.getArea()).isCloseTo(173.20d, withPrecision(0.01d));
    }

    @Test
    void areaOfTheCube() {
        Box box = new Box(0, 1);
        assertThat(box.getArea()).isCloseTo(12.56d, withPrecision(0.01d));
    }
}