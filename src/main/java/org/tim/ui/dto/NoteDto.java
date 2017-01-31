package org.tim.ui.dto;

import java.util.Set;

/**
 * @author Timur Nasibullin
 * @since 1/31/2017
 */
public class NoteDto {
    private Long id;
    private String header;
    private String body;
    private Set<String> tags;

    public NoteDto() {
    }

    public NoteDto(Long id, String header, String body) {
        this.id = id;
        this.header = header;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return header;
    }
}
