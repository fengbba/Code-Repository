package com.example.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-14 14:23
 **/
@Data
public class PaginationDTO {

    private List<QuestionDTO> questionDTOList;                              //  问题列表
    private boolean showFirstPage = false;                                  //  首页按钮
    private boolean showPrevPage = false;                                   //  上一页按钮
    private boolean showNextPage = false;                                   //  下一页按钮
    private boolean showEndPage = false;                                    //  尾页按钮
    private Integer currentPage;                                            //  当前页页码
    private List<Integer> pages = new ArrayList<>();                        //  分页条页码集合
    private long totalPage;                                                 //  总页数





    public void setPaination(long total_count, Integer pageNumber, Integer pageSize) {

        /*
         * 计算总页数
         * */

        if (0 == total_count % pageSize) {
            totalPage = total_count / pageSize;
        } else {
            totalPage = total_count / pageSize + 1;
        }
        /*
         * 容错措施
         * */
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageNumber > totalPage) {
            pageNumber = Math.toIntExact(totalPage);
        }
        this.currentPage = pageNumber;

        /*
         *  分页条页码集合
         * */
        pages.add(pageNumber);
        for (int i = 1; i <= 3; i++) {                          //前后封顶展示三页
            if (pageNumber - i > 0) {                           //判断前面三页是否展示
                pages.add(0, pageNumber - i);
            }
            if (pageNumber + i <= totalPage) {                  //判断后面三页是否展示
                pages.add(pageNumber + i);
            }
        }

        /*
         * 是否展示翻页按钮
         * */
        if (1 != pageNumber) {
            showPrevPage = true;        // 显示上一页按钮
        }

        if (totalPage != pageNumber) {
            showNextPage = true;        // 显示下一页按钮
        }

        if (1 < pageNumber - 3) {
            showFirstPage = true;       // 显示首页按钮
        }

        if (totalPage > pageNumber + 3) {
            showEndPage = true;         // 显示尾页按钮
        }

    }


}
