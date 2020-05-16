package com.example.amo1.service.Impl;

import com.example.amo1.mapper.ManuscriptMapper;
import com.example.amo1.mapper.ManuscriptRepository;
import com.example.amo1.model.Manuscript;
import com.example.amo1.service.ManuscriptService;
import com.github.pagehelper.PageInfo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ManuscriptServiceImpl implements ManuscriptService {

    @Autowired
    private ManuscriptMapper manuscriptMapper;


    @Autowired
    private ManuscriptRepository manuscriptRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 发布稿件
     * @param manuscript
     */
    @Override
    public void publishManuscript(Manuscript manuscript) {
        String html = manuscript.getContent();
        //处理html特殊标签
        String temp = HtmlUtils.htmlEscape(html);
        manuscript.setContent(temp);
        //初始化稿件的数据
        manuscript.setLove(0);
        manuscript.setForward(0);
        manuscript.setComment(0);
        manuscript.setCollection(0);
        manuscriptMapper.insert(manuscript);
        //解析处理了的html语句
        manuscript.setContent(HtmlUtils.htmlUnescape(temp));
        manuscriptRepository.save(manuscript);
    }

    /**
     * 查询稿件内容
     * @param id
     * @return
     */
    @Override
    public Manuscript select(Integer id) {
        Manuscript manuscript = manuscriptMapper.selectByPrimaryKey(id);
        String temp = manuscript.getContent();
        //解析处理了的html语句
        manuscript.setContent(HtmlUtils.htmlUnescape(temp));
        return manuscript;
    }

    /**
     * 删除稿件
     * @param id
     */
    @Override
    public void delete(Integer id) {
        manuscriptMapper.deleteByPrimaryKey(id);
        manuscriptRepository.deleteById(id);
    }

    /**
     * 修改稿件
     * @param manuscript
     */
    @Override
    public void update(Manuscript manuscript) {
        manuscriptMapper.updateByPrimaryKey(manuscript);
        manuscriptRepository.save(manuscript);
    }

    /**
     * 分页显示检索结果
     * @param currPage
     * @param pageSize
     * @param keyword
     * @return
     */
    @Override
    public PageInfo<Manuscript> search(Integer currPage, Integer pageSize, String keyword) {
        //分页(ES从第0页开始查)
        Pageable pageable = PageRequest.of(currPage-1,pageSize);

        //构建查询条件
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        //添加分词查询
        queryBuilder.should(QueryBuilders.multiMatchQuery(keyword,"content","username"));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                //设置高亮字段显示的样式
                .withHighlightFields(
                        new HighlightBuilder.Field("content").preTags("<em style='color:red'>").postTags("</em>"),
                        new HighlightBuilder.Field("username").preTags("<em style='color:red'>").postTags("</em>")
                )
                .withPageable(pageable)
                .build();

        //实施查询，注意：这里的泛型最后和 elasticsearch 中的字段对应
        Page<Manuscript> manuscriptPage = elasticsearchTemplate.queryForPage(searchQuery, Manuscript.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<Manuscript> manuscriptList = new ArrayList<>();
                //命中记录
                SearchHits hits = response.getHits();
                for (SearchHit hit : hits) {
                    if (hits.totalHits <= 0) {
                        return null;
                    }
                    Manuscript manuscript = new Manuscript();
                    manuscript.setId((Integer) hit.getSourceAsMap().get("id"));
                    manuscript.setUserId((Integer) hit.getSourceAsMap().get("userId"));
                    manuscript.setUsername(String.valueOf(hit.getSourceAsMap().get("username")));
                    manuscript.setTitle(String.valueOf(hit.getSourceAsMap().get("title")));
                    manuscript.setCover(String.valueOf(hit.getSourceAsMap().get("cover")));
                    manuscript.setContent(HtmlUtils.htmlUnescape(String.valueOf(hit.getSourceAsMap().get("content"))));
                    manuscript.setPublishdate(new Date((Long) (hit.getSourceAsMap().get("pulishdate"))));
                    manuscript.setLove((Integer) hit.getSourceAsMap().get("love"));
                    manuscript.setForward((Integer) hit.getSourceAsMap().get("forward"));
                    manuscript.setComment((Integer) hit.getSourceAsMap().get("comment"));
                    manuscript.setCollection((Integer) hit.getSourceAsMap().get("collection"));



                    //设置高亮（若对应字段有高亮的话）
                    setHighLight(hit, "content", manuscript);
                    setHighLight(hit, "username", manuscript);

                    manuscriptList.add(manuscript);
                }

                return new AggregatedPageImpl<>((List<T>) manuscriptList);
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });

        //检索出需要分页查询的所有数据(可以高亮显示)
        PageInfo<Manuscript> pageInfo = new PageInfo<>();
        pageInfo.setList(manuscriptPage.getContent());
        //分页检索:可以查询出数记录数和每页条数
        Page<Manuscript> pages = this.search(currPage,keyword);
        pageInfo.setTotal(pages.getTotalElements());
        pageInfo.setPageSize(pages.getSize());

        return pageInfo;

    }

    /**
     * 检索库分页查询(可以查出,总记录数和总页数)
     * @param currPage
     * @param keyword
     * @return
     */
    public Page<Manuscript> search(Integer currPage, String keyword){
        //构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加分词查询
        queryBuilder.withQuery(QueryBuilders.multiMatchQuery(keyword,"bname","author"));
        //分页(ES从第0页开始查)
        queryBuilder.withPageable(PageRequest.of(currPage-1,5));
        //搜索
        Page<Manuscript> page = manuscriptRepository.search(queryBuilder.build());
        return page;
    }


    /**
     * 设置高亮
     * @param hit 命中记录
     * @param filed 字段
     * @param object 待赋值对象
     */
    private static void setHighLight(SearchHit hit, String filed, Object object){
        //获取对应的高亮域
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        HighlightField highlightField = highlightFields.get(filed);
        if (highlightField != null){
            //取得定义的高亮标签
            String highLightMessage = highlightField.fragments()[0].toString();
            // 反射调用set方法将高亮内容设置进去
            try {
                String setMethodName = parSetMethodName(filed);
                Class<?> Clazz = object.getClass();
                Method setMethod = Clazz.getMethod(setMethodName, String.class);
                setMethod.invoke(object, highLightMessage);
            } catch (Exception e) {
                System.out.println("反射报错"+e);
            }
        }
    }

    /**
     * 根据字段名，获取Set方法名
     * @param fieldName 字段名
     * @return  Set方法名
     */
    private static String parSetMethodName(String fieldName){
        if (fieldName==null || fieldName.equals("")){
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_'){
            startIndex = 1;
        }
        return "set" + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }
}
