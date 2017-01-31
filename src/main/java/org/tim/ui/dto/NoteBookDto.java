package org.tim.ui.dto;

/**
 * @author Timur Nasibullin
 * @since 1/31/2017
 */
public class NoteBookDto {
    private Long id;
    private String name;

    public NoteBookDto() {
    }

    public NoteBookDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
