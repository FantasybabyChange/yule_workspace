<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                <div class="comment_list_outer_container">
                    <div class="lang_ltr_arr"></div>
                    <h4 class="comment_list_score_title">用户评分</h4>
                    <p class="comment_list_score_count">分数来自
                        <strong><span id='commetnNumCommentPanel'></span>条评语</strong>
                    </p>
                    <div class="comment_list_score" id='avgScorDIV'></div>
                    <h4 class="comment_list_score_title">单项评分</h4>
                    <ul class="comment_score_breakdown_list" id='commentListUL'>
                    </ul>
                </div>
                <div class="comment_list_container">
                    <div class="comment_list_nav clearfix">
                        <p>显示来自此类客人的评语：</p>
                        <select id="selectComment"class="commenter_type_filter" id="commenter_type_filter">
                            <option value="" data-quantity="172" data-customer-type="total" data-lang="zh-cn" data-cc="tw" data-dist="1" data-pagename="howard-plaza-kaohsiung" data-add="">
                                全部评论者
                            </option>
                            <option value="0" data-quantity="62" data-customer-type="family_with_children" data-lang="zh-cn" data-cc="tw" data-dist="2" data-pagename="howard-plaza-kaohsiung" data-add="">
                               朋友聚会
                            </option>
                            <option value="1" data-quantity="62" data-customer-type="family_with_children" data-lang="zh-cn" data-cc="tw" data-dist="2" data-pagename="howard-plaza-kaohsiung" data-add="">
                               商务聚会
                            </option>
                        </select>
                    </div>
                    <div class="comment_list_page_container">
                        <div class="comment_list_pagination">
                            <p class="page_link comment_previous_page">
                          <a href='javaScript:;'name="comment_last_page_link">      前一页</a>
                            </p>
                            <p class="page_link comment_next_page">
                                <a href='javaScript:;'name="comment_next_page_link">下一页</a>
                            </p>
                            <p class="page_showing">
                                显示 <span name="pageNoSPan"></span> - <span name="pageCountSpan"> </span>
                            </p>
                        </div>
                        <ul class="comment_list" id='companyCommentUL'>
                           			正在加载.....
                        </ul>
                         <div class="comment_list_pagination">
                            <p class="page_link comment_previous_page">
                                <a href='javaScript:;'name="comment_last_page_link">前一页</a>
                            </p>
                            <p class="page_link comment_next_page">
                              <a href='javaScript:;'name="comment_next_page_link">下一页</a>
                            </p>
                            <p class="page_showing">
                                显示 <span name="pageNoSPan"></span> - <span name="pageCountSpan"> </span>
                            </p>
                        </div>
                    </div>
                </div>
