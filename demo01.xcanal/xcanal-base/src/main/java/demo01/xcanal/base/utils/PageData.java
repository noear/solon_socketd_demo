package demo01.xcanal.base.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData implements Serializable {
    public static final int MAX_PAGE_SIZE = 20;

    /**
     * 启始ID
     */
    private long startId;

    /**
     * 页长
     */
    private int pageSize;

    /**
     * 页码
     */
    private int pageNumber;


    public static final PageData _first = new PageData(1, MAX_PAGE_SIZE, 1);

    public static PageData first() {
        return _first;
    }
}
