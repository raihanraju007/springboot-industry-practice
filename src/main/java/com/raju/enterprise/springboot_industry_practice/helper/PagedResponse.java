package com.raju.enterprise.springboot_industry_practice.helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PagedResponse<T> {

    private List<T> content;
    private int page;          // current page (0-based)
    private int size;          // page size
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private boolean hasNext;
    private boolean hasPrevious;

    public static <T> PagedResponse<T> from(Page<T> page) {
        PagedResponse<T> r = new PagedResponse<>();
        r.setContent(page.getContent());
        r.setPage(page.getNumber());
        r.setSize(page.getSize());
        r.setTotalElements(page.getTotalElements());
        r.setTotalPages(page.getTotalPages());
        r.setFirst(page.isFirst());
        r.setLast(page.isLast());
        r.setHasNext(page.hasNext());
        r.setHasPrevious(page.hasPrevious());
        return r;
    }
}